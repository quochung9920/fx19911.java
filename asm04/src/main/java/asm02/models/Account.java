package asm02.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import asm04.common.Utils;
import asm04.dao.AccountDao;
import asm04.dao.TransactionDao;
import asm04.model.DigitalBank;
import asm04.model.Transaction;
import asm04.model.Transaction.TransactionType;

/**
 * Class Account có Access Modifier là public, nên có thể truy cập từ bất cứ đâu
 */
public class Account implements Serializable {
    private static final long serialVersionUID = 1L;

    // Khai báo các thuộc tính của Account
    private String accountNumber;
    private double balance;
    private List<Transaction> transactions = new ArrayList<>();
    private String customerId;

    /** Phương thức khởi tạo */
    public Account() {
    }

    /** Phương thức khởi tạo */
    public Account(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    /** Getter và Setter của các thuộc tính */
    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<Transaction> getTransactions() {
        return TransactionDao.getTransactionsByAccountNumber(this.accountNumber);
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    /**
     * Phương thức isPremium() kiểm tra xem tài khoản có phải là tài khoản Premium
     * hay không
     */
    public boolean isPremiumAccount() {
        return this.balance >= 10000000;
    }

    /** Hàm toString xuất ra thông tin của tài khoản */
    @Override
    public String toString() {
        return String.format("%11s",
                this.getAccountNumber()) + " | "
                + "SAVINGS" + " | "
                + String.format("%37s", Utils.formatAmount(this.getBalance()));
    }

    public Account input(Scanner scanner) {
        String accountNumber = "";
        Bank bank = new DigitalBank();
        do {
            System.out.print("Nhap so tai khoan gom 6 chu so: ");
            accountNumber = scanner.nextLine();
            if (((DigitalBank) bank).isAccountExisted(accountNumber)) {
                System.out.println("So tai khoan da ton tai");
            }
        } while (accountNumber.isEmpty() || accountNumber.length() != 6
                || ((DigitalBank) bank).isAccountExisted(accountNumber)
                || !accountNumber.matches("[0-9]+"));

        String balance = "";
        do {
            System.out.print("Nhap so du tai khoan >= 50000d: ");
            balance = scanner.nextLine();
        } while (balance.isEmpty() || !balance.matches("[0-9]+") || Double.parseDouble(balance) < 50000);

        this.setAccountNumber(accountNumber);
        this.setBalance(Double.parseDouble(balance));
        return this;
    }


}
