package ua.edu.chmnu.fks.oop.streams.fib;

public class FibApp {
    public static void main(String[] args) {
        new FibStream().stream().limit(40).forEachOrdered(System.out::println);
    }
}
