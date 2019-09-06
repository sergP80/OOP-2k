package ua.edu.chmnu.fks.oop.patterns.proxy;

import lombok.Getter;

@Getter
public class LargeAmountException extends RuntimeException {
    private long amount;

    public LargeAmountException(long amount) {
        super(String.format("To large amount [%d]", amount));
        this.amount = amount;
    }
}
