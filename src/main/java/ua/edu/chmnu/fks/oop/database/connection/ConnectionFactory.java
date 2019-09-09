package ua.edu.chmnu.fks.oop.database.connection;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ua.edu.chmnu.fks.oop.database.connection.postgres.ConnectionConfigImpl;
import ua.edu.chmnu.fks.oop.database.exceptions.ConfigException;
import ua.edu.chmnu.fks.oop.database.exceptions.DaoException;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Properties;
import java.util.function.BiFunction;
import java.util.function.Function;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ConnectionFactory {
    private static final String RESOURCE_DB_PROPERTIES = "/db.properties";

    public static Connection createConnection(ConnectionConfig connectionConfig) throws SQLException {
        return DriverManager.getConnection(
                connectionConfig.jdbcUrl(),
                connectionConfig.jdbcUser(),
                connectionConfig.jdbcPassword()
        );
    }

    private static ConnectionConfig getConnectionConfig() {
        BiFunction<Properties, String, String> propReader = (props, key) ->
                Optional.ofNullable(props.getProperty(key))
                .orElseThrow(() -> new ConfigException("Property " + key + " is not set"));

        try(InputStream in = ConnectionFactory.class.getResourceAsStream(RESOURCE_DB_PROPERTIES)) {
            Properties properties = new Properties();
            properties.load(in);

            return ConnectionConfigImpl.builder()
                    .url(propReader.apply(properties, "db.url"))
                    .user(propReader.apply(properties, "db.user"))
                    .password(propReader.apply(properties, "db.password"))
                    .build();
        } catch (IOException e) {
            throw new DaoException(e);
        }
    }

    public static Connection createConnection() throws SQLException {
        return createConnection(getConnectionConfig());
    }
}
