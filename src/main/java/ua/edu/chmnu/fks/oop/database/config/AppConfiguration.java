package ua.edu.chmnu.fks.oop.database.config;

import ua.edu.chmnu.fks.oop.database.dao.PostDao;
import ua.edu.chmnu.fks.oop.database.dao.QuoteDao;
import ua.edu.chmnu.fks.oop.database.dao.UserDao;
import ua.edu.chmnu.fks.oop.database.dao.impl.jdbc.PostDaoImpl;
import ua.edu.chmnu.fks.oop.database.dao.impl.jdbc.QuoteDaoImpl;
import ua.edu.chmnu.fks.oop.database.dao.impl.jdbc.UserDaoImpl;
import ua.edu.chmnu.fks.oop.database.mapper.*;

import java.sql.Connection;

public class AppConfiguration {
    private static LocalDateTimeMapper localDateTimeMapper;
    private static LocalDateMapper localDateMapper;
    private static PostMapper postMapper;
    private static UserMapper userMapper;
    private static UserQuoteMapper userQuoteMapper;
    private static QuoteMapper quoteMapper;

    static {
        localDateTimeMapper = new LocalDateTimeMapper();
        localDateMapper = new LocalDateMapper();
        userMapper = new UserMapper(localDateTimeMapper, localDateMapper);
        postMapper = new PostMapper(localDateTimeMapper, userMapper);
        userQuoteMapper = new UserQuoteMapper(localDateTimeMapper, localDateMapper);
        quoteMapper = new QuoteMapper(localDateTimeMapper, userQuoteMapper, postMapper);

    }
    public static LocalDateTimeMapper localDateTimeMapper() {
        return localDateTimeMapper;
    }

    public static PostMapper postMapper() {
        return postMapper;
    }

    public static UserMapper userMapper() {
        return userMapper;
    }

    public static PostDao getPostDao(Connection connection) {
        return new PostDaoImpl(connection, postMapper, localDateTimeMapper);
    }

    public static UserDao getUserDao(Connection connection) {
        return new UserDaoImpl(connection, userMapper, localDateTimeMapper, localDateMapper);
    }

    public static QuoteDao getQuoteDao(Connection connection) {
        return new QuoteDaoImpl(connection, quoteMapper, localDateTimeMapper);
    }
}
