package ua.edu.chmnu.fks.oop.design.users.exceptions;

public class IllegalUserFormatException extends UserIOException {
    public IllegalUserFormatException() {
    }

    public IllegalUserFormatException(String message) {
        super(message);
    }

    public IllegalUserFormatException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalUserFormatException(Throwable cause) {
        super(cause);
    }

    public IllegalUserFormatException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
