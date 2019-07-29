package ua.edu.chmnu.fks.oop.repository.errors;

public class EmptyRepositoryException extends RuntimeException {
    public EmptyRepositoryException() {
    }

    public EmptyRepositoryException(String message) {
        super(message);
    }

    public EmptyRepositoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmptyRepositoryException(Throwable cause) {
        super(cause);
    }
}
