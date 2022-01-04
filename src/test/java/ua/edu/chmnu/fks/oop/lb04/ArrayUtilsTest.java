/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.edu.chmnu.fks.oop.lb04;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ua.edu.chmnu.fks.oop.arrays.ArrayUtils;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 *
 * @author svpuzyrov
 */
public class ArrayUtilsTest {
    
    public ArrayUtilsTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }
    
    @ParameterizedTest
    @MethodSource("provideValidFixturesForMaxAbs")
    public void shouldSuccessFindMaxValueByItsModule(double[] source, double expectedResult) {
        assertEquals(expectedResult, ArrayUtils.maxAbs(source), 1e-6);
    }
    
    static Stream<Arguments> provideValidFixturesForMaxAbs() {
        return Stream.of(
          Arguments.of(new double[]{-1, 2, 10, -11, 12}, 12),
          Arguments.of(new double[]{-4, 5, 10, -15, 14}, -15)
        );
    }
    
    @Test
    public void shouldFailFindMaxByModuleWhenSourceIsEmpty() {
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> ArrayUtils.maxAbs(new double[0]));
    }

    @ParameterizedTest
    @MethodSource("provideIntArrayFixturesForReverse")
    public void shouldSuccessReverseIntArray(int[] source) {
        int[] temp = new int[source.length];
        System.arraycopy(source, 0, temp, 0, source.length);
        ArrayUtils.reverse(temp);
        ArrayUtils.reverse(temp);
        assertArrayEquals(source, temp);
    }
    
    static Stream<Arguments> provideIntArrayFixturesForReverse() {
        return Stream.of(
            Arguments.of(new int[]{106,187,146,44,144,153,129,216,87,206,97,213,165}),
            Arguments.of(new int[]{-50,-1,25,-30,-7,-9,-37,30,-85,56,33,-102,-1,-122,3,-131,19,-8,-95,-116})
        );
    }
}
