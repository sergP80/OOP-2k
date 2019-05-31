/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.edu.chmnu.fks.oop.calculator;

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
public class CalculatorImplTest {
    
    public CalculatorImplTest() {
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

    /**
     * Test of calc method, of class CalculatorImpl.
     */
    @Test
    public void testCalc() {
        System.out.println("calc");
        double a = 0.0;
        double b = 0.0;
        CalcOperation op = null;
        CalculatorImpl instance = new CalculatorImpl();
        double expResult = 0.0;
        double result = instance.calc(a, b, op);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
