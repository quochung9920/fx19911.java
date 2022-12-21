package asm04.model;

import java.util.List;

import asm02.models.Account;
import asm04.common.Utils;
import asm04.dao.AccountDao;
import asm04.dao.TransactionDao;
import asm04.model.Transaction.TransactionType;
import asm04.service.ReportService;
import asm04.service.Transfer;
import asm04.service.Withdraw;

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
public class SavingsAccount extends Account implements ReportService, Withdraw, Transfer {

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
        return String.format("%11s",
        this.getAccountNumber()) + " | "
        +  String.format("%-32s", "SAVINGS") + " | "
        + String.format("%37s", Utils.formatAmount(this.getBalance()));
    }

    @Override
    public void log(double amount) {
        System.out.println(Utils.getDivider());
        System.out.printf("%32s%n", getTitle());
        System.out.printf("NGAY G/D: %28s%n", Utils.getDateTime());
        System.out.printf("ATM ID: %30s%n", "DIGITAL-BANK-ATM 2022");
        System.out.printf("SO TK: %31s%n", this.getAccountNumber());
        System.out.printf("SO TIEN: %29s%n", Utils.formatBalance(amount));
        System.out.printf("SO DU: %31s%n", Utils.formatBalance(this.getBalance()));
        System.out.printf("PHI + VAT: %27s%n", Utils.formatBalance(0.0));
        System.out.println(Utils.getDivider());
    }

    @Override
    public void log(double amount , TransactionType type, String receiveAccount) {
        System.out.println(Utils.getDivider());
        System.out.printf("%32s%n", getTitle());
        System.out.printf("NGAY G/D: %28s%n", Utils.getDateTime());
        System.out.printf("ATM ID: %30s%n", "DIGITAL-BANK-ATM 2022");
        System.out.printf("SO TK: %31s%n", this.getAccountNumber());
        System.out.printf("SO TK: %31s%n", receiveAccount);
        System.out.printf("SO TIEN CHUYEN: %22s%n", Utils.formatBalance(amount));
        System.out.printf("SO DU: %31s%n", Utils.formatBalance(this.getBalance()));
        System.out.printf("PHI + VAT: %27s%n", Utils.formatBalance(0.0));
        System.out.println(Utils.getDivider());
    }

    /** Phương thức rút tiền 
     * @param amount Số tiền muốn rút
     */
    @Override
    public synchronized boolean withdraw(double amount) {
        // Nếu số tiền rút hợp lệ thì thực hiện rút tiền và ghi log
        if(isAcceptedWithdraw(amount)){
            // Số dư còn lại = số dư hiện tại - số tiền rút
            this.setBalance(this.getBalance() - amount); 
            // Ghi log
            log(amount);
            // Thêm giao dịch vào danh sách giao dịch
            Transaction transaction = new Transaction(this.getAccountNumber(), amount, Utils.getDateTime(), true, TransactionType.WITHDRAW);
            List<Transaction> transactions = TransactionDao.list();
            transactions.add(transaction);

            // Lưu danh sách giao dịch vào file
            TransactionDao.save(transactions);

            // Cập nhật lại số dư vào file
            AccountDao.update(this);

            System.out.println("Rut tien thanh cong");
            return true;
        } else {
            throw new RuntimeException("Rut tien khong thanh cong");
        }
    }

    /** Phương thức kiểm tra số tiền rút có hợp lệ hay không 
     * @param amount Số tiền muốn rút
     */
    @Override
    public boolean isAcceptedWithdraw(double amount) {
        // Nếu số tiền muốn rút lớn hơn 5.000.000d và không phải là tài khoản Premium thì không được rút 
        if(amount > 5000000 && !super.isPremiumAccount())
            return false;

        // Số tiền rút phải lớn hơn hoặc bằng 50.000đ
        // Số tiền rút phải là bội số của 10.000đ
        // Số dư còn lại sau khi rút phải lớn hơn hoặc bằng 50.000đ
        if (amount >= 50000 && amount % 10000 == 0 && this.getBalance() - amount > 50000)
            return true;

        return false;
    }

    /** Phương thức kiểm tra số tiền chuyển có hợp lệ hay không 
     * @param amount Số tiền muốn rút
     */
    @Override
    public boolean isAcceptedTransfer(double amount) {

        // Số tiền chuyển phải là bội số của 10.000đ
        // Số dư còn lại sau khi chuyển phải lớn hơn hoặc bằng 50.000đ
        if (amount % 10000 == 0 && this.getBalance() - amount > 50000)
            return true;

        return false;
    }

    /** Phương thức chuyển tiền 
     * @param receiveAccount Tài khoản nhận tiền
     * @param amount Số tiền muốn chuyển
     */
    @Override
    public void transfer(Account receiveAccount, double amount) {
        // Nếu số tiền chuyển hợp lệ thì thực hiện chuyển tiền và ghi log
        if (isAcceptedTransfer(amount)) {
            // Câp nhật số dư tài khoản chuyển tiền
            this.setBalance(this.getBalance() - amount);
            // Câp nhật số dư tài khoản nhận tiền
            receiveAccount.setBalance(receiveAccount.getBalance() + amount);
            // Update tài khoản chuyển tiền
            AccountDao.update(this);

            // Thêm giao dịch của tài khoản chuyển tiền vào danh sách giao dịch
            Transaction transaction = new Transaction(this.getAccountNumber(), amount, Utils.getDateTime(), true, TransactionType.TRANSFER);
            List<Transaction> transactions = TransactionDao.list();
            transactions.add(transaction);

            // Update tài khoản nhận tiền
            AccountDao.update(receiveAccount);

            // Thêm giao dịch của tài khoản nhận tiền vào danh sách giao dịch
            Transaction transactionReceive = new Transaction(receiveAccount.getAccountNumber(), amount, Utils.getDateTime(), true, TransactionType.DEPOSIT);
            transactions.add(transactionReceive);

            // Lưu danh sách giao dịch
            TransactionDao.save(transactions);

            // Ghi log
            log(amount, TransactionType.TRANSFER, receiveAccount.getAccountNumber());
            System.out.println("Chuyen tien thanh cong");
        } else {
            throw new RuntimeException("Chuyen tien khong thanh cong");
        }
        
    }
    
}
