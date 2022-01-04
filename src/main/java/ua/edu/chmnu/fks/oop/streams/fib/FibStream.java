package ua.edu.chmnu.fks.oop.streams.fib;

import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class FibStream {
    private long last = 0;
    private long next = 1;
    
    public LongStream stream() {
        return LongStream.generate(this::step);
    }
    
    private long step() {
        long temp = last;
        last = next;
        next = temp + next;
        return temp;
    }
}
