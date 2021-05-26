package ua.edu.chmnu.fks.oop.base64;

public class Base64Exception extends RuntimeException {
    public Base64Exception() {
    }
    
    public Base64Exception(String message) {
        super(message);
    }
    
    public Base64Exception(String message, Throwable cause) {
        super(message, cause);
    }
}
