package ua.edu.chmnu.fks.oop.patterns.iterator;

import ua.edu.chmnu.fks.oop.numbers.NumberUtils;

import java.util.Iterator;

public class IntDigitCollection implements Iterable<Byte> {
    private int number;
    public IntDigitCollection(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    private class IntDigitIterator implements Iterator<Byte> {

        private byte[] digits;

        private int currentDigitIndex = 0;

        public IntDigitIterator(int number) {
            this.digits = NumberUtils.numberToDigitsArray(number);
        }

        @Override
        public boolean hasNext() {
            return digits != null && digits.length > 0 && currentDigitIndex < digits.length;
        }

        @Override
        public Byte next() {
            return digits[currentDigitIndex++];
        }
    }

    @Override
    public Iterator<Byte> iterator() {
        return new IntDigitIterator(this.number);
    }
}
