/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.edu.chmnu.fks.oop.calculator;

import java.util.Random;
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
    private Calculator calculator;
    private Random random = new Random();
    private static double MAX_TOL = 1e-12;
    
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
        calculator = new CalculatorImpl();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of calc method, of class CalculatorImpl.
     */
    @Test
    public void testSuccessCalc01() {
        CalcOperation op = CalcOperation.ADD;
        double a = random.nextDouble()*101 - 50;
        double b = random.nextDouble()*101 - 50;
        
        double expResult = a + b;
        double result = calculator.calc(a, b, op);
        assertTrue(Math.abs(expResult-result) <= MAX_TOL);
    }
    
}
