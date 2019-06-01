/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.edu.chmnu.fks.oop.arrays;

/**
 *
 * @author svpuzyrov
 */
public class ArraysApp {
     public static void main(String[] args) {
         int[] a = {-1, 2, 5, -7};
         int m = ArrayUtils.maxAbs(a);
         System.out.println("Abs max " + m);
    }
}
