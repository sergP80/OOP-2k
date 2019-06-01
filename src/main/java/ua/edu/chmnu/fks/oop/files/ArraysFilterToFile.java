/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.edu.chmnu.fks.oop.files;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import ua.edu.chmnu.fks.oop.arrays.ArrayUtils;

/**
 *
 * @author svpuzyrov
 */
public class ArraysFilterToFile {
    private static Random random = new Random();
    
    public static void main(String[] args) {
        int[] array = ArrayUtils.createArray(-100, 100, 50, random);
        
    }
}
