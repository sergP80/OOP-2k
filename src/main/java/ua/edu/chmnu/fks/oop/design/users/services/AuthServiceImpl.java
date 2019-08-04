package ua.edu.chmnu.fks.oop.design.users.services;

import ua.edu.chmnu.fks.oop.design.users.dao.AuthUserRepository;
import ua.edu.chmnu.fks.oop.design.users.model.AuthUser;

import java.util.List;
import java.util.stream.Collectors;

public class AuthServiceImpl implements AuthService {
    private final AuthUserRepository authUserRepository;

    public AuthServiceImpl(AuthUserRepository authUserRepository) {
        this.authUserRepository = authUserRepository;
    }

    @Override
    public AuthUser create(AuthUser authUser) {
        return authUserRepository.create(authUser);
    }

    @Override
    public AuthUser update(AuthUser authUser) {
        return authUserRepository.update(authUser);
    }

    @Override
    public void delete(AuthUser authUser) {
        authUserRepository.delete(authUser);
    }

    @Override
    public AuthUser findById(Long id) {
        return authUserRepository.getById(id);
    }

    @Override
    public AuthUser findByLogin(String login) {
        return authUserRepository.getByLogin(login);
    }

    @Override
    public List<AuthUser> findAll() {
        return authUserRepository.getAll();
    }

    @Override
    public void clearAll() {
        authUserRepository.deleteAll();
    }

    @Override
    public long countLoginUsers() {
        return authUserRepository.getAll().stream()
                .filter(AuthUser::isLogged)
                .count();
    }

    @Override
    public List<AuthUser> findOldThen(int years) {
        return authUserRepository.getAll().stream()
                .filter(au -> au.getPersonalInformation().age() > years)
                .collect(Collectors.toList());
    }
}
