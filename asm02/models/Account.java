package models;

/** Class Account có Access Modifier là public, nên có thể truy cập từ bất cứ đâu */
public class Account {
    // Khai báo các thuộc tính của Account
    private String accountNumber;
    private double balance;
   

    /** Getter và Setter của các thuộc tính */
    public String getAccountNumber() {
        return accountNumber;
    }
    public void setAccountNumber(String accountNumber) {
        // Số tài khoản khách hàng gồm 6 chữ số và là số nguyên từ 0-9
        if (accountNumber.length() == 6 && accountNumber.matches("[0-9]")) {
            this.accountNumber = accountNumber;
        } else {
            System.out.println("Du lieu so tai khoan khong hop le!");
        }
    }
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /** Phương thức isPremium() kiểm tra xem tài khoản có phải là tài khoản Premium hay không */
    public boolean isPremium() {
        return balance >= 10000000;
    }

    /** Hàm toString xuất ra thông tin của tài khoản */
    @Override
    public String toString() {
        return accountNumber + " | " + balance + "đ";
        
    }

}
