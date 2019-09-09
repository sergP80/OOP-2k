package ua.edu.chmnu.fks.oop.database.dao;

import ua.edu.chmnu.fks.oop.database.model.Post;

public interface PostDao extends GenericDao<Post, Long> {
    String TABLE_NAME = "social_net.posts";
}
