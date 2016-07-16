package com.geekday.account;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by shreyas on 16/7/16.
 */
public class AccountRepositoryTest {

    @Test
    public void shouldSaveAccount() throws Exception {
        AccountRepository.initialise();
        AccountRepository repository = new AccountRepository();

        repository.createAccount(new Account("1"));
        Account account = repository.getAccount("1");

        assertEquals(account.getAccountId(), "1");
    }
}