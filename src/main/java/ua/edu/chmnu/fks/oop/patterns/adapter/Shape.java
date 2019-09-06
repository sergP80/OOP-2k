package ua.edu.chmnu.fks.oop.patterns.adapter;

@FunctionalInterface
public interface Shape {

    long area();

    default long perimeter() {
        return 0L;
    }
}
