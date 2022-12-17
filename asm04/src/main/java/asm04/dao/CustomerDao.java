package asm04.dao;

import java.util.List;

import asm02.models.Customer;
import asm04.service.BinaryFileService;

public class CustomerDao {
    private final static String FILE_NAME = "store/customers.dat";

    public static void save(List<Customer> customers) {
        BinaryFileService.writeFile(FILE_NAME, customers);
    }

    public static List<Customer> list() {
        return BinaryFileService.readFile(FILE_NAME);
    }
}
