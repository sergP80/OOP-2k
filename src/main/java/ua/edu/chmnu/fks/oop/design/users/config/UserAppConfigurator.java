package ua.edu.chmnu.fks.oop.design.users.config;

import ua.edu.chmnu.fks.oop.design.users.dao.AuthUserRepository;
import ua.edu.chmnu.fks.oop.design.users.dao.impl.AuthUserRepositoryFileImpl;
import ua.edu.chmnu.fks.oop.design.users.services.AuthService;
import ua.edu.chmnu.fks.oop.design.users.services.AuthServiceImpl;

public class UserAppConfigurator {
    private UserAppConfigurator() {}

    private static final String authUserFileName = "auth-users.dat";

    public static AuthService configureAuthUserService() {
        AuthUserRepository authUserRepository = new AuthUserRepositoryFileImpl(authUserFileName);
        AuthService authService = new AuthServiceImpl(authUserRepository);
        return authService;
    }
}
