package models;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/** Class Account có Access Modifier là public, nên có thể truy cập từ bất cứ đâu */
public class Account {
    // Khai báo các thuộc tính của Account
    private String accountNumber;
    private double balance;

    /** Phương thức khởi tạo */
    public Account() {
    }
   

    /** Getter và Setter của các thuộc tính */
    public String getAccountNumber() {
        return accountNumber;
    }
    public void setAccountNumber(String accountNumber) {
        // Số tài khoản khách hàng gồm 6 chữ số và là số nguyên từ 0-9
        if (accountNumber.length() == 6 && accountNumber.matches("[0-9]+")) {
            this.accountNumber = accountNumber;
        } else {
            throw new IllegalArgumentException("So tai khoan khong hop le!");
        }
    }
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        // Số dư tài khoản phải lớn hơn 50000
        if (balance >= 50000) {
            this.balance = balance;
        } else {
            throw new IllegalArgumentException("So du phai lon hon 50,000d!");
        }
    }

    /** Phương thức isPremium() kiểm tra xem tài khoản có phải là tài khoản Premium hay không */
    public boolean  isPremiumAccount() {
        return balance >= 10000000;
    }

    /** Hàm toString xuất ra thông tin của tài khoản */
    @Override
    public String toString() {
        NumberFormat formatter = new DecimalFormat("#,###d");
        return accountNumber + " | " + String.format("%42s", formatter.format(balance));
    }

}
