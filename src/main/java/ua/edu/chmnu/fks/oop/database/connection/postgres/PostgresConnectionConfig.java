package ua.edu.chmnu.fks.oop.database.connection.postgres;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.edu.chmnu.fks.oop.database.connection.ConnectionConfig;

@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostgresConnectionConfig implements ConnectionConfig {
    private String host;
    private int port;
    private String user;
    private String password;
    private String database;

    @Override
    public String jdbcUrl() {
        return String.format("jdbc:postgresql://%s:%d/%s", host, port, database);
    }

    @Override
    public String jdbcUser() {
        return this.user;
    }

    @Override
    public String jdbcPassword() {
        return this.password;
    }
}
