package ua.edu.chmnu.fks.oop.database.mapper.builders.sql;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import ua.edu.chmnu.fks.oop.database.model.Post;

import java.util.Arrays;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PostPreparedSqlBuilder implements SqlBuilder {
    private Post post;
    private List<String> tableList;

    public static PostPreparedSqlBuilder create(Post post, String... tables) {
        return new PostPreparedSqlBuilder(post, Arrays.asList(tables));
    }

    public String buildSelectAll() {
        String sql = "SELECT p.id as post_id, p.title as post_title, p.content as post_content, " +
                "p.created_time as post_created_time, p.updated_time as post_updated_time, " +
                "u.id as user_id, u.email as user_email, u.phone as user_phone, " +
                "u.birth_day as user_birth_day, u.address as user_address FROM %s p " +
                "JOIN %s u ON p.user_id=u.id";
        return String.format(sql, tableList.get(0), tableList.get(1));
    }
    public String buildSelectById() {
        return buildSelectAll() + " WHERE p.id=?";
    }

    public String buildPageSelect() {
        return buildSelectAll() + " OFFSET ? LIMIT ?";
    }

    public String buildInsert() {
        String sql = "INSERT INTO %s (title, content, created_time, updated_time, user_id) " +
                     "VALUES (?, ?, ?, ?, ?)";
        return String.format(sql, tableList.get(0));
    }

    public String buildUpdate() {
        String sql = "UPDATE %s set title=?, content=?, updated_time=? " +
                     "WHERE id=?";
        return String.format(sql, tableList.get(0));
    }
}
