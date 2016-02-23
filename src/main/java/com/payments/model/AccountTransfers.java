package com.payments.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Jenkins on 23/02/2016.
 */
public class AccountTransfers {
    private Map<String, Account> accounts;
    private List<Transaction> transactions = new ArrayList<Transaction>();

    public Map<String, Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Map<String, Account> accounts) {
        this.accounts = accounts;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
