package com.payments.config;

import com.payments.model.AccountTransfers;
import com.payments.service.impl.AccountService;
import com.payments.service.impl.TransactionService;
import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.before;
import static spark.Spark.get;
import static spark.Spark.halt;
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
            map.put("pageTitle", "Timeline");
            List<String> accountsList = accountService.getAccountNamesForDropDown(accountTransfers.getAccounts());
            map.put("accountsList", accountsList);
            return new ModelAndView(map, "transactions.ftl");
        }, new FreeMarkerEngine());

        /*
		 * Shows the pay page
		 */
        get("/pay", (req, res) -> "Hello Pay");
    }
}
