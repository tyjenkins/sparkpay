package com.payments.service;

import com.payments.model.Account;
import com.payments.model.AccountTransfers;
import com.payments.service.impl.AccountService;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by Jenkins on 23/02/2016.
 */
public class AccountServiceTest {
    AccountService accountService = new AccountService();
    AccountTransfers accountTransfers = new AccountTransfers();

    @Before
    public void setUp() {
        accountTransfers.setAccounts(accountService.getInitialAccounts());
    }

    @Test
    public void getAccountNamesForDropDown_Should_ReturnListOfAccountName_When_CalledWithAccountSet() {
        List<String> result = accountService.getAccountNamesForDropDown(accountTransfers.getAccounts());

        assertThat(result.size(), is(3));

        result.forEach((temp) -> {
            assertThat(accountService.getInitialAccounts().containsKey(temp), is(true));
        });
    }

    @Test
    public void getAccountByName_Should_Return_Account_When_AccountInList() {
        Account account = accountService.getAccountByName(accountTransfers, AccountService.ACC_1);

        assertThat(account.getName(), is(AccountService.ACC_1));
        assertThat(account.getBalance(), is(Account.STARTING_BALANCE));
        assertThat(account.getEmail(), is(AccountService.ACC_1 + " email"));
    }
}
