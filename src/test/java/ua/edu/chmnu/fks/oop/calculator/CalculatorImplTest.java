package ua.edu.chmnu.fks.oop.calculator;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CalculatorImplTest {
    private Calculator calculator;
    private final Random random = new Random();
    private static final double MAX_TOL = 1e-12;
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        calculator = new CalculatorImpl();
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of calc method, of class CalculatorImpl.
     */
    @ParameterizedTest
    @EnumSource(names = {"ADD", "SUBS", "MUL"})
    public void shouldSuccessExecuteOperations(CalcOperation op) {
        double a = random.nextDouble()*101 - 50;
        double b = random.nextDouble()*101 - 50;
        
        double expectedResult;
        switch (op) {
            case ADD:
                expectedResult = a + b;
                break;
            case SUBS:
                expectedResult = a - b;
                break;
            case MUL:
                expectedResult = a * b;
                break;
            default:
                throw new IllegalArgumentException("Illegal test operation argument");
        }
        
        double actualResult = calculator.calc(a, b, op);
        assertTrue(Math.abs(expectedResult-actualResult) <= MAX_TOL);
    }
    
}
