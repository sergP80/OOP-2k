package ua.edu.chmnu.fks.oop.recursion;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ua.edu.chmnu.fks.oop.arrays.ArrayUtils;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SimpleRecursionsTest {
    private static final int SMALL_ARRAY_SIZE = 1000;

    private static final int LARGE_ARRAY_SIZE = 10_000_000;

    private static Random rnd = new Random();

    @ParameterizedTest
    @MethodSource("provideRangeFixtures")
    public void shouldSuccessSumCalculation(int lowValue, int highValue) {
        int[] a = ArrayUtils.createArray(lowValue, highValue, SMALL_ARRAY_SIZE, rnd);
        long expectedSum = Arrays.stream(a).sum();
        long actualSum = SimpleRecursions.sum(a);
        assertEquals(expectedSum, actualSum);
    }
    
    @ParameterizedTest
    @MethodSource("provideRangeFixtures")
    public void shouldFailSumCalculationWithStackOverflow(int lowValue, int highValue) {
        int[] a = ArrayUtils.createArray(lowValue, highValue, LARGE_ARRAY_SIZE, rnd);
        assertThrows(StackOverflowError.class, () -> SimpleRecursions.sum(a));
    }
    
    @ParameterizedTest
    @MethodSource("provideRangeFixtures")
    public void shouldSuccessSumCalculationWithTailRecursion(int lowValue, int highValue) {
        int[] a = ArrayUtils.createArray(lowValue, highValue, SMALL_ARRAY_SIZE, rnd);
        long expectedSum = Arrays.stream(a).sum();
        long actualSum = SimpleRecursions.sumTail(a);
        assertEquals(expectedSum, actualSum);
    }
    
    @ParameterizedTest
    @MethodSource("provideRangeFixtures")
    public void shouldFailSumCalculationWithTailRecursion(int lowValue, int highValue) {
        int[] a = ArrayUtils.createArray(lowValue, highValue, LARGE_ARRAY_SIZE, rnd);
       assertThrows(StackOverflowError.class, () -> SimpleRecursions.sumTail(a));
    }
    
    @ParameterizedTest
    @MethodSource("provideRangeFixtures")
    public void shouldSuccessBinarySearch(int lowValue, int highValue) {
        int[] a = ArrayUtils.createArray(lowValue, highValue, LARGE_ARRAY_SIZE, rnd);
        int key = a[rnd.nextInt(a.length)];
        Arrays.sort(a);
        int expectedIndex = Arrays.binarySearch(a, key);
        int actualIndex = SimpleRecursions.binarySearch(a, key);
        assertEquals(expectedIndex, actualIndex);
    }
    
    static Stream<Arguments> provideRangeFixtures() {
        return Stream.of(
            Arguments.of(-100, 100),
            Arguments.of(-200, 100),
            Arguments.of(-1000, 1000),
            Arguments.of(-5000, 3000),
            Arguments.of(-10000, 14000)
        );
    }
}
