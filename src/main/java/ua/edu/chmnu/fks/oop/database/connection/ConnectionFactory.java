package ua.edu.chmnu.fks.oop.database.connection;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ConnectionFactory {
    public static Connection createConnection(ConnectionConfig connectionConfig) throws SQLException {
        return DriverManager.getConnection(
                connectionConfig.jdbcUrl(),
                connectionConfig.jdbcUser(),
                connectionConfig.jdbcPassword()
        );
    }
}
