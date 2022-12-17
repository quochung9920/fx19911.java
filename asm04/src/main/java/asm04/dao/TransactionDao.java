package asm04.dao;

import java.util.List;

import asm04.model.Transaction;
import asm04.service.BinaryFileService;

public class TransactionDao {
    private final static String FILE_NAME = "store/transactions.dat";

    public static void save(List<Transaction> customers) {
        BinaryFileService.writeFile(FILE_NAME, customers);
    }



    public static List<Transaction> list() {
        return BinaryFileService.readFile(FILE_NAME);
    }
}
