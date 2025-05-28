package com.tama.javafxtest.dao;

import com.tama.javafxtest.model.Customer;
import com.tama.javafxtest.util.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {
    private Connection connection;
    
    public CustomerDAO() {
        try {
            this.connection = DatabaseConnection.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            this.connection = null;
        }
    }
    
    // Metode untuk memeriksa apakah koneksi tersedia
    private boolean isConnectionAvailable() {
        return connection != null;
    }
    
    // Metode untuk mencoba menghubungkan kembali jika koneksi null
    private boolean reconnect() {
        try {
            this.connection = DatabaseConnection.getInstance().getConnection();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Modifikasi metode yang ada untuk memeriksa koneksi
    public ObservableList<Customer> getAllCustomers(int limit, int offset) {
        ObservableList<Customer> customers = FXCollections.observableArrayList();
        
        if (!isConnectionAvailable() && !reconnect()) {
            return customers; // Return empty list if no connection
        }
        
        String query = "SELECT * FROM customer LIMIT ? OFFSET ?";
        
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, limit);
            pstmt.setInt(2, offset);
            
            // Sisanya tetap sama seperti implementasi asli
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Customer customer = new Customer(
                    rs.getInt("idx"),
                    rs.getString("nik"),
                    rs.getString("name"),
                    rs.getDate("born") != null ? rs.getDate("born").toLocalDate() : null,
                    rs.getBoolean("active"),
                    rs.getInt("salary")
                );
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return customers;
    }
    
    public ObservableList<Customer> searchCustomers(String searchTerm, int limit, int offset) {
        ObservableList<Customer> customers = FXCollections.observableArrayList();
        String query = "SELECT * FROM customer WHERE nik LIKE ? OR name LIKE ? OR born LIKE ? OR active LIKE ? OR salary LIKE ? LIMIT ? OFFSET ?";
        
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            String searchPattern = "%" + searchTerm + "%";
            pstmt.setString(1, searchPattern);
            pstmt.setString(2, searchPattern);
            pstmt.setString(3, searchPattern);
            pstmt.setString(4, searchPattern);
            pstmt.setString(5, searchPattern);
            pstmt.setInt(6, limit);
            pstmt.setInt(7, offset);
            
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Customer customer = new Customer(
                    rs.getInt("idx"),
                    rs.getString("nik"),
                    rs.getString("name"),
                    rs.getDate("born") != null ? rs.getDate("born").toLocalDate() : null,
                    rs.getBoolean("active"),
                    rs.getInt("salary")
                );
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return customers;
    }
    
    public int getTotalCount() {
        String query = "SELECT COUNT(*) FROM customer";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public int getSearchCount(String searchTerm) {
        String query = "SELECT COUNT(*) FROM customer WHERE nik LIKE ? OR name LIKE ? OR born LIKE ? OR active LIKE ? OR salary LIKE ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            String searchPattern = "%" + searchTerm + "%";
            pstmt.setString(1, searchPattern);
            pstmt.setString(2, searchPattern);
            pstmt.setString(3, searchPattern);
            pstmt.setString(4, searchPattern);
            pstmt.setString(5, searchPattern);
            
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public boolean insertCustomer(Customer customer) {
        String query = "INSERT INTO customer (nik, name, born, active, salary) VALUES (?, ?, ?, ?, ?)";
        
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, customer.getNik());
            pstmt.setString(2, customer.getName());
            pstmt.setDate(3, customer.getBorn() != null ? Date.valueOf(customer.getBorn()) : null);
            pstmt.setBoolean(4, customer.isActive());
            pstmt.setInt(5, customer.getSalary());
            
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean updateCustomer(Customer customer) {
        String query = "UPDATE customer SET nik = ?, name = ?, born = ?, active = ?, salary = ? WHERE idx = ?";
        
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, customer.getNik());
            pstmt.setString(2, customer.getName());
            pstmt.setDate(3, customer.getBorn() != null ? Date.valueOf(customer.getBorn()) : null);
            pstmt.setBoolean(4, customer.isActive());
            pstmt.setInt(5, customer.getSalary());
            pstmt.setInt(6, customer.getIdx());
            
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean deleteCustomer(int idx) {
        String query = "DELETE FROM customer WHERE idx = ?";
        
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, idx);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public List<Customer> getAllCustomersForExport() {
        List<Customer> customers = new ArrayList<>();
        String query = "SELECT * FROM customer";
        
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Customer customer = new Customer(
                    rs.getInt("idx"),
                    rs.getString("nik"),
                    rs.getString("name"),
                    rs.getDate("born") != null ? rs.getDate("born").toLocalDate() : null,
                    rs.getBoolean("active"),
                    rs.getInt("salary")
                );
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return customers;
    }
}