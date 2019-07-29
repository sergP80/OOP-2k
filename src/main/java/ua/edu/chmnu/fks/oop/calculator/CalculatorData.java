/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.edu.chmnu.fks.oop.calculator;

import java.util.Scanner;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CalculatorData {

    private double a;
    private double b;
    private CalcOperation op = CalcOperation.UNKNOWN;

    private static CalcOperation parseOp(String c) {
        switch (c) {
            case "+":
                return CalcOperation.ADD;
            case "-":
                return CalcOperation.SUBS;
            case "*":
                return CalcOperation.MUL;
            case "/":
                return CalcOperation.DIV;
            case "%":
                return CalcOperation.MOD;
            case "^":
                return CalcOperation.POW;    
            default:
                throw new UnsupportedOperationException("Unknown operation " + c);
        }
    }

    public static CalculatorData parse(String s) {
        try (Scanner in = new Scanner(s)) {
            return CalculatorData
                    .builder()
                    .a(in.nextDouble())
                    .op(parseOp(in.next()))
                    .b(in.nextDouble())
                    .build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
