package com.payments.model;

/**
 * Created by Jenkins on 23/02/2016.
 */
public class Account {
    public static final Integer STARTING_BALANCE = 200;

    private String name;
    private Integer balance;
    private String email;

    public Account(String name, Integer balance, String email) {
        this.name = name;
        this.balance = balance;
        this.email = email;
    }

    public String showAccountDetails() {
        return getName() + ": £" + getBalance();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        return name.equals(account.name);

    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
