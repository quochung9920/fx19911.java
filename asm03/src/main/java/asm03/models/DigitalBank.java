package asm03.models;

import asm02.models.Account;
import asm02.models.Bank;
import asm02.models.Customer;

/** 
 * Class DigitalBank
 * Kế thừa từ class Bank
 */
public class DigitalBank extends Bank{


    /** Phương thức khởi tạo */
    public DigitalBank() {
        super();
    }

    /** Tìm kiếm khách hàng theo mã khách hàng
     * @param customerId mã khách hàng
     * @return trả về khách hàng nếu tìm thấy, trả về null nếu không tìm thấy
     */
    public Customer getCustomerById(String customerId) {
        for (Customer customer : super.getCustomers()) {
            if (customer.getCustomerId().equals(customerId)) {
                return customer;
            }
        }
        return null;
    }

    /** Phương thức thêm khách hàng 
     * @param customerId mã khách hàng
     * @param name tên khách hàng
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


    /** Phương thức kiểm tra tài khoản có tồn tại hay không
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
    

    /** Phương thức dùng để cho phép khách hàng rút tiền
     * @param customerId mã khách hàng
     * @param accountNumber số tài khoản
     * @param amount số tiền cần rút
     * 
     * @return trả về true nếu rút tiền thành công, trả về false nếu rút tiền thất bại
     */
    public void withdraw(String customerId, String accountNumber, double amount) {
        // Kiểm tra xem khách hàng có tồn tại không
        Customer customer = getCustomerById(customerId);
        if (customer == null) {
            throw new IllegalArgumentException("Khach hang khong ton tai");
        } else {
            ((DigitalCustomer)customer).withdraw(accountNumber, amount);
        }
    }

    /** Phương thức in ra lịch sử giao dịch của khách hàng
     * @param customerId mã khách hàng
     */
    public void displayTransactionHistory(String customerId) {
        // Kiểm tra xem khách hàng có tồn tại không
        Customer customer = getCustomerById(customerId);
        if (customer == null) {
            throw new IllegalArgumentException("Khach hang khong ton tai");
        } else {
            ((DigitalCustomer)customer).displayTransactionHistory();
        }

    }
}
