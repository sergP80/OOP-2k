package ua.edu.chmnu.fks.oop.design.users.exceptions;

public class UserNotFoundException extends ServiceException {
    private final static String DEFAULT_MSG_PREFIX = "Can't find user with";

    public UserNotFoundException() {
    }

    public UserNotFoundException(Long id) {
        super(DEFAULT_MSG_PREFIX + " ID " + id);
    }

    public UserNotFoundException(String login, String... messages) {
        super(DEFAULT_MSG_PREFIX + " login " + login + String.join(" ", messages));
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotFoundException(Throwable cause) {
        super(cause);
    }

    public UserNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
