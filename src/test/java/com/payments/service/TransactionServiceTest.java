package com.payments.service;

import com.payments.model.Account;
import com.payments.model.AccountTransfers;
import com.payments.model.Transaction;
import com.payments.service.impl.AccountService;
import com.payments.service.impl.TransactionService;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
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
    public void transfer_Should_WriteSuccessMessage_When_AmountDoesNotExceedAvailableInA() {
        AccountTransfers result = transactionService.transfer(
                accountTransfers, AccountService.ACC_1, AccountService.ACC_3, 100);

        assertThat(result.getMessage(), is(AccountTransfers.TRANSFER_SUCCESS));
    }

    @Test
    public void transfer_Should_WriteFailedMessage_When_AmountExceedsAvailableInA() {
        AccountTransfers result = transactionService.transfer(
                accountTransfers, AccountService.ACC_1, AccountService.ACC_3, 300);

        assertThat(result.getMessage(), is(AccountTransfers.TRANSFER_FAILED));
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
    public void transfer_Should_MoveAmountFromAToB_When_AmountIsEqualToAvailableInA() {
        AccountTransfers result = transactionService.transfer(
                accountTransfers, AccountService.ACC_1, AccountService.ACC_3, 200);

        assertThat(result.getAccounts().get(AccountService.ACC_1).getBalance(), is(0));
        assertThat(result.getAccounts().get(AccountService.ACC_2).getBalance(), is(200));
        assertThat(result.getAccounts().get(AccountService.ACC_3).getBalance(), is(400));
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

    @Test
    public void transfer_Should_NotRecordTransaction_When_AmountDoesExceedAvailableInA() {
        AccountTransfers result = transactionService.transfer(
                accountTransfers, AccountService.ACC_1, AccountService.ACC_3, 300);

        assertThat(result.getTransactions().size(), is(0));
    }

    @Test
    public void transfer_Should_RecordTransaction_When_AmountIsEqualToAvailableInA() {
        AccountTransfers result = transactionService.transfer(
                accountTransfers, AccountService.ACC_1, AccountService.ACC_3, 200);

        assertThat(result.getTransactions().size(), is(1));
        assertThat(result.getTransactions().get(0).getFromAcc(), is(AccountService.ACC_1));
        assertThat(result.getTransactions().get(0).getToAcc(), is(AccountService.ACC_3));
        assertThat(result.getTransactions().get(0).getAmount(), is(200));
    }

    @Test
    public void getTransactionsForAccount_Should_ReturnTransactionsFromAccountA_When_CalledWithAccountA() {
        List<Transaction> transactionList = new ArrayList<>();
        transactionList.add(new Transaction(AccountService.ACC_1, AccountService.ACC_2, 100));
        transactionList.add(new Transaction(AccountService.ACC_3, AccountService.ACC_2, 120));
        accountTransfers.setTransactions(transactionList);

        List<Transaction> result = transactionService.getTransactionsForAccount(accountTransfers, AccountService.ACC_1);

        assertThat(result.size(), is(1));
        assertThat(result.get(0).getFromAcc(), is(AccountService.ACC_1));
        assertThat(result.get(0).getToAcc(), is(AccountService.ACC_2));
        assertThat(result.get(0).getAmount(), is(100));
    }

    @Test
    public void getTransactionsForAccount_Should_ReturnTransactionsToAccountA_When_CalledWithAccountA() {
        List<Transaction> transactionList = new ArrayList<>();
        transactionList.add(new Transaction(AccountService.ACC_2, AccountService.ACC_1, 130));
        transactionList.add(new Transaction(AccountService.ACC_3, AccountService.ACC_2, 100));
        accountTransfers.setTransactions(transactionList);

        List<Transaction> result = transactionService.getTransactionsForAccount(accountTransfers, AccountService.ACC_1);

        assertThat(result.size(), is(1));
        assertThat(result.get(0).getFromAcc(), is(AccountService.ACC_2));
        assertThat(result.get(0).getToAcc(), is(AccountService.ACC_1));
        assertThat(result.get(0).getAmount(), is(130));
    }
}
