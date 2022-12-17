package asm04.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;

import asm02.models.Account;
import asm02.models.Bank;
import asm02.models.Customer;
import asm04.common.Utils;
import asm04.dao.AccountDao;
import asm04.dao.CustomerDao;
import asm04.service.TextFileService;

/**
 * Class DigitalBank
 * Kế thừa từ class Bank
 */
public class DigitalBank extends Bank {

    /** Phương thức khởi tạo */
    public DigitalBank() {
        super();
    }

    /**
     * Tìm kiếm khách hàng theo mã khách hàng
     * 
     * @param customerId mã khách hàng
     * @return trả về khách hàng nếu tìm thấy, trả về null nếu không tìm thấy
     */
    public Customer getCustomerById(String customerId) {
        return CustomerDao.list().stream().filter(customer -> customer.getCustomerId().equals(customerId)).findFirst()
                .orElse(null);
    }

    /**
     * Tìm kiếm khách hàng theo mã khách hàng
     * 
     * @param customerId mã khách hàng
     * @return trả về khách hàng nếu tìm thấy, trả về null nếu không tìm thấy
     */
    public Customer getCustomerById(List<Customer> customerList, String customerId) {
        return customerList.stream().filter(customer -> customer.getCustomerId().equals(customerId)).findFirst()
                .orElse(null);
    }

    /**
     * Phương thức thêm khách hàng
     * 
     * @param customerId mã khách hàng
     * @param name       tên khách hàng
     */
    public void addCustomer(String name, String customerId) {
        // Kiểm tra xem mã khách hàng đã tồn tại chưa
        if (getCustomerById(customerId) != null) {
            throw new IllegalArgumentException("Khach hang da ton tai");
        } else {
            // Thêm khách hàng mới
            super.getCustomers().add(new DigitalCustomer(name, customerId));
        }
    }

