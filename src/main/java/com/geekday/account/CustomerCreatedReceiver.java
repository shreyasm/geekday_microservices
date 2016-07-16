package com.geekday.account;

import com.geekday.common.Receiver;

/**
 * Created by shreyas on 16/7/16.
 */
public class CustomerCreatedReceiver extends Receiver {

    public CustomerCreatedReceiver() {
    }

    @Override
    public void on(Object event) {
        new AccountService().createAccount();
    }
}
