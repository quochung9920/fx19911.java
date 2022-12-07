import java.util.List;
import java.util.Scanner;

import models.Account;
import models.Customer;
import models.DigitalBank;
import models.DigitalCustomer;
import models.LoanAccount;
import models.SavingsAccount;

public class Asm03 {

    private static final Scanner scanner = new Scanner(System.in);
    private static final int EXIT_COMMAND_CODE = 0;
    private static final int EXIT_ERROR_CODE = -1;
    private static final DigitalBank activeBank = new DigitalBank();
    private static final String CUSTOMER_ID = "067200005473";
    private static final String CUSTOMER_NAME = "Hung";

    private static final String AUTHOR = "FX19911";
    private static final String VERSION = "v3.0.0";

    /** Hàm hiển thị menu chính */
    public static void showMainMenu() {
        System.out.println("+----------+--------------------+----------+");
        System.out.println("| NGAN HANG SO | " + AUTHOR + "@" + VERSION + "            |");
        System.out.println("+----------+--------------------+----------+");
        System.out.println(" 1. Thong tin khach hang");
        System.out.println(" 2. Them tai khoan ATM");
        System.out.println(" 3. Them tai khoan tin dung");
        System.out.println(" 4. Rut tien");
        System.out.println(" 5. Lich su giao dich");
        System.out.println(" 0. Thoat");
        System.out.println("+----------+--------------------+----------+");
    }

    /** Hàm nhập chức năng */
    public static String inputFunction(Scanner scanner, String message) {
        System.out.print(message);
        System.out.print("\033[32m");
        String choice = scanner.nextLine();
        System.out.print("\033[0m");
        return choice;
    }

    /** Hàm lựa chọn chức năng của menu chính */
    public static void parseFunction(Scanner scanner, String choice) {
        switch (choice) {
            case "1":
                // Thông tin khách hàng
                showCustomer();
                break;
            case "2":
                // Thêm tài khoản ATM
                addAtmAccount();
                break;
            case "3":
                // Thêm tài khoản tín dụng
                addCreditAccount();
                break;
            case "4":
                // Rút tiền
                withdrawMoney();
                break;
            case "5":
                // Lịch sử giao dịch
                showHistory();
                break;
            case "0":
                // Thoát chương trình
                System.out.println("Cam on ban da su dung dich vu cua chung toi");
                System.out.println("+----------+--------------------+----------+");
                System.exit(0);
                break;
        }
        // Sau khi thực hiện xong chức năng, hiển thị menu chính
        showMainMenu();
        inputFunctionMain(scanner);
    }

    /**
     * Hàm triển khai chức năng của menu chính
     * Khi người dùng nhập vào chức năng, hàm này sẽ gọi hàm parseFunction để triển
     * khai chức năng
     */
    public static void inputFunctionMain(Scanner scanner) {
        while (true) {
            String choice;
            do {

                // Nhập lựa chọn chức năng từ bàn phím
                choice = inputFunction(scanner, "Chuc nang: ");

                // Kiểm tra lựa chọn chức năng
                if (choice.matches("[0-5]")) {
                    parseFunction(scanner, choice);
                } else {
                    System.out.println("Chuc nang khong hop le. Vui long nhap lai.");
                }
            } while (!choice.matches("[0-5]"));
        }
    }


    /** Hàm kiểm tra tài khoản ATM khách hàng */
    public static SavingsAccount checkNumberAccountATM(SavingsAccount newAccount) {
        String numberAccount = inputFunction(scanner, "Nhap ma so tai khoan gom 6 chu so: \n");

        // Kiểm tra định dạng của số tài khoản
        try {
            // Gán số tài khoản cho đối tượng tài khoản
            newAccount.setAccountNumber(numberAccount);

            activeBank.isAccountExisted(numberAccount);
        } catch (Exception e) {
            // Thông báo lỗi
            System.out.println(e.getMessage());
            // Thêm tài khoản khách hàng lại
            checkNumberAccountATM(newAccount);
        }
        return newAccount;
    }

    /** Hàm kiểm tra số tiền trong ATM */
    public static SavingsAccount checkBalanceAccountATM(SavingsAccount newAccount) {
        String balance = inputFunction(scanner, "Nhap so du: \n");

        // Kiểm tra định dạng của số tiền
        try {
            // Gán số tiền cho đối tượng tài khoản
            newAccount.setBalance(Double.parseDouble(balance));
        } catch (Exception e) {
            // Thông báo lỗi
            System.out.println(e.getMessage());
            // Thêm tài khoản khách hàng lại
            checkBalanceAccountATM(newAccount);
        }
        return newAccount;
    }

    /** Hàm kiểm tra tài khoản Credit khách hàng */
    public static LoanAccount checkNumberCreditAccount(LoanAccount newAccount) {
        String numberAccount = inputFunction(scanner, "Nhap ma so tai khoan gom 6 chu so: \n");

        // Kiểm tra định dạng của số tài khoản
        try {
            // Gán số tài khoản cho đối tượng tài khoản
            newAccount.setAccountNumber(numberAccount);

            activeBank.isAccountExisted(numberAccount);
        } catch (Exception e) {
            // Thông báo lỗi
            System.out.println(e.getMessage());
            // Thêm tài khoản khách hàng lại
            checkNumberCreditAccount(newAccount);
        }
        return newAccount;
    }

