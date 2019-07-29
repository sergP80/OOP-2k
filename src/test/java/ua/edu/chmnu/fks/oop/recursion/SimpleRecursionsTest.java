package ua.edu.chmnu.fks.oop.recursion;


import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import ua.edu.chmnu.fks.oop.arrays.ArrayUtils;

import java.util.Arrays;
import java.util.Random;

@RunWith(JUnitParamsRunner.class)
public class SimpleRecursionsTest {
    private static final int SMALL_ARRAY_SIZE = 1000;

    private static final int LARGE_ARRAY_SIZE = 10_000_000;

    private static Random rnd = new Random();

    @Test
    @Parameters({"-100, 100", "-200, 100", "-1000, 1000", "-5000, 3000", "-10000, 14000"})
    public void successSumCalc(int lowValue, int highValue) {
        int[] a = ArrayUtils.createArray(lowValue, highValue, SMALL_ARRAY_SIZE, rnd);
        long expectedSum = Arrays.stream(a).sum();
        long actualSum = SimpleRecursions.sum(a);
        Assert.assertEquals(expectedSum, actualSum);
    }

    @Test(expected = StackOverflowError.class)
    @Parameters({"-100, 100", "-200, 100", "-1000, 1000", "-5000, 3000", "-10000, 14000"})
    public void failWithStackOverflowSumCalc(int lowValue, int highValue) {
        int[] a = ArrayUtils.createArray(lowValue, highValue, LARGE_ARRAY_SIZE, rnd);
        long expectedSum = Arrays.stream(a).sum();
        long actualSum = SimpleRecursions.sum(a);
        Assert.assertEquals(expectedSum, actualSum);
    }

    @Test
    @Parameters({"-100, 100", "-200, 100", "-1000, 1000", "-5000, 3000", "-10000, 14000"})
    public void successSumWithTailRecursion(int lowValue, int highValue) {
        int[] a = ArrayUtils.createArray(lowValue, highValue, SMALL_ARRAY_SIZE, rnd);
        long expectedSum = Arrays.stream(a).sum();
        long actualSum = SimpleRecursions.sumTail(a);
        Assert.assertEquals(expectedSum, actualSum);
    }

    @Test(expected = StackOverflowError.class)
    @Parameters({"-100, 100", "-200, 100", "-1000, 1000", "-5000, 3000", "-10000, 14000"})
    public void failSumWithTailRecursionStackOverflow(int lowValue, int highValue) {
        int[] a = ArrayUtils.createArray(lowValue, highValue, LARGE_ARRAY_SIZE, rnd);
        long expectedSum = Arrays.stream(a).sum();
        long actualSum = SimpleRecursions.sumTail(a);
        Assert.assertEquals(expectedSum, actualSum);
    }

    @Test
    @Parameters({"-100, 100", "-200, 100", "-1000, 1000", "-5000, 3000", "-10000, 14000"})
    public void successBinarySearch(int lowValue, int highValue) {
        int[] a = ArrayUtils.createArray(lowValue, highValue, LARGE_ARRAY_SIZE, rnd);
        int key = a[rnd.nextInt(a.length)];
        Arrays.sort(a);
        int expectedIndex = Arrays.binarySearch(a, key);
        int actualIndex = SimpleRecursions.binarySearch(a, key);
        Assert.assertEquals(expectedIndex, actualIndex);
    }
}