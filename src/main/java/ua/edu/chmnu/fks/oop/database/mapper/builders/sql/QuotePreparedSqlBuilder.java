package ua.edu.chmnu.fks.oop.database.mapper.builders.sql;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import ua.edu.chmnu.fks.oop.database.model.Quote;

import java.util.Arrays;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class QuotePreparedSqlBuilder implements SqlBuilder {
    private Quote quote;
    private List<String> tableList;

    public static QuotePreparedSqlBuilder create(Quote quotes, String... tables) {
        return new QuotePreparedSqlBuilder(quotes, Arrays.asList(tables));
    }

    public String buildSelectAll() {
        String sql = "SELECT q.id as quote_id, q.content as quote_content, " +
                "q.created_time as quote_created_time, " +
                "p.id as post_id, p.title as post_title, p.content as post_content, " +
                "p.created_time as post_created_time, p.updated_time as post_updated_time, " +
                "u1.id as user_id, u1.email as user_email, u1.phone as user_phone, " +
                "u1.birth_day as user_birth_day, u1.address as user_address, " +
                "u2.id as quote_user_id, u2.email as quote_user_email, u2.phone as quote_user_phone, " +
                "u2.birth_day as quote_user_birth_day, u2.address as quote_user_address FROM %s q " +
                "JOIN %s p ON q.post_id=p.id " +
                "JOIN %s u1 ON u1.id=p.user_id " +
                "JOIN %s u2 ON u2.id=q.user_id";
        return String.format(sql, tableList.get(0), tableList.get(1), tableList.get(1));
    }
    public String buildSelectById() {
        return buildSelectAll() + " WHERE q.id=?";
    }

    public String buildPageSelect() {
        return buildSelectAll() + " OFFSET ? LIMIT ?";
    }

    public String buildInsert() {
        String sql = "INSERT INTO %s (content, created_time, user_id, post_id) " +
                     "VALUES (?, ?, ?, ?, ?)";
        return String.format(sql, tableList.get(0));
    }

    public String buildUpdate() {
        String sql = "UPDATE %s set content=? " +
                     "WHERE id=?";
        return String.format(sql, tableList.get(0));
    }
}
