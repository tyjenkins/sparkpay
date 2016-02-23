package com.payments.service.impl;

import com.payments.model.Account;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Jenkins on 23/02/2016.
 */
@Service
public class PaymentsService {

    public Set<Account> getInitialAccounts() {
        Set<Account> accounts = new HashSet<Account>();
        accounts.add(new Account("Acc 1", Account.STARTING_BALANCE, "Acc 1 email"));
        accounts.add(new Account("Acc 2", Account.STARTING_BALANCE, "Acc 2 email"));
        accounts.add(new Account("Acc 3", Account.STARTING_BALANCE, "Acc 3 email"));

        return accounts;
    }

}
