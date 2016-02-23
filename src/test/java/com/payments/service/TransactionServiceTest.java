package com.payments.service;

import com.payments.model.Account;
import com.payments.service.impl.AccountService;
import com.payments.service.impl.TransactionService;
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

    @Test
    public void transfer_Should_MoveAmountFromAToB_When_AmountDoesNotExceedAvailableInA() {
        Map<String, Account> result = transactionService.transfer(
                accountService.getInitialAccounts(), AccountService.ACC_1, AccountService.ACC_3, 100);

        assertThat(result.get(AccountService.ACC_1).getBalance(), is(100));
        assertThat(result.get(AccountService.ACC_2).getBalance(), is(200));
        assertThat(result.get(AccountService.ACC_3).getBalance(), is(300));
    }
}
