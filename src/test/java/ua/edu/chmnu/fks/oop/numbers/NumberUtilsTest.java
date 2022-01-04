package ua.edu.chmnu.fks.oop.numbers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class NumberUtilsTest {

    @ParameterizedTest
    @ValueSource(strings = {"342331", "213543252", "24553525", "3434325"})
    public void shouldSuccessCountDigits(String strNumber) {
        int expectedDigits = strNumber.length();
        int number = Integer.parseInt(strNumber);
        int actualDigits = NumberUtils.countDigits(number);
        assertEquals(expectedDigits, actualDigits);
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"342333541", "9943882", "4587921", "435427294"})
    public void shouldSuccessCountOfDigitsWithShift(String strNumber) {
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
