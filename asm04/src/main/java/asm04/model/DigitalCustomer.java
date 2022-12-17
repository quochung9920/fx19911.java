package asm04.model;

import java.util.List;
import java.util.Scanner;

import asm02.models.Account;
import asm02.models.Bank;
import asm02.models.Customer;
import asm04.common.Utils;
import asm04.dao.AccountDao;

/**
 * Class DigitalCustomer
 * Kế thừa từ class Customer
 */
public class DigitalCustomer extends Customer {

    /** Phương thức khởi tạo */
    public DigitalCustomer() {
        super();
    }

    public DigitalCustomer(String name, String customerId) {
        super(name, customerId);
    }

    public DigitalCustomer(List<String> values) {
        this(values.get(1), values.get(0));
    }

    /** Phương thức hiển thị thông tin khách hàng */
    @Override
    public void displayInformation() {
        System.out.println(this.toString());
        if (this.getAccounts().size() > 0) {
            for (int i = 0; i < this.getAccounts().size(); i++) {
                System.out.println((i + 1) + this.getAccounts().get(i).toString());
            }
        }
    }

    /** Phương thức tìm kiếm tài khoản theo mã tài khoản */
    public Account getAccountByAccountNumber(List<Account> accounts, String accountNumber) {
        return accounts.stream().filter(account -> account.getAccountNumber().equals(accountNumber)).findFirst()
                .orElse(null);
    }

    /** Phương thức rút tiền */
    public void withdraw(Scanner scanner) {
        List<Account> accounts = this.getAccounts();
        if(!accounts.isEmpty()){
            Account account;
            double amount;

            do {
                System.out.print("Nhap so tai khoan: ");
                account = this.getAccountByAccountNumber(accounts, scanner.nextLine());
            } while (account == null);

            do {
                System.out.print("Nhap so tien can rut: ");
                amount = scanner.nextDouble();
            } while (amount <= 0);

            if (account instanceof SavingsAccount) {
                ((SavingsAccount)account).withdraw(amount);
            } else if (account instanceof LoanAccount) {
                ((LoanAccount)account).withdraw(amount);
            } 
        } else {
            System.out.println("Khach hang khong co tai khoan nao, thao tac khong thanh cong");
        }
    }

    /** Phương thức in ra lịch sử giao dịch của tài khoản */
    public void displayTransactionHistory() {
        
    }

    /** Phương thức lấy danh sách tài khoản */
    @Override
    public List<Account> getAccounts() {
        return AccountDao.list().stream().filter(a -> a.getCustomerId().equals(this.getCustomerId())).toList();
    }

    @Override
    public String toString() {
        return this.getCustomerId() + " | " + String.format("%-32s", this.getName()) + " | "
                + String.format("%12s", (this.isPremium() ? "Premium" : "Normal")) + " | "
                + String.format("%22s",
                        Utils.formatAmount(this.getAccounts().stream().mapToDouble(a -> a.getBalance()).sum()));
    }

    /** Phương thức thêm tài khoản ATM */
    public Account addSavingAccount(Scanner scanner) {
        Account acount = new SavingsAccount();
        acount.input(scanner);
        acount.setCustomerId(this.getCustomerId());
        return acount;
    }

    /** Phương thức chuyển tiền */
    public void tranfers(Scanner scanner) {
        // Lấy danh sách tài khoản thuộc khách hàng này
        List<Account> accounts = this.getAccounts();

        // Nếu khách hàng có tài khoản
        if(!accounts.isEmpty()){
            Account account;
            String amount;
            String accountNumber;
            boolean isExist;
            Account accountReceive = null;
            String confirm;

            do {
                System.out.print("Nhap so tai khoan: ");
                account = this.getAccountByAccountNumber(accounts, scanner.nextLine());
            } while (account == null);

            do {
                System.out.print("Nhap so tai khoan nhan (exit de thoat): ");
                accountNumber = scanner.nextLine();
                if (accountNumber.toLowerCase().equals("exit")) {
                    return;
                }
                if (accountNumber.equals(account.getAccountNumber()) || accountNumber.isEmpty()) {
                    System.out.println("So tai khoan khong hop le");
                }
                List<Account> accounts2 = AccountDao.list();
                isExist = false;
                for (Account account2 : accounts2) {
                    if (account2.getAccountNumber().equals(accountNumber)) {
                        isExist = true;
                        accountReceive = account2;
                        break;
                    } 
                }
                if (!isExist) {
                    System.out.println("So tai khoan khong ton tai");
                }

            } while (accountNumber.isEmpty() || !isExist || accountNumber.equals(account.getAccountNumber()));

            Bank bank = new DigitalBank();
            System.out.println("Gui tien den so tai khoan: " + accountNumber + " | " + ((DigitalBank)bank).getCustomerByAccountId(accountNumber).getName());

            do {
                System.out.print("Nhap so tien chuyen: ");
                amount = scanner.nextLine();
                if(amount.isEmpty() || !amount.matches("[0-9]+") || Double.parseDouble(amount) <= 0){
                    System.out.println("So tien chuyen khong hop le");
                }

            } while (!amount.matches("[0-9]+") || amount.isEmpty() || Double.parseDouble(amount) <= 0);

            do{
                System.out.print("Xac nhan thuc hien chuyen " + amount + " tu tai khoan " + account.getAccountNumber() + " den tai khoan " + accountNumber + " (y/n): ");
            confirm = scanner.nextLine();
            } while (!confirm.toLowerCase().equals("y") && !confirm.toLowerCase().equals("n"));

            if (confirm.toLowerCase().equals("y")) {
                if (account instanceof SavingsAccount) {
                    ((SavingsAccount)account).transfer(accountReceive, Double.parseDouble(amount));
                } else if (account instanceof LoanAccount) {
                    // ((LoanAccount)account).tranfers(amount, accountNumber);
                } 
            } else {
                return;
            }

        } else {
            System.out.println("Khach hang khong co tai khoan nao, thao tac khong thanh cong");
        }
    }

}
