package ua.edu.chmnu.fks.oop;

import java.util.Random;
import java.util.Scanner;

import static ua.edu.chmnu.fks.oop.lb04.ArrayUtils.*;

public class MainApp {

    private static Random rnd = new Random();

    public static void main(String[] args) {
        try(Scanner in = new Scanner(System.in)) {
            System.out.print("Enter [a, b]: ");
            int a = in.nextInt();
            int b = in.nextInt();
            int sz = rnd.nextInt(11) + 10;
            int[] arr = createArray(a, b, sz, rnd);
            printArray(arr, "Original array");
            reverse(arr);
            printArray(arr, "Reverse array");
        }
    }
}
