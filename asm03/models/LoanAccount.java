package models;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import Utils.Utils;
import interfaces.ReportService;
import interfaces.Withdraw;

public class LoanAccount extends Account implements ReportService, Withdraw {

    // Thuộc tính
    private static final double LOAN_ACCOUNT_WITHDRAW_FEE = 0.05;
    private static final double LOAN_ACCOUNT_WITHDRAW_PREMIUM_FEE = 0.01;
    private static final double LOAN_ACCOUNT_MAX_BALANCE = 100000000;
    private String title = "BIEN LAI GIAO DICH LOAN";
    private double fee;


    /** Phương thức khởi tạo */
    public LoanAccount() {
        super();
    }

    /** Phương thức khởi tạo có tham số */
    public LoanAccount(String accountNumber, double balance) {
        super(accountNumber, balance);
    }

    public double getFee() {
        return super.isPremiumAccount() ? LOAN_ACCOUNT_WITHDRAW_PREMIUM_FEE : LOAN_ACCOUNT_WITHDRAW_FEE;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        NumberFormat formatter = new DecimalFormat("#,###d");
        return String.format("%11s",this.getAccountNumber()) + " | " + String.format("%-8s", "LOAN") + " | " + String.format("%31s", formatter.format(this.getBalance()));
    }

    @Override
    public void log(double amount) {
        System.out.println(Utils.getDivider());
        System.out.printf("%30s%n", getTitle());
        System.out.printf("NGAY G/D: %28s%n", Utils.getDateTime());
        System.out.printf("ATM ID: %30s%n", "DIGITAL-BANK-ATM 2022");
        System.out.printf("SO TK: %31s%n", this.getAccountNumber());
        System.out.printf("SO TIEN: %29s%n", Utils.formatBalance(amount));
        System.out.printf("SO DU: %31s%n", Utils.formatBalance(this.getBalance()));
        System.out.printf("PHI + VAT: %27s%n", Utils.formatBalance(this.getFee() * amount));
        System.out.println(Utils.getDivider());
    }

    @Override
    public boolean withdraw(double amount) {
        if(isAccepted(amount)){
            super.setBalance(super.getBalance() - amount - this.getFee() * amount);
            log(amount);
            super.getTransactions().add(new Transaction(this.getAccountNumber(), amount, Utils.getDateTime(), true));
            return true;
        }

        return false;
    }

    @Override
    public boolean isAccepted(double amount) {

        if (amount > 100000000 || super.getBalance() - amount - this.getFee() * amount < 50000) {
            
            return false;
        }

        return true;
    }
}
