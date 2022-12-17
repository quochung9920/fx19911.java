package asm02.models;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

/** Class Customer có Access Modifier là public và kế thừa lại class User */
public class Customer extends User {
    // Khai báo các thuộc tính của class Customer
    private List<Account> accounts;

    /** Phương thức khởi tạo */
    public Customer(String name, String customerId) {
        super.setName(name);
        super.setCustomerId(customerId);
        this.accounts = new ArrayList<>();
    }

    /** Getter và Setter của các thuộc tính */
    public List<Account> getAccounts() {
        return accounts;
    }

    /**
     * Phương thức isPremium() kiểm tra xem tài khoản có phải là tài khoản Premium
     * hay không, 1 khách hàng là Premium nếu có ít nhất 1 tài khoản là Premium
     */
    public boolean isPremium() {
        for (Account account : accounts) {
            if (account.isPremiumAccount()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Phương thức addAccount dùng để thêm tài khoản mới cho khách hàng. Tài khoản
     * được thêm khi và chỉ khi số tài khoản của tài khoản chưa từng tồn tại trước
     * đó.
     */
    public void addAccount(Account newAccount) {
        if (!isAccountExisted(newAccount.getAccountNumber())) {
            accounts.add(newAccount);
        } else {
            throw new RuntimeException("Tai khoan da ton tai!");
        }
    }

    /**
     * Phương thức isAccountExisted dùng để kiểm tra xem tài khoản này đã tồn tại
     * trong khách hàng hay chưa
     */
    public boolean isAccountExisted(String accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Phương thức getBalance() dùng để tính toán số dư của khách hàng là tổng số dư
     * của tất cả tài khoản mà khách hàng có
     */
    public double getBalance() {
        double balance = 0;
        for (Account account : accounts) {
            balance += account.getBalance();
        }
        return balance;
    }

    /**
     * Hàm displayInformation() dùng để hiển thị thông tin của khách hàng. Hàng đầu
     * tiên là thông tin tài khoản gồm: CCCD, tên, loại khách hàng nếu là Premium
     * thì in ra Premium, ngược lại là Normal, sau cùng là tổng số tiền mà khách
     * hàng này có.
     */
    public void displayInformation() {
        NumberFormat formatter = new DecimalFormat("#,###d");

        System.out.println(getCustomerId() + " | " + String.format("%12s", getName()) + " | " + String.format("%12s", (isPremium() ? "Premium" : "Normal")) + " | "
                +  String.format("%14s", formatter.format(getBalance())));
        // Hiển thị thông tin tài khoản của khách hàng
        int i = 1;
        for (Account account : accounts) {
            System.out.println(i++ + "\t" + account.toString());
        }
    }

}
