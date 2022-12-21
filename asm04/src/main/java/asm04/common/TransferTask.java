package asm04.common;

import asm02.models.Account;
import asm04.model.SavingsAccount;

public class TransferTask implements Runnable {
    private Account fromAccount;
    private Account toAccount;
    private double amount;

    public TransferTask(Account fromAccount, Account toAccount, double amount) {
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
    }

    @Override
    public void run() {
        ((SavingsAccount)fromAccount).transfer(toAccount, amount);
    }
}
