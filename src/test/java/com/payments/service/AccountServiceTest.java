package com.payments.service;

import com.payments.service.impl.AccountService;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by Jenkins on 23/02/2016.
 */
public class AccountServiceTest {
    AccountService accountService = new AccountService();

    @Test
    public void getAccountNamesForDropDown_Should_ReturnListOfAccountName_When_CalledWithAccountSet() {
        List<String> result = accountService.getAccountNamesForDropDown(accountService.getInitialAccounts());

        assertThat(result.size(), is(3));

        result.forEach((temp) -> {
            assertThat(accountService.getInitialAccounts().containsKey(temp), is(true));
        });
    }
}
