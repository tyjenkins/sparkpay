package com.payments.model;

/**
 * Created by Jenkins on 23/02/2016.
 */
public class Transaction {
    private String fromAcc;
    private String toAcc;
    private Integer amount;

    public Transaction(String fromAcc, String toAcc, Integer amount) {
        this.fromAcc = fromAcc;
        this.toAcc = toAcc;
        this.amount = amount;
    }

    public String getFromAcc() {
        return fromAcc;
    }

    public void setFromAcc(String fromAcc) {
        this.fromAcc = fromAcc;
    }

    public String getToAcc() {
        return toAcc;
    }

    public void setToAcc(String toAcc) {
        this.toAcc = toAcc;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
