package ua.edu.chmnu.fks.oop.patterns.facade;

public interface UserDao extends BasicDao<User> {

    void updateOrderCount(User user, int count);
}
