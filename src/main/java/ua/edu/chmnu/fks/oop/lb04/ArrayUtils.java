/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.edu.chmnu.fks.oop.lb04;

/**
 *
 * @author svpuzyrov
 */
public class ArrayUtils {
    public static int maxAbs(int[] a) {
        int max = a[0];
        for (int i = 1; i < a.length; ++ i) {
            if (Math.abs(a[i]) > Math.abs(max)) {
                max = a[i];
            }
        }
        return max;
    }
    
     public static double maxAbs(double[] a) {
        double max = a[0];
        for (int i = 1; i < a.length; ++ i) {
            if (Math.abs(a[i]) > Math.abs(max)) {
                max = a[i];
            }
        }
        return max;
    }
}
