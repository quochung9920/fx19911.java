import java.util.Scanner;

import models.Bank;
import models.Customer;
import models.User;

public class Asm02 {

    private static final Bank bank = new Bank();
    public static final String AUTHOR = "FX19911";
    public static final String VERSION = "2.0.0";
    // Init User có sẵn
    // public static User[] user = User.initUsers();

    /* Hàm hiển thị menu chính */
    public static void showMainMenu() {
        System.out.println("+----------+--------------------+----------+");
        System.out.println("| NGAN HANG SO | " + AUTHOR + "@" + VERSION + "             |");
        System.out.println("+----------+--------------------+----------+");
        System.out.println(" 1. Them khach hang                         ");
        System.out.println(" 2. Them tai khoan cho khach hang           ");
        System.out.println(" 3. Hien thi danh sach khach hang           ");
        System.out.println(" 4. Tim theo CCCD                           ");
        System.out.println(" 5. Tim theo ten khach hang                 ");
        System.out.println(" 0. Thoat                                   ");
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

    /** Hàm phân tích chức năng */
    public static void parseFunction(Scanner scanner, String choice) {
        switch (choice) {
            case "1":
                // Thêm khách hàng
                addCustomer(scanner);
                showMainMenu();
                inputFunctionMain(scanner);
                break;
            case "2":
                // Thêm tài khoản cho khách hàng
                break;
            case "3":
                // Hiển thị danh sách khách hàng
                break;
            case "4":
                // Tìm theo CCCD
                break;
            case "5":
                // Tìm theo tên khách hàng
                break;
            case "0":
                // Thoát chương trình
                System.out.println("Cam on ban da su dung dich vu cua chung toi");
                System.out.println("+----------+--------------------+----------+");
                System.exit(0);
                break;
        }
    }

    /** Hàm triển khai chức năng của menu chính */
    public static void inputFunctionMain(Scanner scanner) {
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

    public static void addCustomerAndCheckCCCD(Scanner scanner, String name, String cccd) {

        // Kiểm tra định dạng của CCCD
        try {
            // Nhập số CCCD từ bàn phím
            cccd = inputFunction(scanner, "Nhap so CCCD: \n");

            // Tạo đối tượng khách hàng
            Customer customer = new Customer(name, cccd);

            // Thêm khách hàng vào ngân hàng
            bank.addCustomer(customer);

            // Thông báo thêm khách hàng thành công
            System.out.println("Them khach hang thanh cong!");
        } catch (Exception e) {
            // Thông báo lỗi
            System.out.println(e.getMessage());
            // Thêm CCCD khách hàng lại
            addCustomerAndCheckCCCD(scanner, name, cccd);
        }
    }

    /** Hàm thêm khách hàng vào ngân hàng */
    public static void addCustomer(Scanner scanner) {
        String name = inputFunction(scanner, "Nhap ten khach hang: \n");
        String cccd = "";
        addCustomerAndCheckCCCD(scanner, name, cccd);
    }

    

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        showMainMenu();
        inputFunctionMain(scanner);

    }
}
