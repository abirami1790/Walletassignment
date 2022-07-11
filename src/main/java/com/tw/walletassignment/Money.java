package com.tw.walletassignment;
import com.tw.walletassignment.exceptions.InsufficientBalanceException;
import com.tw.walletassignment.exceptions.InvalidAmountException;
import java.util.Objects;
public class Money {
    private double amount;
    private final BaseCurrency currency;
    public Money(double amount, BaseCurrency currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public static Money baseValue(BaseCurrency currency)  {
        return new Money(0,currency);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return Double.compare(money.amount, amount) == 0 ;
    }
    @Override
    public int hashCode() {
        return Objects.hash(amount, currency);
    }
    public void add(Money putMoney) throws InvalidAmountException {
        if (putMoney.amount < 0)
            throw new InvalidAmountException();
        this.amount = roundOff(this.amount + putMoney.currency.convertToBase(putMoney.amount));
    }


    public void deduct(Money withdraw) throws InvalidAmountException, InsufficientBalanceException {
        if (withdraw.amount < 0)
            throw new InvalidAmountException();
        double amountInBaseCurrency = withdraw.currency.convertToBase(withdraw.amount);
        if (this.amount < amountInBaseCurrency)
            throw new InsufficientBalanceException();
        this.amount = roundOff(this.amount - withdraw.currency.convertToBase(withdraw.amount));
    }

    public double balanceInPreferredCurrency() {
        double balanceInPreferredCurrency = currency.convertToPreferredCurrency(this.amount);
        return roundOff(balanceInPreferredCurrency);
    }
    public double roundOff(double amount) {
        return Math.round(amount * 100.0) / 100.0;
    }

}
