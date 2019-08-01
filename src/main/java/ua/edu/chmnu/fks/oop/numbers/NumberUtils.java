package ua.edu.chmnu.fks.oop.numbers;

public class NumberUtils {
    private NumberUtils() {}

    public static int countDigits(int number) {
        int result = 0;
        for (;number > 0; ++result) {
            number /= 10;
        }
        return result;
    }

    public static int countShiftDigits(int number) {
        int result = 0;
        for (int match = 0; number > match; ++result) {
            match = (match << 3) + (match << 1) + 9;
        }
        return result;
    }

    public static byte[] numberToDigitsArray(int number) {
        int countOfDigits = countDigits(number);
        byte[] digits = new byte[countOfDigits];
        for (int i = digits.length - 1; i >=0; number /= 10, --i){
            digits[i] = (byte)(number % 10);
        }
        return digits;
    }
}