    /**
     * Phương thức kiểm tra tài khoản có tồn tại hay không
     * 
     * @param accountNumber số tài khoản
     * @return trả về true nếu tài khoản tồn tại, trả về false nếu không tồn tại
     */
    public boolean isAccountExistedNotThrow(String accountNumber) {
        for (Customer customer : super.getCustomers()) {
            for (Account account : customer.getAccounts()) {
                if (account.getAccountNumber().equals(accountNumber)) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Phương thức in ra lịch sử giao dịch của khách hàng
     * 
     * @param customerId mã khách hàng
     */
    public void displayTransactionHistory(String customerId) {
        // Kiểm tra xem khách hàng có tồn tại không
        Customer customer = getCustomerById(customerId);
        if (customer == null) {
            throw new IllegalArgumentException("Khach hang khong ton tai");
        } else {
            ((DigitalCustomer) customer).displayTransactionHistory();
        }

    }

    /**
     * Phương thức in ra danh sách khách hàng
     * 
     * @param customers danh sách khách hàng
     * @return trả về danh sách khách hàng
     */
    public void showCustomers() {
        List<Customer> customers = CustomerDao.list();
        if (customers.size() > 0) {
            for (Customer customer : customers) {
                showCustomerById(customer.getCustomerId());
            }
        } else {
            System.out.println("Chua co khach hang nao trong danh sach!");
        }
    }

    /**
     * Phương thức lấy danh sách tài khoản của khách hàng
     * 
     * @param customerId mã khách hàng
     * @return trả về danh sách tài khoản của khách hàng
     */
    public void showCustomerById(String customerId) {
        Customer customer = CustomerDao.list().stream().filter(c -> c.getCustomerId().equals(customerId)).findFirst()
                .orElse(null);
        if (customer != null) {
            ((DigitalCustomer) customer).displayInformation();
        }
    }

    /**
     * Phương thức tìm kiếm khách hàng theo theo số tài khoản
     * 
     * @param accountNumber số tài khoản
     * @return trả về khách hàng
     */
    public Customer getCustomerByAccountId(String accountNumber) {
        List<Customer> customers = CustomerDao.list();
        for (Customer customer : customers) {
            List<Account> accounts = getAccountsByCustomerId(customer.getCustomerId());
            for (Account account : accounts) {
                if (account.getAccountNumber().equals(accountNumber)) {
                    return customer;
                }
            }
        }
        return null;
    }

    /**
     * Phương thức thêm khách hàng vào danh sách khách hàng
     * 
     * @param fileName tên file
     */
    public void addCustomers(String fileName) {
        // Lấy danh sách khách hàng từ file text
        List<List<String>> customersFileText = TextFileService.readFile(fileName);
        // Tạo danh sách khách hàng
        List<Customer> customers = new ArrayList<>();
        // Thêm khách hàng vào danh sách khách hàng
        customersFileText.stream().forEach(customer -> customers.add(new DigitalCustomer(customer)));
        // Lấy danh sách khách hàng từ file dat
        List<Customer> customersDat = CustomerDao.list();
        // Tạo danh sách khách hàng mới
        List<Customer> customersNew = new ArrayList<>();
        // Kiểm tra xem khách hàng nào chưa tồn tại trong danh sách khách hàng thì thêm
        // vào danh sách khách hàng
        customers.stream().forEach(customer -> {
            // Biến kiểm tra khách hàng đã tồn tại hay chưa
            Boolean isExisted = false;

            // Kiểm tra khách hàng đã tồn tại hay chưa
            for (Customer customerDat : customersDat) {
                if (customerDat.getCustomerId().equals(customer.getCustomerId())) {
                    isExisted = true;
                    break;
                }
            }

            // Nếu khách hàng chưa tồn tại thì thêm vào danh sách khách hàng mới
            if (isExisted) {
                System.out.println(
                        "Khach hang " + customer.getCustomerId() + " da ton tai, them khach hang khong thanh cong");
            } else {
                customersNew.add(customer);
                System.out.println("Da them khach hang " + customer.getCustomerId() + " vao danh sach khach hang");
            }
        });
        // Thêm danh sách khách hàng vào file dat
        if (customersNew.size() > 0) {
            CustomerDao.save(customersNew);
        }
    }

    /**
     * Phương thức kiểm tra xem khách hàng có tồn tại hay không
     * 
     * @param customerId mã khách hàng
     * @return trả về true nếu khách hàng tồn tại, trả về false nếu khách hàng không
     *         tồn tại
     */
    @Override
    public boolean isCustomerExisted(String customerId) {
        List<Customer> customers = CustomerDao.list();
        for (Customer customer : customers) {
            if (customer.getCustomerId().equals(customerId)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Phương thức kiểm tra xem khách hàng có tồn tại hay không
     * 
     * @param customerId mã khách hàng
     * @return trả về true nếu khách hàng tồn tại, trả về false nếu khách hàng không
     *         tồn tại
     */
    public boolean isCustomerExisted(List<Customer> customers, Customer newCustomer) {
        for (Customer customer : customers) {
            if (customer.getCustomerId().equals(newCustomer.getCustomerId())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Phương thức kiểm tra xem tài khoản khách hàng có tồn tại hay không
     * 
     * @param accountId mã tài khoản khách hàng
     * @return trả về khách hàng nếu tài khoản khách hàng tồn tại, trả về null nếu
     *         tài khoản khách hàng không tồn tại
     */
    @Override
    public boolean isAccountExisted(String accountId) {
        List<Account> accounts = AccountDao.list();
        for (Account account : accounts) {
            if (account.getAccountNumber().equals(accountId)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Phương thức kiểm tra xem tài khoản khách hàng có tồn tại hay không
     * 
     * @param accountId mã tài khoản khách hàng
     * @return trả về khách hàng nếu tài khoản khách hàng tồn tại, trả về null nếu
     *         tài khoản khách hàng không tồn tại
     */
    public boolean isAccountExisted(List<Account> accountsList, Account newAccount) {
        for (Account account : accountsList) {
            if (account.getAccountNumber().equals(newAccount.getAccountNumber())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Phương thức kiểm tra xem tài khoản khách hàng có tồn tại hay không
     * 
     * @param accountId mã tài khoản khách hàng
     * @return trả về khách hàng nếu tài khoản khách hàng tồn tại, trả về null nếu
     *         tài khoản khách hàng không tồn tại
     */
    public boolean isAccountExistedInCustomer(String customerId, String accountId) {

        List<Account> accounts = getAccountsByCustomerId(customerId);
        for (Account account : accounts) {
            if (account.getAccountNumber().equals(accountId)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Phương thức thêm tài khoản tiết kiệm vào file dat
     * 
     * @param account tài khoản tiết kiệm
     */
    public void addSavingAccount(Scanner scanner, String customerId) {
        Customer customer = getCustomerById(customerId);
        AccountDao.add((((DigitalCustomer) customer).addSavingAccount(scanner)));
        System.out.println("Them tai khoan thanh cong");
    }

    /**
     * Phương thức lấy danh sách tài khoản khách hàng theo mã khách hàng
     * 
     * @param customerId mã khách hàng
     * @return trả về danh sách tài khoản khách hàng
     */
    public List<Account> getAccountsByCustomerId(String customerId) {

        List<Account> accounts = AccountDao.list().stream()
                .filter(account -> account.getCustomerId().equals(customerId))
                .collect(Collectors.toList());

        return accounts;
    }

    /**
     * Phương thức tìm tài khoản khách hàng theo mã tài khoản khách hàng
     * 
     * @param accountId mã tài khoản khách hàng
     * @return trả về tài khoản khách hàng nếu tìm thấy, trả về null nếu không tìm
     *         thấy
     */
    public Account getAccountByAccountNumber(String customerId, String accountId) {
        List<Account> accounts = AccountDao.list();
        Customer customer = getCustomerById(customerId);
        return ((DigitalCustomer) customer).getAccountByAccountNumber(accounts, accountId);
    }

    /**
     * Phương thức rút tiền khỏi tài khoản tiết kiệm
     * 
     * @param scanner đối tượng Scanner
     */
    public void withdraw(Scanner scanner, String customerId) {
        try {
            Customer customer = getCustomerById(customerId);
            customer.displayInformation();
            ((DigitalCustomer) customer).withdraw(scanner);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Phương thức rút tiền khỏi tài khoản tiết kiệm
     * 
     * @param scanner đối tượng Scanner
     */
    public void tranfers(Scanner scanner, String customerId) {
        Customer customer = getCustomerById(customerId);
        ((DigitalCustomer) customer).tranfers(scanner);
    }

}
