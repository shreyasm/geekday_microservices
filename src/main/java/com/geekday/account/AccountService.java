package com.geekday.account;

import com.geekday.common.DomainEvent;
import com.geekday.common.DomainEventSubscriber;

/**
 * Created by shreyas on 16/7/16.
 */
public class AccountService {

    public void listenForCustomerCreatedEvent() {
        DomainEventSubscriber subscriber = new DomainEventSubscriber("CustomerCreated");

        while(true) {
            System.out.println("Trying to get event");
            DomainEvent receivedDomainEvent = subscriber.receive();
            createAccount();
        }
    }

    public void createAccount() {
        Account account = new Account("newAccount");
        AccountRepository accountRepository = new AccountRepository();
        accountRepository.createAccount(account);
    }

    public Account getAccount(String id) {
        return new AccountRepository().getAccount(id);
    }
}
