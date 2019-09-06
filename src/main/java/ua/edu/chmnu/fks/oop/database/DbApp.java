package ua.edu.chmnu.fks.oop.database;

import ua.edu.chmnu.fks.oop.database.config.AppConfiguration;
import ua.edu.chmnu.fks.oop.database.connection.ConnectionConfig;
import ua.edu.chmnu.fks.oop.database.connection.ConnectionFactory;
import ua.edu.chmnu.fks.oop.database.connection.postgres.PostgresConnectionConfig;
import ua.edu.chmnu.fks.oop.database.dao.PostDao;
import ua.edu.chmnu.fks.oop.database.dao.UserDao;
import ua.edu.chmnu.fks.oop.database.model.Post;
import ua.edu.chmnu.fks.oop.database.model.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;

public class DbApp {
    private static String host = "192.168.0.106";
    private static Integer port = 5433;
    private static String user = "user1";
    private static String password = "password123";
    private static String database = "courses";

    public static void main(String[] args) {

        ConnectionConfig connectionConfig = PostgresConnectionConfig.builder()
                .host(host)
                .port(port)
                .user(user)
                .password(password)
                .database(database)
                .build();

        try(Connection connection = ConnectionFactory.createConnection(connectionConfig)) {
            UserDao userDao = AppConfiguration.getUserDao(connection);
            PostDao postDao = AppConfiguration.getPostDao(connection);

            int r = userDao.deleteAll();
            System.out.println("Deleted " + r + " users");
            User user = User.builder()
                         .email("user1@domain.com")
                         .phone("+380913424512")
                         .birthDay(LocalDate.of(1999, 3, 1))
                         .address("Soborna 1, Nikolayev")
                         .build();
            user = userDao.create(user);
            System.out.println("Inserted user " + user);
            List<Post> posts = Arrays.asList(
              Post
              .builder()
                 .title("Post 1")
                 .content("Content 1")
                 .user(user)
              .build(),

              Post
                .builder()
                    .title("Post 2")
                    .content("Content 2")
                    .user(user)
                .build()
            );

            posts.stream().map(postDao::create).forEach(System.out::println);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
