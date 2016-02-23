package com.payments.config;

import com.payments.service.impl.PaymentsService;
import spark.ModelAndView;

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

    private PaymentsService paymentsService;


    public WebConfig(PaymentsService paymentsService) {
        this.paymentsService = paymentsService;
        staticFileLocation("/");
        setupRoutes();
    }

    private void setupRoutes() {
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
