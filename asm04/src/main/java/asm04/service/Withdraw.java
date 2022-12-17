package asm04.service;

public interface Withdraw {
    boolean withdraw(double amount);
    boolean isAcceptedWithdraw(double amount);
    boolean isAcceptedTransfer(double amount);
}
