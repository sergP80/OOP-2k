package ua.edu.chmnu.fks.oop.database.mapper;

import ua.edu.chmnu.fks.oop.database.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements Converter<ResultSet, User> {

    private final LocalDateTimeMapper localDateTimeMapper;
    private final LocalDateMapper localDateMapper;

    public UserMapper(LocalDateTimeMapper localDateTimeMapper, LocalDateMapper localDateMapper) {
        this.localDateTimeMapper = localDateTimeMapper;
        this.localDateMapper = localDateMapper;
    }

    @Override
    public ResultSet convertTo(User user) throws RuntimeException {
       throw new UnsupportedOperationException();
    }

    @Override
    public User convertFrom(ResultSet rowSet) throws RuntimeException {
        try {
            return User.builder()
                    .id(rowSet.getLong("user_id"))
                    .email(rowSet.getString("user_email"))
                    .phone(rowSet.getString("user_phone"))
                    .address(rowSet.getString("user_address"))
                    .birthDay(localDateMapper.convertFrom(rowSet.getTimestamp("user_birth_day")))
                    .build();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
