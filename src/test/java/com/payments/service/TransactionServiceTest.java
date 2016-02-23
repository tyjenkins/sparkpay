package com.payments.service;

import com.payments.model.Account;
import com.payments.model.AccountTransfers;
import com.payments.service.impl.AccountService;
import com.payments.service.impl.TransactionService;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Jenkins on 23/02/2016.
 */
public class TransactionServiceTest {
    TransactionService transactionService = new TransactionService();
    AccountService accountService = new AccountService();
    AccountTransfers accountTransfers = new AccountTransfers();

    @Before
    public void setUp() {
        accountTransfers.setAccounts(accountService.getInitialAccounts());
    }

    @Test
    public void transfer_Should_MoveAmountFromAToB_When_AmountDoesNotExceedAvailableInA() {
        AccountTransfers result = transactionService.transfer(
                accountTransfers, AccountService.ACC_1, AccountService.ACC_3, 100);

        assertThat(result.getAccounts().get(AccountService.ACC_1).getBalance(), is(100));
        assertThat(result.getAccounts().get(AccountService.ACC_2).getBalance(), is(200));
        assertThat(result.getAccounts().get(AccountService.ACC_3).getBalance(), is(300));
    }

    @Test
    public void transfer_Should_NotMoveAmountFromAToB_When_AmountDoesExceedAvailableInA() {
        AccountTransfers result = transactionService.transfer(
                accountTransfers, AccountService.ACC_1, AccountService.ACC_3, 300);

        assertThat(result.getAccounts().get(AccountService.ACC_1).getBalance(), is(200));
        assertThat(result.getAccounts().get(AccountService.ACC_2).getBalance(), is(200));
        assertThat(result.getAccounts().get(AccountService.ACC_3).getBalance(), is(200));
    }

    @Test
    public void transfer_Should_RecordTransaction_When_AmountDoesNotExceedAvailableInA() {
        AccountTransfers result = transactionService.transfer(
                accountTransfers, AccountService.ACC_1, AccountService.ACC_3, 100);

        assertThat(result.getTransactions().size(), is(1));
        assertThat(result.getTransactions().get(0).getFromAcc(), is(AccountService.ACC_1));
        assertThat(result.getTransactions().get(0).getToAcc(), is(AccountService.ACC_3));
        assertThat(result.getTransactions().get(0).getAmount(), is(100));
    }
}
