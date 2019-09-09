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
public class ConnectionConfigImpl implements ConnectionConfig {
    private String url;
    private String user;
    private String password;

    @Override
    public String jdbcUrl() {
        return this.url;
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
