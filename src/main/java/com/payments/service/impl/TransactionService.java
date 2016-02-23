package com.payments.service.impl;

import com.payments.model.Account;
import com.payments.model.AccountTransfers;
import com.payments.model.Transaction;
import org.springframework.stereotype.Service;

/**
 * Created by Jenkins on 23/02/2016.
 */
@Service
public class TransactionService {
    public AccountTransfers transfer(AccountTransfers accountTransfers, String accountA, String accountB, Integer amount) {
        Account modA = accountTransfers.getAccounts().get(accountA);
        Account modB = accountTransfers.getAccounts().get(accountB);

        if (modA.getBalance() - amount >= 0) {
            modA.setBalance(modA.getBalance() - amount);
            modB.setBalance(modB.getBalance() + amount);

            accountTransfers.getAccounts().put(accountA, modA);
            accountTransfers.getAccounts().put(accountB, modB);

            accountTransfers.getTransactions().add(new Transaction(accountA, accountB, amount));
        }

        return accountTransfers;
    }
}
