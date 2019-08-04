package ua.edu.chmnu.fks.oop.design.users.dao;

import ua.edu.chmnu.fks.oop.design.users.model.AuthUser;

import java.util.List;

public interface AuthUserRepository {
    AuthUser create(AuthUser authUser);

    AuthUser update(AuthUser authUser);

    void delete(AuthUser authUser);

    void deleteAll();

    AuthUser getById(Long id);

    AuthUser getByLogin(String login);

    List<AuthUser> getAll();
}
