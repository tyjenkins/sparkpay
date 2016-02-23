package com.payments.service.impl;

import com.payments.model.Account;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.zip.Inflater;

/**
 * Created by Jenkins on 23/02/2016.
 */
@Service
public class PaymentsService {

    public Map<String, Account> getInitialAccounts() {
        Map<String, Account> accounts = new HashMap<String, Account>();
        accounts.put("Acc 1", new Account("Acc 1", Account.STARTING_BALANCE, "Acc 1 email"));
        accounts.put("Acc 2", new Account("Acc 2", Account.STARTING_BALANCE, "Acc 2 email"));
        accounts.put("Acc 3", new Account("Acc 3", Account.STARTING_BALANCE, "Acc 3 email"));

        return accounts;
    }

    public List<String> getAccountNamesForDropDown(Map<String, Account> accounts) {
        List<String> accountList = new ArrayList<>();
        accounts.forEach((k,v) -> accountList.add(k));

        return accountList;
    }

    public Map<String, Account> transferAmountFromAccountAToB(Map<String, Account> accounts, String accountA,
                                                              String accountB, Integer amount) {

        return accounts;
    }

}
