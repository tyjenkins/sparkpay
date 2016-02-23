package com.payments.service;

import com.payments.model.Account;
import com.payments.service.impl.PaymentsService;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by Jenkins on 23/02/2016.
 */
public class PaymentsServiceTest {
    PaymentsService paymentsService = new PaymentsService();

    @Test
    public void getAccountNamesForDropDown_Should_ReturnListOfAccountName_When_CalledWithAccountSet() {
        List<String> result = paymentsService.getAccountNamesForDropDown(paymentsService.getInitialAccounts());

        assertThat(result.size(), is(3));

        result.forEach((temp) -> {
            assertThat(paymentsService.getInitialAccounts().contains(new Account(temp,1,"em")), is(true));
        });
    }
}
