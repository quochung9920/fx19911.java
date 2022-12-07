package models;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import Utils.Utils;
import interfaces.ReportService;
import interfaces.Withdraw;

/**
 * Class SavingsAccount
 * Kế thừa từ class Account
 * Implements interface ReportService, Withdraw
 * Có thuộc tính: 
 * - title: tiêu đề biên lai
 * Có phương thức:
 * - getTitle(): trả về tiêu đề biên lai
 * - withdraw(): rút tiền
 * - printReport(): in biên lai
 */
public class SavingsAccount extends Account implements ReportService, Withdraw {

    // Thuộc tính
    private String title = "BIEN LAI GIAO DICH SAVINGS";

    public String getTitle() {
        return title;
    }

    /** Phương thức khởi tạo */
    public SavingsAccount() {
        super();
    }

    /** Phương thức khởi tạo có tham số */
    public SavingsAccount(String accountNumber, double balance) {
        super(accountNumber, balance);
    }
    @Override
    public String toString() {
        NumberFormat formatter = new DecimalFormat("#,###d");
        return String.format("%11s", this.getAccountNumber()) + " | " + String.format("%-8s", "SAVINGS") + " | "
                + String.format("%31s", formatter.format(this.getBalance()));
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
        System.out.printf("PHI + VAT: %27s%n", Utils.formatBalance(0.0));
        System.out.println(Utils.getDivider());
    }

    /** Phương thức rút tiền 
     * @param amount Số tiền muốn rút
     */
    @Override
    public boolean withdraw(double amount) {
        // Nếu số tiền rút hợp lệ thì thực hiện rút tiền và ghi log
        if(isAccepted(amount)){
            // Số dư còn lại = số dư hiện tại - số tiền rút
            super.setBalance(super.getBalance() - amount);
            // Ghi log
            log(amount);
            // Thêm giao dịch vào danh sách giao dịch
            super.getTransactions().add(new Transaction(this.getAccountNumber(), amount, Utils.getDateTime(), true));
            
            return true;
        }

        return false;
    }

    /** Phương thức kiểm tra số tiền rút có hợp lệ hay không 
     * @param amount Số tiền muốn rút
     */
    @Override
    public boolean isAccepted(double amount) {
        // Nếu số tiền muốn rút lớn hơn 5.000.000d và không phải là tài khoản Premium thì không được rút 
        if(amount > 5000000 && !super.isPremiumAccount())
            return false;

        // Số tiền rút phải lớn hơn hoặc bằng 50.000đ
        // Số tiền rút phải là bội số của 10.000đ
        // Số dư còn lại sau khi rút phải lớn hơn hoặc bằng 50.000đ
        if (amount >= 50000 && amount % 10000 == 0 && super.getBalance() - amount > 50000)
            return true;

        return false;
    }
}
