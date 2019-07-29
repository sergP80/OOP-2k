/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.edu.chmnu.fks.oop.calculator;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CalcOperation {
    UNKNOWN(0), ADD(1), SUBS(1), MUL(2), DIV(2), MOD(2), POW(3);
    
    private int priority;
}