    /** Hàm kiểm tra số tiền trong Credit */
    public static LoanAccount checkBalanceCreditAccount(LoanAccount newAccount) {
        String balance = inputFunction(scanner, "Nhap so du: \n");

        // Kiểm tra định dạng của số tiền
        try {
            // Gán số tiền cho đối tượng tài khoản
            newAccount.setBalance(Double.parseDouble(balance));
        } catch (Exception e) {
            // Thông báo lỗi
            System.out.println(e.getMessage());
            // Thêm tài khoản khách hàng lại
            checkBalanceCreditAccount(newAccount);
        }
        return newAccount;
    }

    
    /** Hàm kiểm tra tài khoản khách hàng */
    public static Account checkNumberAccount(Account newAccount) {
        String numberAccount = inputFunction(scanner, "Nhap so tai khoan: \n");

        // Kiểm tra định dạng của số tài khoản
        try {
            // Néu tài khoản tồn tại thì gán số tài khoản cho đối tượng tài khoản
            if (activeBank.isAccountExistedNotThrow(numberAccount)) {
                // Gán số tài khoản cho đối tượng tài khoản
                newAccount.setAccountNumber(numberAccount);
            } else {
                throw new Exception("Tai khoan khong ton tai");
            }

        } catch (Exception e) {
            // Thông báo lỗi
            System.out.println(e.getMessage());
            // Thêm tài khoản khách hàng lại
            checkNumberAccount(newAccount);
        }
        return newAccount;
    }

    /** Hàm kiểm tra định dạng số tiền rút ra */
    public static String checkAmount() {

        // Nhập số tiền rút ra
        String amount = inputFunction(scanner, "Nhap so tien muon rut: \n");

        try {
            // chuỗi số tiền nhập vào phải là số và không được để trống và phải lớn hơn 0
            if (amount.matches("[0-9]+") && !amount.isEmpty() && Double.parseDouble(amount) > 0) {
                return amount;
            } else {
                throw new NumberFormatException("So tien khong hop le");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            checkAmount();
        }

        return amount;
    }

    /** Chức năng 1: Thông tin khách hàng */
    private static void showCustomer() {

        Customer customer = activeBank.getCustomerById(CUSTOMER_ID);

        if (customer != null) {
            customer.displayInformation();
        }

    }

    /** Chức năng 2: Thêm tài khoản ATM */
    private static void addAtmAccount() {

        try {
            // Tạo đối tượng tài khoản
            SavingsAccount newAccount = new SavingsAccount();

            // Nhập và kiểm tra số tài khoản
            newAccount = checkNumberAccountATM(newAccount);

            // Nhập và kiểm tra số tiền
            newAccount = checkBalanceAccountATM(newAccount);

            // Thêm tài khoản vào danh sách tài khoản của khách hàng
            activeBank.addAccount(CUSTOMER_ID, newAccount);

            // Thông báo thêm tài khoản thành công
            System.out.println("Them tai khoan ATM thanh cong");
        } catch (Exception e) {
            System.out.println("Them tai khoan ATM that bai");
            System.out.println(e.getMessage());
        }

    }

    /** Chức năng 3: Thêm tài khoản tín dụng */
    private static void addCreditAccount() {
        try {
            // Tạo đối tượng tài khoản
            LoanAccount newAccount = new LoanAccount();

            // Nhập và kiểm tra số tài khoản
            newAccount = checkNumberCreditAccount(newAccount);

            // Nhập và kiểm tra số tiền
            newAccount = checkBalanceCreditAccount(newAccount);

            // Thêm tài khoản vào danh sách tài khoản của khách hàng
            activeBank.addAccount(CUSTOMER_ID, newAccount);

            // Thông báo thêm tài khoản thành công
            System.out.println("Them tai khoan Credit thanh cong");

        } catch (Exception e) {
            System.out.println("Them tai khoan Credit that bai");
            System.out.println(e.getMessage());
        }
    }


    /** Chức năng 4: Rút tiền */
    private static void withdrawMoney() {
        Customer customer = activeBank.getCustomerById(CUSTOMER_ID);

        // Nhập số tài khoản
        Account numberAccount = checkNumberAccount(new Account());

        // Kiểm tra số tiền có đúng định dạng không
        try {
            // Nhập số tiền muốn rút
            String amount = checkAmount();
            // Rút tiền
            activeBank.withdraw(customer.getCustomerId(), numberAccount.getAccountNumber(), Double.parseDouble(amount));
            // Thông báo rút tiền thành công
            System.out.println("Rut tien thanh cong");

        } catch (Exception e) {
            // Thông báo lỗi
            System.out.println("Rut tien that bai");
            System.out.println("Loi: " + e.getMessage());
        }
    }

    /** Chức năng 5: In ra lịch sử giao dịch */
    private static void showHistory() {
        // // Nhập số tài khoản
        // Account numberAccount = checkNumberAccount(new Account());

        // In ra lịch sử giao dịch
        activeBank.displayTransactionHistory(CUSTOMER_ID);
    }

    public static void main(String[] args) {

        // Dữ liệu mẫu, muốn sử dụng dữ liệu mẫu thì bỏ comment
        activeBank.addCustomer(CUSTOMER_NAME, CUSTOMER_ID);
        activeBank.addAccount(CUSTOMER_ID, new SavingsAccount("123451", 1000000));
        activeBank.addAccount(CUSTOMER_ID, new LoanAccount("123452", 12000000));
        activeBank.addAccount(CUSTOMER_ID, new SavingsAccount("123453", 3000000));
        activeBank.addAccount(CUSTOMER_ID, new LoanAccount("123454", 5000000));
        activeBank.addAccount(CUSTOMER_ID, new SavingsAccount("123455", 7000000));
        activeBank.addAccount(CUSTOMER_ID, new LoanAccount("123456", 9000000));
        activeBank.addAccount(CUSTOMER_ID, new SavingsAccount("123457", 11000000));
        activeBank.addAccount(CUSTOMER_ID, new LoanAccount("123458", 13000000));
        activeBank.addAccount(CUSTOMER_ID, new SavingsAccount("123459", 15000000));

        showMainMenu();
        inputFunctionMain(scanner);
    }
}
