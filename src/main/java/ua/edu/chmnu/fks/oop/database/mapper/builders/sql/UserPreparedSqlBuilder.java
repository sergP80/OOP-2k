package ua.edu.chmnu.fks.oop.database.mapper.builders.sql;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import ua.edu.chmnu.fks.oop.database.model.Post;
import ua.edu.chmnu.fks.oop.database.model.User;

import java.util.Arrays;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserPreparedSqlBuilder implements SqlBuilder {
    private User user;
    private List<String> tableList;

    public static UserPreparedSqlBuilder create(User user, String... tables) {
        return new UserPreparedSqlBuilder(user, Arrays.asList(tables));
    }

    public String buildSelectAll() {
        String sql = "SELECT u.id as user_id, u.email as user_email, u.phone as user_phone, " +
                "u.birth_day as user_birth_day, u.address as user_address FROM %s u";
        return String.format(sql, tableList.get(0));
    }
    public String buildSelectById() {
        return buildSelectAll() + " WHERE u.id=?";
    }

    public String buildPageSelect() {
        return buildSelectAll() + " OFFSET ? LIMIT ?";
    }

    public String buildInsert() {
        String sql = "INSERT INTO %s (email, phone, birth_day, address) " +
                     "VALUES (?, ?, ?, ?)";
        return String.format(sql, tableList.get(0));
    }

    public String buildUpdate() {
        String sql = "UPDATE %s set email=?, phone=?, birth_day=?, address=? " +
                     "WHERE id=?";
        return String.format(sql, tableList.get(0));
    }
}
