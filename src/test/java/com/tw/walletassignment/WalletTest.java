package com.tw.walletassignment;
import com.tw.walletassignment.exceptions.InvalidAmountException;
import com.tw.walletassignment.exceptions.InsufficientBalanceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class WalletTest {

    private Wallet wallet =null;
    @BeforeEach
    void beforeEach() {
        wallet = new Wallet();
    }
    @Test
    void balanceShouldBeINR100WhenINR100IsDeposited() throws InvalidAmountException {
        Money deposit = new Money(100, BaseCurrency.INR);
        wallet.putMoney(deposit);
        assertEquals(100,wallet.balanceInBaseCurrency());
    }
    @Test
    void balanceShouldBeINR374RsAnd25pWhenUSD5IsDeposited() throws InvalidAmountException {
        Money deposit = new Money(5, BaseCurrency.USD);
        wallet.putMoney(deposit);
        assertEquals(374.25,wallet.balanceInBaseCurrency());
    }
    @Test
    void balanceShouldBeINR748And5pWhenUSD7AndUSD3IsDeposited() throws  InvalidAmountException {
        Money deposit1 = new Money(7, BaseCurrency.USD);
        wallet.putMoney(deposit1);
        Money deposit2 = new Money(3, BaseCurrency.USD);
        wallet.putMoney(deposit2);
        assertEquals(748.5,wallet.balanceInBaseCurrency());
    }
    @Test
    void shouldThrowExceptionWhenNegativeAmountIsDepositedInINR()  {
        Money deposit = new Money(-10, BaseCurrency.INR);
        assertThrows(InvalidAmountException.class,() ->wallet.putMoney(deposit));
    }
    @Test
    void shouldThrowExceptionWhenNegativeAmountIsDepositedInUSD()  {
        Money deposit = new Money(-10, BaseCurrency.USD);
        assertThrows(InvalidAmountException.class,() ->wallet.putMoney(deposit));
    }
    @Test
    void balanceShouldBeINR50WhenINR100IsDepositedAndINR50IsWithdrawn() throws InvalidAmountException, InsufficientBalanceException {
        Money deposit = new Money(100,BaseCurrency.INR);
        Money withdraw = new Money(50, BaseCurrency.INR);
        wallet.putMoney(deposit);
        wallet.withdrawMoney(withdraw);
        assertEquals(50,wallet.balanceInBaseCurrency());
    }
    @Test
    void balanceShouldBeINR74And85pWhenUSD5IsDepositedAndUSD4IsWithdrawn() throws InvalidAmountException, InsufficientBalanceException {
        Money deposit = new Money(5, BaseCurrency.USD);
        Money withdraw = new Money(4,BaseCurrency.USD);
        wallet.putMoney(deposit);
        wallet.withdrawMoney(withdraw);
        assertEquals(74.85,wallet.balanceInBaseCurrency());
    }
    @Test
    void shouldThrowExceptionWhenNegativeAmountIsGivenInINRForWithdrawal() {
        Money deposit = new Money(-50, BaseCurrency.INR);
        assertThrows(InvalidAmountException.class,()->wallet.putMoney(deposit));
    }
    @Test
    void shouldThrowExceptionWhenWithdrawalAmountIsGreaterThanBalanceForINR() throws InvalidAmountException {
        Money deposit = new Money(50, BaseCurrency.INR);
        Money withdraw = new Money(100, BaseCurrency.INR);
        wallet.putMoney(deposit);
        //  wallet.withdraw(withdraw);
        assertThrows(InsufficientBalanceException.class,()->wallet.withdrawMoney(withdraw));
    }
    @Test
    void shouldThrowExceptionWhenNegativeAmountIsGivenInUSDForWithdrawal() {
        Money deposit = new Money(-50,BaseCurrency.USD);
        assertThrows(InvalidAmountException.class,()->wallet.putMoney(deposit));
    }
    @Test
    void shouldThrowExceptionWhenWithdrawalAmountIsGreaterThanBalanceForUSD() throws InvalidAmountException {
        Money deposit = new Money(1,BaseCurrency.USD);
        Money withdraw = new Money(2,BaseCurrency.USD);
        wallet.putMoney(deposit);
        assertThrows(InsufficientBalanceException.class,()->wallet.withdrawMoney(withdraw));
    }
    @Test
    void balanceShouldBeINR124And85pWhenINR50AndUSD1IsDeposited() throws InvalidAmountException {

        Money deposit1 = new Money(50, BaseCurrency.INR);
        Money deposit2 = new Money(1,BaseCurrency.USD);
        wallet.putMoney(deposit1);
        wallet.putMoney(deposit2);
        assertEquals(124.85, wallet.balanceInBaseCurrency());
    }
    @Test
    void balanceShouldBeUSD4WhenDepositIsINR74And85pAnd1USDAndINR149And7p() throws  InvalidAmountException {

        Money deposit1 = new Money(74.85, BaseCurrency.INR);
        Money deposit2 = new Money(1,BaseCurrency.USD);
        Money deposit3 = new Money(149.7, BaseCurrency.INR);
        wallet.putMoney(deposit1);
        wallet.putMoney(deposit2);
        wallet.putMoney(deposit3);
        assertEquals(4, wallet.balanceInPreferredCurrency(BaseCurrency.USD));

    }
    @Test
    void balanceShouldBeINR124And85pWhenDepositIsINR50And1Dollar() throws  InvalidAmountException {

        Money deposit1 = new Money(50,BaseCurrency.INR);
        Money deposit2 = new Money(1,BaseCurrency.USD);
        wallet.putMoney(deposit1);
        wallet.putMoney(deposit2);
        assertEquals(124.85, wallet.balanceInPreferredCurrency(BaseCurrency.INR));
    }

    @Test
    void balanceShouldBeINR82And26pWhen1EUROIsDeposited() throws InvalidAmountException {
        Money deposit1 = new Money(82.26, BaseCurrency.INR);
        wallet.putMoney(deposit1);
        assertEquals(82.26,wallet.balanceInBaseCurrency());
    }



}



