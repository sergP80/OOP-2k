package ua.edu.chmnu.fks.oop.design.users.exceptions;

public class UserIOException extends ServiceException {
    public UserIOException() {
    }

    public UserIOException(String message) {
        super(message);
    }

    public UserIOException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserIOException(Throwable cause) {
        super(cause);
    }

    public UserIOException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
