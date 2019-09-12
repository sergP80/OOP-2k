package ua.edu.chmnu.fks.oop.database.mapper;

import ua.edu.chmnu.fks.oop.database.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserQuoteMapper implements Converter<ResultSet, User> {

    private final LocalDateTimeMapper localDateTimeMapper;
    private final LocalDateMapper localDateMapper;

    public UserQuoteMapper(LocalDateTimeMapper localDateTimeMapper, LocalDateMapper localDateMapper) {
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
                    .id(rowSet.getLong("quote_user_id"))
                    .email(rowSet.getString("quote_user_email"))
                    .phone(rowSet.getString("quote_user_phone"))
                    .address(rowSet.getString("quote_user_address"))
                    .birthDay(localDateMapper.convertFrom(rowSet.getTimestamp("quote_user_birth_day")))
                    .build();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
