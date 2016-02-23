package com.payments.service.impl;

import com.payments.model.Account;

import java.util.Map;

/**
 * Created by Jenkins on 23/02/2016.
 */
public class TransactionService {
    public Map<String, Account> transfer(Map<String, Account> accounts, String accountA, String accountB, Integer amount) {
        Account modA = accounts.get(accountA);
        Account modB = accounts.get(accountB);

        modA.setBalance(modA.getBalance() - amount);
        modB.setBalance(modB.getBalance() + amount);

        accounts.put(accountA, modA);
        accounts.put(accountB, modB);

        return accounts;
    }
}
