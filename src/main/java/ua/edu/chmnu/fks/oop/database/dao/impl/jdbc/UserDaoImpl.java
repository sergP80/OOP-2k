package ua.edu.chmnu.fks.oop.database.dao.impl.jdbc;

import ua.edu.chmnu.fks.oop.database.dao.UserDao;
import ua.edu.chmnu.fks.oop.database.exceptions.DaoException;
import ua.edu.chmnu.fks.oop.database.mapper.LocalDateMapper;
import ua.edu.chmnu.fks.oop.database.mapper.LocalDateTimeMapper;
import ua.edu.chmnu.fks.oop.database.mapper.UserMapper;
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
        return "social_net.users";
    }

    @Override
    public Connection connection() {
        return this.connection;
    }

    @Override
    public User create(User user) throws DaoException {
        String sql = String.format("INSERT into %s (email, phone, birth_day, address) " +
                                   "VALUES(?, ?, ?, ?)",
                                   tableName()
        );
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
        String sql = String.format("UPDATE %s set email=?, phone=?, birth_day=?, address=?) " +
                                  "WHERE id=?",
                                  tableName()
        );
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
        String sql = String.format("SELECT id as user_id, title as post_title, content as post_content,  created_time as post_created_time, updated_time as post_updated_time FROM %s " +
                        "WHERE id=? LIMIT 1", tableName()
        );
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
        String sql = String.format("SELECT id as user_id, title as post_title, content as post_content,  created_time as post_created_time, updated_time as post_updated_time FROM %s " +
                                    "LIMIT=? OFFSET=?", tableName()
        );
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
        String sql = String.format("SELECT id as user_id, title as post_title, content as post_content,  created_time as post_created_time, updated_time as post_updated_time FROM %s ",
                                    tableName()
        );
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
