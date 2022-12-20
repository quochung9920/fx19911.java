package asm04.service;

/** Lớp này dùng để định nghĩa các phương thức để thực hiện giao dịch rút tiền */
public interface Withdraw {
    boolean withdraw(double amount);
    boolean isAcceptedWithdraw(double amount);
    boolean isAcceptedTransfer(double amount);
}
