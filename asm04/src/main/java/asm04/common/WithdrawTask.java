package asm04.common;

import asm02.models.Account;
import asm04.model.SavingsAccount;

public class WithdrawTask implements Runnable {
    private Account account;
    private double amount;
    
    public WithdrawTask(Account account, double amount) {
        this.account = account;
        this.amount = amount;
    }

    @Override
    public void run() {
        ((SavingsAccount)account).withdraw(amount);
    }
}
