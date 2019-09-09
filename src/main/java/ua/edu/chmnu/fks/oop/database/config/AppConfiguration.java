package ua.edu.chmnu.fks.oop.database.config;

import ua.edu.chmnu.fks.oop.database.dao.PostDao;
import ua.edu.chmnu.fks.oop.database.dao.UserDao;
import ua.edu.chmnu.fks.oop.database.dao.impl.jdbc.PostDaoImpl;
import ua.edu.chmnu.fks.oop.database.dao.impl.jdbc.UserDaoImpl;
import ua.edu.chmnu.fks.oop.database.mapper.LocalDateMapper;
import ua.edu.chmnu.fks.oop.database.mapper.LocalDateTimeMapper;
import ua.edu.chmnu.fks.oop.database.mapper.PostMapper;
import ua.edu.chmnu.fks.oop.database.mapper.UserMapper;

import java.sql.Connection;

public class AppConfiguration {
    private static LocalDateTimeMapper localDateTimeMapper;
    private static LocalDateMapper localDateMapper;
    private static PostMapper postMapper;
    private static UserMapper userMapper;

    static {
        localDateTimeMapper = new LocalDateTimeMapper();
        localDateMapper = new LocalDateMapper();
        userMapper = new UserMapper(localDateTimeMapper, localDateMapper);
        postMapper = new PostMapper(localDateTimeMapper, userMapper);
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
}
