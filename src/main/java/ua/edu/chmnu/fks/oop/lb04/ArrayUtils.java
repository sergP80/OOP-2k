/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.edu.chmnu.fks.oop.lb04;

import java.util.Random;

public class ArrayUtils {
    public static int[] createArray(int low, int high, int sz, Random rnd) {
        int[] arr = new int[sz];
        for(int i = 0; i < arr.length; ++i) {
            arr[i] = low + rnd.nextInt(high + 1);
        }
        return arr;
    }

    public static void printArray(int[] arr, String msg) {
        if (arr == null || arr.length == 0) {
            return;
        }

        if (msg != null && !msg.isEmpty()) {
            System.out.println(msg);
        }
        System.out.print("[");
        for (int i = 0; i < arr.length; ++i) {
            if (i > 0) {
                System.out.print(",");
            }
            System.out.print(arr[i]);
        }
        System.out.println("]");
    }

    public static void reverse(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }

        for (int i = 0, j = arr.length - 1;  i < j; ++i, --j) {
            int t = arr[i];
            arr[i] = arr[j];
            arr[j] = t;
        }
    }

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
