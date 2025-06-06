package com.tama.javafxtest.util;

import com.tama.javafxtest.model.Customer;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class CSVUtil {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
    public static class ImportResult {
        private final int successCount;
        private final int failureCount;
        private final List<String> errors;
        
        public ImportResult(int successCount, int failureCount, List<String> errors) {
            this.successCount = successCount;
            this.failureCount = failureCount;
            this.errors = errors;
        }
        
        public int getSuccessCount() { return successCount; }
        public int getFailureCount() { return failureCount; }
        public List<String> getErrors() { return errors; }
    }
    
    public static List<Customer> importFromCSV(File file) throws IOException {
        List<Customer> customers = new ArrayList<>();
        
        try (FileReader reader = new FileReader(file);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withDelimiter(';').withFirstRecordAsHeader())) {
            
            for (CSVRecord csvRecord : csvParser) {
                try {
                    String nik = csvRecord.get("nik");
                    String name = csvRecord.get("name");
                    String bornStr = csvRecord.get("born");
                    String activeStr = csvRecord.get("active");
                    String salaryStr = csvRecord.get("salary");
                    
                    LocalDate born = null;
                    if (bornStr != null && !bornStr.trim().isEmpty()) {
                        born = LocalDate.parse(bornStr, DATE_FORMATTER);
                    }
                    
                    boolean active = "1".equals(activeStr) || "true".equalsIgnoreCase(activeStr);
                    int salary = Integer.parseInt(salaryStr);
                    
                    Customer customer = new Customer(0, nik, name, born, active, salary);
                    customers.add(customer);
                } catch (Exception e) {
                    System.err.println("Error parsing record: " + csvRecord + ", Error: " + e.getMessage());
                }
            }
        }
        
        return customers;
    }
    
    public static void exportToCSV(List<Customer> customers, File file) throws IOException {
        try (FileWriter writer = new FileWriter(file);
             CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withDelimiter(';'))) {
            
            // Write header
            csvPrinter.printRecord("idx", "nik", "name", "born", "active", "salary");
            
            // Write data
            for (Customer customer : customers) {
                csvPrinter.printRecord(
                    customer.getIdx(),
                    customer.getNik(),
                    customer.getName(),
                    customer.getBorn() != null ? customer.getBorn().format(DATE_FORMATTER) : "",
                    customer.isActive() ? "1" : "0",
                    customer.getSalary()
                );
            }
        }
    }
}