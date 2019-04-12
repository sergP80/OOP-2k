/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.edu.chmnu.fks.oop.lb04;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

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
}
