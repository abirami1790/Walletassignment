package com.tw.walletassignment;
import com.tw.walletassignment.exceptions.InsufficientBalanceException;
import com.tw.walletassignment.exceptions.InvalidAmountException;

public class Wallet {

    private final Money amountValue;

    public Wallet() {
        amountValue = Money.baseValue(BaseCurrency.INR);
    }



    public void putMoney(Money putMoney) throws InvalidAmountException {
        amountValue.add(putMoney);

    }
    public void withdrawMoney(Money withdraw) throws InsufficientBalanceException, InvalidAmountException {
        amountValue.deduct(withdraw);

    }
    public double balanceInPreferredCurrency(BaseCurrency currency) {
        return currency.convertToPreferredCurrency(amountValue.balanceInPreferredCurrency());
    }

    public double balanceInBaseCurrency() {
        return amountValue.balanceInPreferredCurrency();
    }


}
