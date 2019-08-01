package ua.edu.chmnu.fks.oop.numbers;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(JUnitParamsRunner.class)
public class NumberUtilsTest {

    @Test
    @Parameters({"342331", "213543252", "24553525", "3434325"})
    public void testCountDigitsOfInt(String strNumber) {
        int expectedDigits = strNumber.length();
        int number = Integer.parseInt(strNumber);
        int actualDigits = NumberUtils.countDigits(number);
        assertEquals(expectedDigits, actualDigits);
    }

    @Test
    @Parameters({"342333541", "9943882", "4587921", "435427294"})
    public void testCountShiftDigitsOfInt(String strNumber) {
        int expectedDigits = strNumber.length();
        int number = Integer.parseInt(strNumber);
        int actualDigits = NumberUtils.countShiftDigits(number);
        assertEquals(expectedDigits, actualDigits);
    }

    @Test
    public void successNumberToDigitsArray01() {
        int a = 45452342;
        byte[] expectedDigits = {4,5,4,5,2,3,4,2};
        byte[] actualDigits = NumberUtils.numberToDigitsArray(a);
        assertArrayEquals(expectedDigits, actualDigits);
    }
}