package ua.edu.chmnu.fks.oop.database.dao.impl.jdbc;

import ua.edu.chmnu.fks.oop.database.dao.UserDao;
import ua.edu.chmnu.fks.oop.database.exceptions.DaoException;
import ua.edu.chmnu.fks.oop.database.mapper.LocalDateMapper;
import ua.edu.chmnu.fks.oop.database.mapper.LocalDateTimeMapper;
import ua.edu.chmnu.fks.oop.database.mapper.UserMapper;
import ua.edu.chmnu.fks.oop.database.mapper.builders.sql.UserPreparedSqlBuilder;
import ua.edu.chmnu.fks.oop.database.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends GenericDaoImpl<User, Long> implements UserDao {

    private final Connection connection;
    private final UserMapper userMapper;
    private final LocalDateTimeMapper dateTimeMapper;
    private final LocalDateMapper dateMapper;

    public UserDaoImpl(Connection connection, UserMapper userMapper, LocalDateTimeMapper dateTimeMapper, LocalDateMapper dateMapper) {
        this.connection = connection;
        this.userMapper = userMapper;
        this.dateTimeMapper = dateTimeMapper;
        this.dateMapper = dateMapper;
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
    public User create(User user) throws DaoException {
        String sql = UserPreparedSqlBuilder.create(user, TABLE_NAME).buildInsert();
        try(PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPhone());
            statement.setTimestamp(3, dateMapper.convertTo(user.getBirthDay()));
            statement.setString(4, user.getAddress());
            int result = statement.executeUpdate();
            if (result != 1) {
                throw new DaoException("Could not inserted user record");
            }
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    user.setId(generatedKeys.getLong(1));
                } else {
                    throw new DaoException("Could not insert user record with generation key");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    @Override
    public User update(User user) throws DaoException {
        String sql = UserPreparedSqlBuilder.create(user, TABLE_NAME).buildUpdate();

        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPhone());
            statement.setTimestamp(3, dateMapper.convertTo(user.getBirthDay()));
            statement.setString(4, user.getAddress());
            statement.setLong(5, user.getId());
            int result = statement.executeUpdate();
            if (result != 1) {
                throw new DaoException("Could not update user record");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    @Override
    public int delete(Long id) throws DaoException {
        String sql = String.format("DELETE FROM %s WHERE id=?", tableName());
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            int result = statement.executeUpdate();
            if (result != 1) {
                throw new DaoException("Could not delete user record");
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User read(Long id) throws RuntimeException {
        String sql = UserPreparedSqlBuilder.create(null, TABLE_NAME).buildSelectById();
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            try(ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    return userMapper.convertFrom(result);
                }
                throw new DaoException("Could not find post by id=" + id);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> read(int page, int size) throws DaoException {
        page = page > 0 ? page - 1 : 0;
        String sql = UserPreparedSqlBuilder.create(null, TABLE_NAME).buildPageSelect();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, page);
            statement.setLong(2, size);
            try(ResultSet result = statement.executeQuery()) {
                List<User> listResult = new ArrayList<>();
                while (result.next()) {
                    listResult.add(userMapper.convertFrom(result));
                }
                return listResult;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> readAll() throws DaoException {
        String sql = UserPreparedSqlBuilder.create(null, TABLE_NAME).buildSelectAll();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            try(ResultSet result = statement.executeQuery()) {
                List<User> listResult = new ArrayList<>();
                while (result.next()) {
                    listResult.add(userMapper.convertFrom(result));
                }
                return listResult;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
