package com.payments.model;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Created by Jenkins on 23/02/2016.
 */
public class AccountTest {

    @Test
    public void showAccountDetails_Should_PrintNameAndBalance_When_Called(){
        Account account = new Account("Test Account", 300, "test account email");

        assertEquals(account.showAccountDetails(), "Test Account: £300");
    }
}
