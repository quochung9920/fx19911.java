package asm04.dao;

import java.util.List;

import asm02.models.Customer;
import asm04.service.BinaryFileService;

/**
 * Lớp này chứa các phương thức để thao tác với file customers.dat
 * Lưu ý: Các phương thức trong lớp này đều là static
 * Nên không cần khởi tạo đối tượng để sử dụng
 */
public class CustomerDao {
    // Đường dẫn tới file customers.dat
    private final static String FILE_NAME = "store/customers.dat";

    /** Phương thức này dùng để lưu danh sách khách hàng vào file */
    public static void save(List<Customer> customers) {
        BinaryFileService.writeFile(FILE_NAME, customers);
    }

    /** Phương thức này dùng để đọc danh sách khách hàng từ file */
    public static List<Customer> list() {
        return BinaryFileService.readFile(FILE_NAME);
    }
}
