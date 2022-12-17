package asm04.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import asm02.models.Customer;

public class TextFileService {
    private static final String COMMA_DELIMITER = ",";

    public static List<List<String>> readFile(String fileName){
        List<List<String>> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(COMMA_DELIMITER);
                records.add(Arrays.asList(values));
            }
        } catch (IOException e) {
            System.out.println("Tep khong ton tai");
        }
        return records;
    }

    public static void writeFile(String fileName, List<Customer> customers) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (Customer customer : customers) {
                bw.write(customer.getCustomerId() + COMMA_DELIMITER + customer.getName());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Tep khong ton tai");
        }
    }
}
