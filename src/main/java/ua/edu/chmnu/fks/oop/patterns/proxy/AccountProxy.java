package ua.edu.chmnu.fks.oop.patterns.proxy;

public class AccountProxy implements Account {
    private static final long MAX_AMOUNT = 2_000_000_000L;
    private final Account account;

    public AccountProxy(Account account) {
        this.account = account;
    }

    @Override
    public long amount() {
        long amount = account.amount();
        if (amount >= MAX_AMOUNT) {
            throw new LargeAmountException(amount);
        }
        return amount;
    }
}
