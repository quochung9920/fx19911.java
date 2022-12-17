package asm04.service;

import asm02.models.Account;

public interface Transfer {
    void transfer(Account receiveAccount,double amount);
}
