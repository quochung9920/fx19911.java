package asm04.dao;

import java.util.ArrayList;
import java.util.List;

import asm02.models.Account;
import asm04.service.BinaryFileService;

/**
 * Lớp này chứa các phương thức để thao tác với file accounts.dat
 * Lưu ý: Các phương thức trong lớp này đều là static
 * Nên không cần khởi tạo đối tượng để sử dụng
 */
public class AccountDao {
    // Đường dẫn tới file accounts.dat
    private final static String FILE_NAME = "store/accounts.dat";

    /** Phương thức này dùng để lưu danh sách tài khoản vào file */
    public static void save(List<Account> customers) {
        BinaryFileService.writeFile(FILE_NAME, customers);
    }

    /** Phương thức này dùng để đọc danh sách tài khoản từ file */
    public static List<Account> list() {
        return BinaryFileService.readFile(FILE_NAME);
    }

    /** Phương thức này dùng để thêm 1 tài khoản vào file */
    public static void add(Account account) {
        // Thêm 1 tài khoản vào file
        List<Account> accounts = list();
        accounts.add(account);
        save(accounts);
    }

    /** Phương thức này dùng để cập nhật 1 tài khoản trong file */
    public static void update(Account account) {
        List<Account> accounts = list();
        boolean hasExist = accounts.stream().anyMatch(acc -> acc.getAccountNumber().equals(account.getAccountNumber()));
        List<Account> updateAccounts;
        if(!hasExist){
            updateAccounts = new ArrayList<>();
            updateAccounts.add(account);
        } else {
            updateAccounts = new ArrayList<>();

            for (Account acc : accounts) {
                if (acc.getAccountNumber().equals(account.getAccountNumber())) {
                    updateAccounts.add(account);
                } else {
                    updateAccounts.add(acc);
                }
            }
        }

        save(updateAccounts);
    }
}
