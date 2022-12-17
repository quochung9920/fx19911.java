package asm03.models;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import asm02.models.Account;
import asm02.models.Customer;

/**
 * Class DigitalCustomer
 * Kế thừa từ class Customer
 */
public class DigitalCustomer extends Customer {

    /** Phương thức khởi tạo */
    public DigitalCustomer(String name, String customerId) {
        super(name, customerId);
    }

    /** Phương thức hiển thị thông tin khách hàng */
    @Override
    public void displayInformation() {
        // Định dạng số tiền
        NumberFormat formatter = new DecimalFormat("#,###d");

        // Hiển thị thông tin khách hàng
        System.out.println(getCustomerId() + " | " + String.format("%-8s", getName()) + " | "
                + String.format("%-8s", (isPremium() ? "Premium" : "Normal")) + " | "
                + String.format("%20s", formatter.format(getBalance())));

        // Hiển thị thông tin tài khoản của khách hàng
        int i = 1;
        for (Account account : super.getAccounts()) {
            System.out.println((i++) + account.toString());
        }
    }

    /** Phương thức tìm kiếm tài khoản theo mã tài khoản */
    public Account getAccountByAccountNumber(String accountId) {
        for (Account account : super.getAccounts()) {
            if (account.getAccountNumber().equals(accountId)) {
                return account;
            }
        }
        return null;
    }

    /** Phương thức rút tiền */
    public void withdraw(String accountNumber, double amount) {
        // Tìm tài khoản theo số tài khoản
        Account account = getAccountByAccountNumber(accountNumber);
        if (account instanceof LoanAccount) {
            if (!((LoanAccount) account).withdraw(amount)) {
                throw new IllegalArgumentException("So tien nhap vao khong hop le");
            }
            
        } else {
            if (!((SavingsAccount) account).withdraw(amount)) {
                throw new IllegalArgumentException("So tien nhap vao khong hop le");
            }
        }
    }

    /** Phương thức in ra lịch sử giao dịch của tài khoản */
    public void displayTransactionHistory() {
        int i = 0;
        this.displayInformation();
        for (Account account : super.getAccounts()) {
            // Hiển thị thông tin tài khoản
            for (Transaction transaction : account.getTransactions()) {
                transaction.displayInformation();
                i++;
            }
        }

        // Nếu không có giao dịch nào
        if (i == 0) {
            System.out.println("Khong co giao dich nao!");
        }
    }
}
