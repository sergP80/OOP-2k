package ua.edu.chmnu.fks.oop.database;

import ua.edu.chmnu.fks.oop.database.config.AppConfiguration;
import ua.edu.chmnu.fks.oop.database.connection.ConnectionConfig;
import ua.edu.chmnu.fks.oop.database.connection.ConnectionFactory;
import ua.edu.chmnu.fks.oop.database.connection.postgres.ConnectionConfigImpl;
import ua.edu.chmnu.fks.oop.database.dao.PostDao;
import ua.edu.chmnu.fks.oop.database.dao.UserDao;
import ua.edu.chmnu.fks.oop.database.model.Post;
import ua.edu.chmnu.fks.oop.database.model.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class DbApp {

    public static void main(String[] args) {

        try(Connection connection = ConnectionFactory.createConnection()) {
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

            System.out.println("======>> Creation user with posts");
            Long postId = posts.stream()
                    .map(postDao::create)
                    .map(Post::getId)
                    .findAny()
                    .get();

            System.out.println("======>> Select post with id " + postId);
            Post postById = postDao.read(postId);
            System.out.println("Found " + postById);

            System.out.println("======>> Updated post with id " + postId);
            postById.setTitle("New title " + postId);
            postById = postDao.update(postById);
            System.out.println(postById);

            System.out.println("======>> Select page from 10 posts");
            postDao.read(1, 10).forEach(System.out::println);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
