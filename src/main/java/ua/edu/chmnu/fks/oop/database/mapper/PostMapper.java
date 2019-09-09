package ua.edu.chmnu.fks.oop.database.mapper;

import ua.edu.chmnu.fks.oop.database.model.Post;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PostMapper implements Converter<ResultSet, Post>{

    private final LocalDateTimeMapper localDateTimeMapper;
    private final UserMapper userMapper;

    public PostMapper(LocalDateTimeMapper localDateTimeMapper, UserMapper userMapper) {
        this.localDateTimeMapper = localDateTimeMapper;
        this.userMapper = userMapper;
    }

    @Override
    public Post convertFrom(ResultSet rowSet) {
        try {
            return Post.builder()
                    .id(rowSet.getLong("post_id"))
                    .title(rowSet.getString("post_title"))
                    .content(rowSet.getString("post_content"))
                    .createdTime(localDateTimeMapper.convertFrom(rowSet.getTimestamp("post_created_time")))
                    .updatedTime(localDateTimeMapper.convertFrom(rowSet.getTimestamp("post_updated_time")))
                    .user(userMapper.convertFrom(rowSet))
                    .build();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResultSet convertTo(Post post) {
        throw new UnsupportedOperationException();
    }
}
