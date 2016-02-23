package com.payments.config;

import com.payments.model.Account;
import com.payments.model.AccountTransfers;
import com.payments.model.Transaction;
import com.payments.service.impl.AccountService;
import com.payments.service.impl.TransactionService;
import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import org.eclipse.jetty.util.MultiMap;
import org.eclipse.jetty.util.UrlEncoded;
import org.springframework.beans.BeanUtils;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;
import static spark.SparkBase.staticFileLocation;

/**
 * Created by Jenkins on 23/02/2016.
 */
public class WebConfig {

    private AccountTransfers accountTransfers = new AccountTransfers();
    AccountService accountService;
    TransactionService transactionService;

    public WebConfig(AccountService accountService, TransactionService transactionService) {
        this.accountService = accountService;
        this.transactionService = transactionService;
        this.accountTransfers.setAccounts(accountService.getInitialAccounts());

        staticFileLocation("/");

        setupRoutes();
    }

    private void setupRoutes() {
        /*
		 * Redirects / to the transactions page
		 */
        before("/", (req, res) -> {
            res.redirect("/transactions");
            halt();
        });

		/*
		 * Shows the transactions page
		 */
        get("/transactions", (req, res) -> {
            Map<String, Object> map = new HashMap<>();

            List<String> accountsList = accountService.getAccountNamesForDropDown(accountTransfers.getAccounts());
            map.put("accountsList", accountsList);

            Account account = accountService.getAccountByName(accountTransfers, accountsList.get(0));
            map.put("account", account);

            List<Transaction> transactionList = transactionService.getTransactionsForAccount(accountTransfers, accountsList.get(0));
            map.put("transactionList", transactionList);

            return new ModelAndView(map, "transactions.ftl");
        }, new FreeMarkerEngine());
        /*
		 * Submits the transactions page
		 */
        post("/transactions", (req, res) -> {
            String selectedAccount = "";
            if (req.body().endsWith(AccountService.ACC_1)) {
                selectedAccount = AccountService.ACC_1;
            } else if (req.body().endsWith(AccountService.ACC_2)) {
                selectedAccount = AccountService.ACC_2;
            } else if (req.body().endsWith(AccountService.ACC_3)) {
                selectedAccount = AccountService.ACC_3;
            }

            Map<String, Object> map = new HashMap<>();

            List<Transaction> transactionList = transactionService.getTransactionsForAccount(accountTransfers, selectedAccount);
            map.put("transactionList", transactionList);

            Account account = accountService.getAccountByName(accountTransfers, selectedAccount);
            map.put("account", account);

            List<String> accountsList = accountService.getAccountNamesForDropDown(accountTransfers.getAccounts());
            map.put("accountsList", accountsList);

            return new ModelAndView(map, "transactions.ftl");
        }, new FreeMarkerEngine());

        /*
		 * Shows the pay page
		 */
        get("/pay", (req, res) -> {
            Map<String, Object> map = new HashMap<>();

            List<String> accountsList = accountService.getAccountNamesForDropDown(accountTransfers.getAccounts());
            map.put("accountsList", accountsList);

            return new ModelAndView(map, "pay.ftl");
        }, new FreeMarkerEngine());
        /*
		 * Shows the pay page
		 */
        post("/pay", (req, res) -> {
            String body = req.body();

            Map<String, Object> map = new HashMap<>();

            List<String> accountsList = accountService.getAccountNamesForDropDown(accountTransfers.getAccounts());
            map.put("accountsList", accountsList);

            return new ModelAndView(map, "pay.ftl");
        }, new FreeMarkerEngine());
    }
}
