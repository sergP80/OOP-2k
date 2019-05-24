/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.edu.chmnu.fks.oop.lb04;

import org.junit.*;

import static org.junit.Assert.*;
import static ua.edu.chmnu.fks.oop.lb04.ArrayUtils.reverse;

/**
 *
 * @author svpuzyrov
 */
public class ArrayUtilsTest {
    
    public ArrayUtilsTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void test01() {
        double[] a = {-1, 2, 10, -11, 12};
        double m = 12;
        double r = ArrayUtils.maxAbs(a);
        assertEquals(m, r, 1e-6);
    }
    
    @Test
    public void test02() {
        double[] a = {-1, 2, 10, -11, 12};
        double m = 11;
        double r = ArrayUtils.maxAbs(a);
        assertNotEquals(m, r);
    }
    
    @Test
    public void test03() {
        int[] a = {-4, 5, 10, -15, 14};
        int m = -15;
        int r = ArrayUtils.maxAbs(a);
        assertEquals(m, r);
    }
    
    @Test
    public void test04() {
        int[] a = {-4, 5, 10, -15, 14};
        int m = 14;
        int r = ArrayUtils.maxAbs(a);
        assertNotEquals(m, r);
    }
    
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void test06() {
        double[] a = new double[0];
        double m = -11;
        double r = ArrayUtils.maxAbs(a);
        assertEquals(m, r);
    }

    @Test
    public void reverseArrayUtilTest01() {
        int[] originalArray = {106,187,146,44,144,153,129,216,87,206,97,213,165};
        int[] expectedReverseArray = {165,213,97,206,87,216,129,153,144,44,146,187,106};
        reverse(originalArray);
        Assert.assertArrayEquals(expectedReverseArray, originalArray);
    }

    @Test
    public void reverseArrayUtilTest02() {
        int[] originalArray = {-50,-1,25,-30,-7,-9,-37,30,-85,56,33,-102,-1,-122,3,-131,19,-8,-95,-116};
        int[] expectedReverseArray = {-116,-95,-8,19,-131,3,-122,-1,-102,33,56,-85,30,-37,-9,-7,-30,25,-1,-50};
        reverse(originalArray);
        Assert.assertArrayEquals(expectedReverseArray, originalArray);
    }
}
