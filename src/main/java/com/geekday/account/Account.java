package com.geekday.account;

public class Account {

    private String accountId;

    public Account(String accountId) {
        this.accountId = accountId;
    }

    public String getAccountId() {
        return accountId;
    }

    @Override
    public String toString() {
        return "{ accountId : " + accountId + '}';
    }
}
