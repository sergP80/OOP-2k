package ua.edu.chmnu.fks.oop.database.mapper;

import ua.edu.chmnu.fks.oop.database.model.Quote;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QuoteMapper implements Converter<ResultSet, Quote>{

    private final LocalDateTimeMapper localDateTimeMapper;
    private final UserQuoteMapper userQuoteMapper;
    private final PostMapper postMapper;

    public QuoteMapper(LocalDateTimeMapper localDateTimeMapper, UserQuoteMapper userMapper, PostMapper postMapper) {
        this.localDateTimeMapper = localDateTimeMapper;
        this.userQuoteMapper = userMapper;
        this.postMapper = postMapper;
    }

    @Override
    public Quote convertFrom(ResultSet rowSet) {
        try {
            return Quote.builder()
                    .id(rowSet.getLong("quote_id"))
                    .content(rowSet.getString("quote_content"))
                    .createdTime(localDateTimeMapper.convertFrom(rowSet.getTimestamp("quote_created_time")))
                    .user(userQuoteMapper.convertFrom(rowSet))
                    .post(postMapper.convertFrom(rowSet))
                    .build();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResultSet convertTo(Quote quote) {
        throw new UnsupportedOperationException();
    }
}
