package models;

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



    /** Phương thức isCustomerExisted dùng để kiểm tra xem khách hàng này đã tồn tại trong ngân hàng hay chưa */
    public boolean isCustomerExisted(String customerId) {
        for (Customer customer : customers) {
            if (customer.getCustomerId().equals(customerId)) {
                return true;
            }
        }
        return false;
    }


}
