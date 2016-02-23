package com.payments.config;

import com.payments.service.impl.AccountService;
import com.payments.service.impl.TransactionService;

import static spark.Spark.before;
import static spark.Spark.get;
import static spark.Spark.halt;
import static spark.SparkBase.staticFileLocation;

/**
 * Created by Jenkins on 23/02/2016.
 */
public class WebConfig {

    private AccountService paymentsService;
    private TransactionService transactionService;

    public WebConfig(AccountService paymentsService, TransactionService transactionService) {
        this.paymentsService = paymentsService;
        this.transactionService = transactionService;
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
        get("/transactions", (req, res) -> "Hello Trans");

        /*
		 * Shows the pay page
		 */
        get("/pay", (req, res) -> "Hello Pay");
    }
}
