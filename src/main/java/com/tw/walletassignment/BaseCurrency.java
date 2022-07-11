package com.tw.walletassignment;

public enum BaseCurrency {
    USD(74.85),
    INR(1);

    private final double rate;
    BaseCurrency(double baseCurrencyValue) {
        rate=baseCurrencyValue;
    }
 public double convertToBase(double amount)
 {
     return amount*rate;
 }

    public double convertToPreferredCurrency(double amount) {
        return amount / rate;
    }



}
