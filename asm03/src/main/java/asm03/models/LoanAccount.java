package asm03.models;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import asm02.models.Account;
import asm03.interfaces.ReportService;
import asm03.interfaces.Withdraw;

import asm03.Utils.Utils;

/**
 * Class LoanAccount
 * Kế thừa từ class Account
 * Implements interface ReportService, Withdraw
 * Có thuộc tính: 
 * - LOAN_ACCOUNT_WITHDRAW_FEE: phí rút tiền 
 * - LOAN_ACCOUNT_WITHDRAW_PREMIUM_FEE: phí rút tiền cho tài khoản premium
 * - LOAN_ACCOUNT_MAX_BALANCE: số tiền tối đa có thể rút
 * - title: tiêu đề biên lai
 * - feeWithdraw: phí rút tiền
 * - totalAccountBalance: tổng số tiền đã rút trong tài khoản
 * Có phương thức:
 * - getFee(): trả về phí rút tiền
 * - getTitle(): trả về tiêu đề biên lai
 * - getTotalAccountBalance(): trả về tổng số tiền đã rút trong tài khoản
 * - setTotalAccountBalance(): cập nhật tổng số tiền đã rút trong tài khoản
 * - withdraw(): rút tiền
 * - printReport(): in biên lai
 */
public class LoanAccount extends Account implements ReportService, Withdraw {

    // Thuộc tính
    private static final double LOAN_ACCOUNT_WITHDRAW_FEE = 0.05;
    private static final double LOAN_ACCOUNT_WITHDRAW_PREMIUM_FEE = 0.01;
    private static final double LOAN_ACCOUNT_MAX_BALANCE = 100000000;
    private String title = "BIEN LAI GIAO DICH LOAN";
    private double feeWithdraw;

    /** Phương thức khởi tạo */
    public LoanAccount() {
        super();
        super.setBalance(LOAN_ACCOUNT_MAX_BALANCE);
    }

    /** Phương thức khởi tạo có tham số */
    public LoanAccount(String accountNumber) {
        super(accountNumber, LOAN_ACCOUNT_MAX_BALANCE);
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
        System.out.printf("PHI + VAT: %27s%n", Utils.formatBalance(feeWithdraw));
        System.out.println(Utils.getDivider());
    }

    /** Phương thức rút tiền
     *  @param amount Số tiền cần rút
     * @return true nếu rút thành công, false nếu rút thất bại
     */
    @Override
    public boolean withdraw(double amount) {
        if(isAccepted(amount)){
            // Cập nhật phí rút tiền
            feeWithdraw = this.getFee() * amount;
            // Cập nhật số dư tài khoản
            super.setBalance(super.getBalance() - amount - feeWithdraw);
            // Ghi log giao dịch
            log(amount);
            // Thêm giao dịch vào danh sách giao dịch
            super.getTransactions().add(new Transaction(this.getAccountNumber(), amount, Utils.getDateTime(), true));
            
            return true;
        }

        return false;
    }

    /** Phương thức kiểm tra số tiền vay có hợp lệ hay không 
     * @param amount Số tiền cần vay
     * @return true nếu hợp lệ, false nếu không hợp lệ
     */
    @Override
    public boolean isAccepted(double amount) {
        // Kiểm tra số tiền vay có vượt quá giới hạn cho phép không
        if (this.getBalance() + amount <= 0) {
            throw new IllegalArgumentException("So tien vay vuot qua gioi han cho phep");
        }

        // Kiểm tra số dư tài khoản có đủ để thực hiện giao dịch không
        if (super.getBalance() - amount - this.getFee() * amount < 50000) {
            return false;
        }

        return true;
    }
}
