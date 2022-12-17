package asm02.models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/** class Bank có Access Modifier là public */
public class Bank {
    // Khai báo các thuộc tính của class Bank
    private final String id;
    private final List<Customer> customers;

    /** Phương thức khởi tạo */
    public Bank() {
        this.customers = new ArrayList<>();
        this.id = String.valueOf(UUID.randomUUID());
    }

    /** Setter cho thuộc tính Id */
    public List<Customer> getCustomers() {
        return customers;
    }
    public String getId() {
        return id;
    }

    /** Phương thức addCustomer dùng để thêm khách hàng mới cho ngân hàng. Khách hàng được thêm khi và chỉ khi số CCCD của khách hàng chưa từng tồn tại trước đó. */
    public void addCustomer(Customer newCustomer) {
        if(!isCustomerExisted(newCustomer.getCustomerId())) {
            customers.add(newCustomer);
        } else {
            throw new RuntimeException("Khach hang da ton tai!");
        }
    }

    /** Phương thức addAccount dùng để thêm tài khoản mới cho khách hàng */
    public void addAccount(String customerId, Account newAccount) {
        Customer customer = searchCustomerByCCCD(customerId);
        if(customer != null) {
            customer.addAccount(newAccount);
        }
    }

    /** Phương thức searchCustomerByCCCD dùng để tìm kiếm khách hàng theo số CCCD */
    public Customer searchCustomerByCCCD(String cccd) {
        for (Customer customer : customers) {
            if (customer.getCustomerId().equals(cccd)) {
                return customer;
            }
        }
        return null;
    }

    /** Phương thức findCustomerByName dùng để tìm kiếm khách hàng theo tên */
    public List<Customer> findCustomerByName(String name) {
        List<Customer> result = new ArrayList<>();

        // So sánh tên khách hàng với chuỗi con name, không phân biệt chữ hoa chữ thường
        for (Customer customer : customers) {
            if (customer.getName().toLowerCase().contains(name.toLowerCase())) {
                result.add(customer);
            }
        }

        return result;
    }

    /** Phương thức isCustomerExisted dùng để kiểm tra xem khách hàng này đã tồn tại trong ngân hàng hay chưa */
    public boolean isCustomerExisted(String customerId) {
        for (Customer customer : customers) {
            if (customer.getCustomerId().equals(customerId)) {
                return true;
            }
        }
        return false;
    }

    /**  Phương thức isAccountExisted dùng để kiểm tra xem khách hàng này đã tồn tại trong ngân hàng hay chưa */
    public boolean isAccountExisted(String accountId) {
        for (Customer customer : customers) {
            if(customer.isAccountExisted(accountId)){
                throw new RuntimeException("Tai khoan da ton tai!");
            }
        }
        return false;
    }
}
