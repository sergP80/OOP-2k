package ua.edu.chmnu.fks.oop.database.dao;

import ua.edu.chmnu.fks.oop.database.model.User;

public interface UserDao extends GenericDao<User, Long> {
    String TABLE_NAME = "social_net.users";
}
