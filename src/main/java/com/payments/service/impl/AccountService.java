package com.payments.service.impl;

import com.payments.model.Account;
import com.payments.model.AccountTransfers;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.zip.Inflater;

/**
 * Created by Jenkins on 23/02/2016.
 */
@Service
public class AccountService {

    public static final String ACC_1 = "Acc 1";
    public static final String ACC_2 = "Acc 2";
    public static final String ACC_3 = "Acc 3";

    public Map<String, Account> getInitialAccounts() {
        Map<String, Account> accounts = new HashMap<String, Account>();
        accounts.put(ACC_1, new Account(ACC_1, Account.STARTING_BALANCE, ACC_1 + " email"));
        accounts.put(ACC_2, new Account(ACC_2, Account.STARTING_BALANCE, ACC_2 + " email"));
        accounts.put(ACC_3, new Account(ACC_3, Account.STARTING_BALANCE, ACC_3 + " email"));

        return accounts;
    }

    public List<String> getAccountNamesForDropDown(Map<String, Account> accounts) {
        List<String> accountList = new ArrayList<>();
        accounts.forEach((k,v) -> accountList.add(k));

        return accountList;
    }

    public Account getAccountByName(AccountTransfers accountTransfers, String account) {
        return accountTransfers.getAccounts().get(account);
    }
}
