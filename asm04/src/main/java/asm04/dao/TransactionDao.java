package asm04.dao;

import java.util.List;

import asm04.model.Transaction;
import asm04.service.BinaryFileService;

/**
 * Lớp này chứa các phương thức để thao tác với file transactions.dat
 * Lưu ý: Các phương thức trong lớp này đều là static
 * Nên không cần khởi tạo đối tượng để sử dụng
 */
public class TransactionDao {
    // Đường dẫn tới file transactions.dat
    private final static String FILE_NAME = "store/transactions.dat";

    /** Phương thức này dùng để lưu danh sách giao dịch vào file */
    public static void save(List<Transaction> customers) {
        BinaryFileService.writeFile(FILE_NAME, customers);
        System.out.println("Ghi file thành công!");
    }

    /** Phương thức này dùng để đọc danh sách giao dịch từ file */
    public static List<Transaction> list() {
        return BinaryFileService.readFile(FILE_NAME);
    }

    /** Phương thức này dùng để lấy danh sách giao dịch theo mã khách hàng */
    public static List<Transaction> getTransactionsByAccountNumber(String accountNumber) {
        List<Transaction> transactions = list();
        if (transactions == null) {
            return null;
        }
        for (int i = 0; i < transactions.size(); i++) {
            if (!transactions.get(i).getAccountNumber().equals(accountNumber)) {
                transactions.remove(i);
                i--;
            }
        }
        return transactions;
    }


}
