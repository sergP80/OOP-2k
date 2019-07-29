package ua.edu.chmnu.fks.oop.calculator;

import java.util.Scanner;

public class Lab02 {

    public static void main(String[] args) {
        String s = "Enter a op b: ";
        System.out.print(s);
        try(Scanner in = new Scanner(System.in);) {
            String line = in.nextLine();
            CalculatorData data = CalculatorData.parse(line);
            Calculator calculator = new CalculatorImpl();
            double r = calculator.calc(data.getA(), data.getB(), data.getOp());
            System.out.println("Result=" + r);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
