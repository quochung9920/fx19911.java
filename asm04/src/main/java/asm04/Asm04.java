package asm04;

import java.util.List;
import java.util.Scanner;

import asm02.models.Account;
import asm02.models.Customer;
import asm04.common.Utils;
import asm04.dao.CustomerDao;
import asm04.exception.CustomerIdNotValidException;
import asm04.model.DigitalBank;
import asm04.model.DigitalCustomer;
import asm04.model.LoanAccount;
import asm04.model.SavingsAccount;

public class Asm04 {
    private static final Scanner scanner = new Scanner(System.in);
    private static final int EXIT_COMMAND_CODE = 0;
    private static final int EXIT_ERROR_CODE = -1;
    private static final DigitalBank activeBank = new DigitalBank();
    private static final String CUSTOMER_ID = "067200005473";
    private static final String CUSTOMER_NAME = "Hung";

    private static final String AUTHOR = "FX19911";
    private static final String VERSION = "v4.0.0";

    /** Hàm hiển thị intro */
    public static void showIntro() {
        System.out.println("Phan mem quan ly tai khoan ngan hang so");
        System.out.println("Sinh vien: " + CUSTOMER_NAME);
        System.out.println("So CCCD: " + CUSTOMER_ID);
        System.out.println();
        System.out.println("Chuong trinh duoc viet boi: " + AUTHOR + " - " + VERSION);
        System.out.println("Chuong trinh da duoc kiem tra chay tren Java 17");
        System.out.println("Chuong trinh duoc viet theo huong doi tuong");
        System.out.println("Chuong trinh dang chay tren he dieu hanh " + System.getProperty("os.name"));
        System.out.println();
    }

    /** Hàm hiển thị menu chính */
    public static void showMainMenu() {
        System.out.println("+----------+--------------------+----------+");
        System.out.println("| NGAN HANG SO | " + AUTHOR + "@" + VERSION + "            |");
        System.out.println("+----------+--------------------+----------+");
        System.out.println(" 1. Danh sach khach hang");
        System.out.println(" 2. Nhap danh sach khach hang");
        System.out.println(" 3. Them tai khoan ATM");
        System.out.println(" 4. Chuyen tien");
        System.out.println(" 5. Rut tien");
        System.out.println(" 6. Tra cuu lich su giao dich");
        System.out.println(" 0. Thoat");
        System.out.println("+----------+--------------------+----------+");
        String choice = inputFunctionMain(scanner);
        parseFunction(scanner, choice);
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
                // Danh sách khách hàng
                showCustomers();
                break;
            case "2":
                // Thêm danh sách khách hàng
                addCustomers();
                break;
            case "3":
                // Thêm tài khoản ATM
                addAtmAccount();
                break;
            case "4":
                // Thêm tài khoản tín dụng
                depositMoney();
                break;
            case "5":
                // Rút tiền
                withdrawMoney();
                break;
            case "6":
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
    }

    /**
     * Hàm triển khai chức năng của menu chính
     * Khi người dùng nhập vào chức năng, hàm này sẽ gọi hàm parseFunction để triển
     * khai chức năng
     */
    public static String inputFunctionMain(Scanner scanner) {
        String choice = "";
        try {
            choice = inputFunction(scanner, "Nhap chuc nang: ");
            // Kiểm tra lựa chọn chức năng
            if (choice.matches("[0-6]") && !choice.isEmpty()) {
                return choice;
            } else {
                throw new Exception("Chuc nang khong hop le. Vui long nhap lai.");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return inputFunctionMain(scanner);
        }
    }

    /** Chức năng 1: Thông tin khách hàng */
    private static void showCustomers() {
        try {
            activeBank.showCustomers();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * Chức năng 2: Hàm thêm khách hàng vào ngân hàng
     * 
     * @param scanner Đối tượng Scanner để nhập dữ liệu từ bàn phím
     * 
     *                Sử dụng inputFunction để nhập thông tin
     *                Sử dụng addCustomerAndCheckCCCD để thêm khách hàng vào danh
     *                sách khách hàng của ngân hàng
     */
    public static void addCustomers() {
        String fileName = inputFunction(scanner, "Nhap duong dan den tep: \n");
        // Kiểm tra định dạng của tên file
        try {
            // Tên file phải có đuôi .txt
            if (fileName.matches(".*\\.txt")) {
                activeBank.addCustomers(fileName);

            } else {
                throw new Exception("Duong dan khong hop le");
            }
        } catch (Exception e) {
            // Thông báo lỗi
            System.out.println(e.getMessage());
            // Thêm khách hàng lại
            addCustomers();
        }

    }

    /** Check cccd */
    public static String checkCCCD() {
        String cccd = inputFunction(scanner, "Nhap ma so khach hang: \n");
        try {
            if (cccd.isEmpty()) {
                throw new CustomerIdNotValidException("Khong duoc de trong");
            } else {
                if (activeBank.isCustomerExisted(cccd)) {
                    Customer customer = new DigitalCustomer();
                    customer.setCustomerId(cccd);
                    return cccd;
                } else {
                    throw new CustomerIdNotValidException("Khong tim thay khach hang");
                }
            }
        } catch (CustomerIdNotValidException e) {
            System.out.println(e.getMessage());
            return checkCCCD();
        }

    }

    /** Chức năng 3: Thêm tài khoản ATM */
    private static void addAtmAccount() {
        try {
            String cccd = inputFunction(scanner, "Nhap ma so khach hang: \n");
            if (!activeBank.isCustomerExisted(cccd)) {
                throw new CustomerIdNotValidException("Khong tim thay khach hang " + cccd + ", tac vu khong thanh cong");
            } else {
                activeBank.addSavingAccount(scanner, cccd);
            }

        } catch (CustomerIdNotValidException e) {
            System.out.println(e.getMessage());
        }
    }

    /** Chức năng 4: Gửi tiền */
    private static void depositMoney() {
        try {
            String customer = checkCCCD();
            activeBank.showCustomerById(customer);
            Scanner scanner = new Scanner(System.in);
            activeBank.tranfers(scanner, customer);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /** Chức năng 5: Rút tiền */
    private static void withdrawMoney() {
        try {
            String cccd = checkCCCD();
            activeBank.withdraw(new Scanner(System.in), cccd);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    /** Chức năng 6: In ra lịch sử giao dịch */
    private static void showHistory() {
        String cccd = checkCCCD();
        activeBank.displayTransactionHistory(cccd);
    }

    public static void main(String[] args) {
        // showIntro();
        showMainMenu();
        inputFunctionMain(scanner);

    }
}
