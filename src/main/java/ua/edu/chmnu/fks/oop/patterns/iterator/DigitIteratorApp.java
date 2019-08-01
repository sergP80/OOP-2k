package ua.edu.chmnu.fks.oop.patterns.iterator;

public class DigitIteratorApp {
    public static void main(String[] args) {
        int a = 1340242;
        IntDigitCollection digitCollection = new IntDigitCollection(a);
        for (byte digit : digitCollection) {
            System.out.print(digit + " ");
        }
    }
}
