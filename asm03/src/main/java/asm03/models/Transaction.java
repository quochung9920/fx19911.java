package asm03.models;

import java.util.UUID;

import asm03.Utils.Utils;



/** class quản lý giao dịch */
public class Transaction {
    // Thuộc tính
    private String id;
    private String accountNumber;
    private double amount;
    private String time;
    private Boolean status;

    /** Phương thức khởi tạo */
    public Transaction() {
    }

    /** Phương thức khởi tạo có tham số */
    public Transaction(String accountNumber, double amount, String time, Boolean status) {
        this.id = UUID.randomUUID().toString();
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.time = time;
        this.status = status;
    }

    /** Getter và Setter của các thuộc tính */
    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    /** Phương thức displayInformation() hiển thị thông tin giao dịch */
    public void displayInformation() {
        System.out.println("[GD]  " + this.accountNumber + " | " + String.format("%-18s", "-" + Utils.formatAmount(amount)) + "d | " + String.format("%20s", this.time));
    }
}
