/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.edu.chmnu.fks.oop.calculator;

public class CalculatorImpl implements Calculator{

    @Override
    public double calc(double a, double b, CalcOperation op) {
        double r;
        switch(op) {
            case ADD:
                r = a + b;
                break;
            case SUBS:
                r = a - b;
                break;
            case MUL:
                r = a*b;
                break;
            case DIV :
                r = a/b;
                break;
            case MOD:
                r = a % b;
                break;
            case POW:
                r = Math.pow(a, b);
                break;    
            default:
                throw new UnsupportedOperationException("Unsupported operation " + op);
        }
        
        return r;
    }
}
