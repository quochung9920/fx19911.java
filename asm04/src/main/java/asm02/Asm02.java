package asm02;

import java.util.List;

import java.util.Scanner;

import asm02.models.Account;
import asm02.models.Bank;
import asm02.models.Customer;

public class Asm02 {

    private static final Bank bank = new Bank();
    private static final String AUTHOR = "FX19911";
    private static final String VERSION = "2.0.0";

    /** Hàm hiển thị menu chính */
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

    /** Hàm lựa chọn chức năng của menu chính */
    public static void parseFunction(Scanner scanner, String choice) {
        switch (choice) {
            case "1":
                // Thêm khách hàng
                addCustomer(scanner);
                break;
            case "2":
                // Thêm tài khoản cho khách hàng
                addAccountForCustomer(scanner);
                break;
            case "3":
                // Hiển thị danh sách khách hàng
                showCustomers();
                break;
            case "4":
                // Tìm theo CCCD
                searchCustomerByCCCD(scanner);
                break;
            case "5":
                // Tìm theo tên khách hàng
                findCustomerByName(scanner);
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

    /**
     * Hàm nhập thông tin khách hàng: CCCD
     * 
     * @param scanner Đối tượng Scanner để nhập dữ liệu từ bàn phím
     * @param name    Tên của khách hàng
     * 
     *                Sử dụng inputFunction để nhập thông tin
     *                Sử dụng addCustomer để thêm khách hàng vào danh sách khách
     *                hàng của ngân hàng
     */
    public static void addCustomerAndCheckCCCD(Scanner scanner, String name) {

        // Kiểm tra định dạng của CCCD
        try {
            // Nhập số CCCD từ bàn phím
            String cccd = inputFunction(scanner, "Nhap so CCCD: \n");

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
            addCustomerAndCheckCCCD(scanner, name);
        }
    }

    /** Hàm kiểm tra tài khoản khách hàng */
    public static Account checkNumberAccountInformation(Scanner scanner, Account newAccount){
        String numberAccount = inputFunction(scanner, "Nhap so tai khoan: \n");

        // Kiểm tra định dạng của số tài khoản
        try {
            // Gán số tài khoản cho đối tượng tài khoản
            newAccount.setAccountNumber(numberAccount);

            bank.isAccountExisted(numberAccount);
        } catch (Exception e) {
            // Thông báo lỗi
            System.out.println(e.getMessage());
            // Thêm tài khoản khách hàng lại
            checkNumberAccountInformation(scanner, newAccount);
        }
        return newAccount;
    }

    /** Hàm kiểm tra số tiền khách hàng muốn gửi */
    public static Account checkBalanceInformation(Scanner scanner, Account newAccount){
        String balance = inputFunction(scanner, "Nhap so du: \n");

        // Kiểm tra định dạng của số tiền
        try {
            // Gán số tiền cho đối tượng tài khoản
            newAccount.setBalance(Double.parseDouble(balance));
        } catch (Exception e) {
            // Thông báo lỗi
            System.out.println(e.getMessage());
            // Thêm tài khoản khách hàng lại
            checkBalanceInformation(scanner, newAccount);
        }
        return newAccount;
    }

    /**
     * Hàm kiểm tra thông tin tài khoản khách hàng
     * 
     * @param scanner  Đối tượng Scanner để nhập dữ liệu từ bàn phím
     * @param customer Đối tượng khách hàng
     * 
     *                 Sử dụng inputFunction để nhập thông tin
     *                 Sử dụng addAccountForCustomer để thêm tài khoản cho khách
     *                 hàng
     */
    public static void checkAccountInformation(Scanner scanner, Customer customer, String cccd) {
        // Tạo đối tượng tài khoản
        Account newAccount = new Account();

        // Set số tài khoản cho đối tượng tài khoản
        newAccount = checkNumberAccountInformation(scanner, newAccount);
        // Set số tiền cho đối tượng tài khoản
        newAccount = checkBalanceInformation(scanner, newAccount);

        try {
            // Thêm tài khoản cho khách hàng
            bank.addAccount(cccd, newAccount);
            System.out.println("Them tai khoan thanh cong!");
        } catch (Exception e) {
            // Thông báo lỗi
            System.out.println(e.getMessage());
            // Thêm thông tin tài khoản khách hàng lại
            checkAccountInformation(scanner, customer, cccd);
        }
    }

    /**
     * Chức năng 1: Hàm thêm khách hàng vào ngân hàng
     * 
     * @param scanner Đối tượng Scanner để nhập dữ liệu từ bàn phím
     * 
     *                Sử dụng inputFunction để nhập thông tin
     *                Sử dụng addCustomerAndCheckCCCD để thêm khách hàng vào danh
     *                sách khách hàng của ngân hàng
     */
    public static void addCustomer(Scanner scanner) {

        // Nhập tên khách hàng từ bàn phím
        String name = inputFunction(scanner, "Nhap ten khach hang: \n");

        // Kiểm tra dữ liệu nhập vào và thêm khách hàng vào ngân hàng
        addCustomerAndCheckCCCD(scanner, name);
    }

    /**
     * Chức năng 2: Hàm thêm tài khoản cho khách hàng
     * 
     * @param scanner Đối tượng Scanner để nhập dữ liệu từ bàn phím
     * 
     *                Sử dụng inputFunction để nhập thông tin
     *                Sử dụng checkAccountInformation để kiểm tra thông tin tài
     *                khoản khách hàng
     *                Sử dụng addAccountForCustomer để nhâp lại thông tin tài khoản
     *                khách hàng
     */
    public static void addAccountForCustomer(Scanner scanner) {

        // Nhập số CCCD của khách hàng
        String cccd = inputFunction(scanner, "Nhap CCCD khach hang: \n");

        // Tìm khách hàng theo số CCCD
        Customer customer = bank.searchCustomerByCCCD(cccd);

        // Kiểm tra khách hàng có tồn tại không
        if (customer != null) {
            checkAccountInformation(scanner, customer, cccd);
        } else {
            System.out.println("Khong tim thay khach hang! Vui long kiem tra lai.");
            addAccountForCustomer(scanner);
        }
    }

    /** Chức năng 3: Hàm hiển thị danh sách khách hàng */
    public static void showCustomers() {
        System.out.println("Danh sach khach hang: ");
        bank.getCustomers().forEach(customer -> {
            // Hiển thị thông tin 1 khách hàng
            customer.displayInformation();
        });
    }

    /**
     * Chức năng 4: Hàm tìm khách hàng theo CCCD
     * 
     * @param scanner Đối tượng Scanner để nhập dữ liệu từ bàn phím
     * 
     *                Sử dụng inputFunction để nhập thông tin
     *                Sử dụng searchCustomerByCCCD để tìm khách hàng theo số CCCD
     *                Sử dụng showCustomerInformation để hiển thị thông tin khách
     *                hàng
     * 
     * @return In ra thông tin khách hàng tìm được
     */
    public static void searchCustomerByCCCD(Scanner scanner) {

        // Nhập số CCCD của khách hàng
        String cccd = inputFunction(scanner, "Nhap CCCD khach hang: \n");

        // Tìm khách hàng theo số CCCD
        Customer customer = bank.searchCustomerByCCCD(cccd);

        // Hiển thị thông tin khách hàng
        if (customer != null) {
            customer.displayInformation();
        } else {
            System.out.println("Khong tim thay khach hang!");
        }
    }

    /**
     * Chức năng 5: Hàm tìm khách hàng theo tên
     * 
     * @param scanner Đối tượng Scanner để nhập dữ liệu từ bàn phím
     * 
     *                Sử dụng inputFunction để nhập thông tin
     *                Sử dụng searchCustomerByName để tìm khách hàng theo tên
     * 
     * @return In ra danh sách khách hàng tìm được
     */
    public static void findCustomerByName(Scanner scanner) {
        // Nhập tên khách hàng
        String name = inputFunction(scanner, "Nhap ten khach hang: \n");

        // Tìm khách hàng theo tên
        List<Customer> customers = bank.findCustomerByName(name);

        // Hiển thị thông tin khách hàng
        if (customers.size() > 0) {
            customers.forEach(customer -> {
                customer.displayInformation();
            });
        } else {
            System.out.println("Khong tim thay khach hang!");
        }
    }

    public static void main(String[] args) {

        // Dữ liệu mẫu, muốn sử dụng dữ liệu mẫu thì bỏ comment
        //  bank.addCustomer(new Customer("Hung", "067200005473"));
        //  bank.addCustomer(new Customer("Hoa", "067200005474"));
        //  bank.addCustomer(new Customer("Huong", "067200005475"));
        //  bank.addCustomer(new Customer("Tien", "067200005476"));
        //  bank.addCustomer(new Customer("Hai", "067200005477"));
        //  bank.addCustomer(new Customer("Hien", "067200005478"));
        //  bank.addCustomer(new Customer("Hong", "067200005479"));
        //  Customer customer = bank.searchCustomerByCCCD("067200005473");
        //  Account account1 = new Account();
        //  account1.setAccountNumber("123456");
        //  account1.setBalance(1000000);
        //  customer.addAccount(account1);
        //  Account account2 = new Account();
        //  account2.setAccountNumber("123457");
        //  account2.setBalance(2000000);
        //  customer.addAccount(account2);
        //  Account account3 = new Account();
        //  account3.setAccountNumber("123458");
        //  account3.setBalance(3000000);
        //  customer.addAccount(account3);
        //  Account account4 = new Account();
        //  account4.setAccountNumber("123459");
        //  account4.setBalance(4000000);
        //  customer.addAccount(account4);
        //  Account account5 = new Account();
        //  account5.setAccountNumber("123460");
        //  account5.setBalance(5000000);
        //  Customer customer1 = bank.searchCustomerByCCCD("067200005474");
        //  Account account6 = new Account();
        //  account6.setAccountNumber("123461");
        //  account6.setBalance(6000000);
        //  customer1.addAccount(account6);
        //  Account account7 = new Account();
        //  account7.setAccountNumber("123462");
        //  account7.setBalance(7000000);
        //  customer1.addAccount(account7);
        //  Account account8 = new Account();
        //  account8.setAccountNumber("123463");
        //  account8.setBalance(8000000);
        //  customer1.addAccount(account8);
        //  Customer customer2 = bank.searchCustomerByCCCD("067200005475");
        //  Account account9 = new Account();
        //  account9.setAccountNumber("123464");
        //  account9.setBalance(9000000);
        //  customer2.addAccount(account9);
        //  Account account10 = new Account();
        //  account10.setAccountNumber("123465");
        //  account10.setBalance(10000000);
        //  customer2.addAccount(account10);
        //  Customer customer3 = bank.searchCustomerByCCCD("067200005476");
        //  Account account11 = new Account();
        //  account11.setAccountNumber("123466");
        //  account11.setBalance(11000000);
        //  customer3.addAccount(account11);
        //  Account account12 = new Account();
        //  account12.setAccountNumber("123467");
        //  account12.setBalance(12000000);
        //  customer3.addAccount(account12);
        //  Account account13 = new Account();
        //  account13.setAccountNumber("123468");
        //  account13.setBalance(13000000);
        //  customer3.addAccount(account13);
        //  Account account14 = new Account();
        //  account14.setAccountNumber("123469");
        //  account14.setBalance(14000000);
        //  customer3.addAccount(account14);
        //  Account account15 = new Account();
        //  account15.setAccountNumber("123470");
        //  account15.setBalance(15000000);
        //  customer3.addAccount(account15);
        //  Account account16 = new Account();
        //  account16.setAccountNumber("123471");
        //  account16.setBalance(16000000);
        //  customer3.addAccount(account16);
        //  Customer customer4 = bank.searchCustomerByCCCD("067200005477");
        //  Account account17 = new Account();
        //  account17.setAccountNumber("123472");
        //  account17.setBalance(17000000);
        //  customer4.addAccount(account17);
        //  Account account18 = new Account();
        //  account18.setAccountNumber("123473");
        //  account18.setBalance(18000000);
        //  customer4.addAccount(account18);
        //  Customer customer6 = bank.searchCustomerByCCCD("067200005479");
        //  Account account19 = new Account();
        //  account19.setAccountNumber("123474");
        //  account19.setBalance(19000000);
        //  customer6.addAccount(account19);
        //  Account account20 = new Account();
        //  account20.setAccountNumber("123475");
        //  account20.setBalance(20000000);
        //  customer6.addAccount(account20);

        Scanner scanner = new Scanner(System.in);

        showMainMenu();
        inputFunctionMain(scanner);
    }
}
