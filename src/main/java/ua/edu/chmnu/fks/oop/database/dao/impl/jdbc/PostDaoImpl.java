package ua.edu.chmnu.fks.oop.database.dao.impl.jdbc;

import ua.edu.chmnu.fks.oop.database.dao.PostDao;
import ua.edu.chmnu.fks.oop.database.dao.UserDao;
import ua.edu.chmnu.fks.oop.database.exceptions.DaoException;
import ua.edu.chmnu.fks.oop.database.mapper.LocalDateTimeMapper;
import ua.edu.chmnu.fks.oop.database.mapper.PostMapper;
import ua.edu.chmnu.fks.oop.database.mapper.builders.sql.PostPreparedSqlBuilder;
import ua.edu.chmnu.fks.oop.database.model.Post;
import ua.edu.chmnu.fks.oop.database.model.User;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PostDaoImpl extends GenericDaoImpl<Post, Long> implements PostDao {
    private final Connection connection;
    private final PostMapper postMapper;
    private final LocalDateTimeMapper dateTimeMapper;

    public PostDaoImpl(Connection connection, PostMapper postMapper, LocalDateTimeMapper dateTimeMapper) {
        this.connection = connection;
        this.postMapper = postMapper;
        this.dateTimeMapper = dateTimeMapper;
    }

    @Override
    public String tableName() {
        return TABLE_NAME;
    }

    @Override
    public Connection connection() {
        return this.connection;
    }

    @Override
    public Post create(Post post) throws DaoException {
        String sql = PostPreparedSqlBuilder.create(post, tableName(), UserDao.TABLE_NAME).buildInsert();
        try(PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, post.getTitle());
            statement.setString(2, post.getContent());
            statement.setTimestamp(3, dateTimeMapper.convertTo(LocalDateTime.now()));
            statement.setTimestamp(4, dateTimeMapper.convertTo(LocalDateTime.now()));
            statement.setLong(5, Optional.ofNullable(post.getUser()).map(User::getId).orElse(null));
            int result = statement.executeUpdate();
            if (result != 1) {
                throw new DaoException("Could not inserted post record");
            }
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    post.setId(generatedKeys.getLong(1));
                } else {
                    throw new DaoException("Could not insert post record with generation key");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return post;
    }

    @Override
    public Post update(Post post) throws DaoException {
        String sql = PostPreparedSqlBuilder.create(post, TABLE_NAME, UserDao.TABLE_NAME).buildUpdate();
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, post.getTitle());
            statement.setString(2, post.getContent());
            statement.setTimestamp(3, dateTimeMapper.convertTo(LocalDateTime.now()));
            statement.setLong(4, post.getId());
            int result = statement.executeUpdate();
            if (result != 1) {
                throw new DaoException("Could not update post record");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return post;
    }

    @Override
    public int delete(Long id) throws DaoException {
        String sql = String.format("DELETE FROM %s WHERE id=?", tableName());
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            int result = statement.executeUpdate();
            if (result != 1) {
                throw new DaoException("Could not delete post record");
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Post read(Long id) throws RuntimeException {
        String sql = PostPreparedSqlBuilder.create(null, TABLE_NAME, UserDao.TABLE_NAME).buildSelectById();
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            try(ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    return postMapper.convertFrom(result);
                }
                throw new DaoException("Could not find post by id=" + id);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Post> read(int page, int size) throws DaoException {
        page = page > 0 ? page - 1 : 0;
        String sql = PostPreparedSqlBuilder.create(null, TABLE_NAME, UserDao.TABLE_NAME).buildPageSelect();
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, page);
            statement.setLong(2, size);
            try(ResultSet result = statement.executeQuery()) {
                List<Post> listResult = new ArrayList<>();
                while (result.next()) {
                    listResult.add(postMapper.convertFrom(result));
                }
                return listResult;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Post> readAll() throws DaoException {
        String sql = PostPreparedSqlBuilder.create(null, TABLE_NAME, UserDao.TABLE_NAME).buildSelectAll();
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            try(ResultSet result = statement.executeQuery()) {
                List<Post> listResult = new ArrayList<>();
                while (result.next()) {
                    listResult.add(postMapper.convertFrom(result));
                }
                return listResult;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
