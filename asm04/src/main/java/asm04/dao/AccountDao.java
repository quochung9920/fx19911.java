package asm04.dao;

import java.util.ArrayList;
import java.util.List;

import asm02.models.Account;
import asm04.service.BinaryFileService;

public class AccountDao {
    private final static String FILE_NAME = "store/accounts.dat";

    public static void save(List<Account> customers) {
        BinaryFileService.writeFile(FILE_NAME, customers);
    }

    public static List<Account> list() {
        return BinaryFileService.readFile(FILE_NAME);
    }

    public static void add(Account account) {
        // Thêm 1 tài khoản vào file
        List<Account> accounts = list();
        accounts.add(account);
        save(accounts);
    }

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
