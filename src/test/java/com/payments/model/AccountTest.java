package com.payments.model;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by Jenkins on 23/02/2016.
 */
public class AccountTest {

    @Test
    public void showAccountDetails_Should_PrintNameAndBalance_When_Called(){
        Account account = new Account("Test Account", 300, "test account email");

        assertThat(account.showAccountDetails(), is("Test Account: £300"));
    }
}
