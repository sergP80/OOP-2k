package ua.edu.chmnu.fks.oop.design.users.services;

import ua.edu.chmnu.fks.oop.design.users.model.AuthUser;

import java.util.List;

public interface AuthService {

    AuthUser create(AuthUser authUser);

    AuthUser update(AuthUser authUser);

    void delete(AuthUser authUser);

    AuthUser findById(Long id);

    AuthUser findByLogin(String login);

    List<AuthUser> findAll();

    void clearAll();

    long countLoginUsers();

    List<AuthUser> findOldThen(int years);
}
