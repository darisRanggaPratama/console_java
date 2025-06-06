package com.tama.javafxtest.util;

import com.tama.javafxtest.model.DatabaseConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static DatabaseConfig config = new DatabaseConfig("localhost", 3306, "testing", "rangga", "rangga");
    
    private static DatabaseConnection instance;
    private Connection connection;
    private boolean isConnected = false;
    
    private DatabaseConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(
                config.getConnectionUrl(), 
                config.getUsername(), 
                config.getPassword());
            isConnected = true;
        } catch (ClassNotFoundException ex) {
            System.err.println("Database Connection Creation Failed : " + ex.getMessage());
            throw new SQLException("Database driver not found", ex);
        }
    }
    
    public Connection getConnection() {
        return connection;
    }
    
    public boolean isConnected() {
        return isConnected;
    }
    
    public static void setConfig(DatabaseConfig newConfig) {
        config = newConfig;
        // Reset instance to force reconnection with new config
        instance = null;
    }
    
    public static DatabaseConfig getConfig() {
        return config;
    }
    
    public static DatabaseConnection getInstance() throws SQLException {
        if (instance == null) {
            instance = new DatabaseConnection();
        } else if (instance.getConnection().isClosed()) {
            instance = new DatabaseConnection();
        }
        
        return instance;
    }
    
    public static boolean testConnection(DatabaseConfig testConfig) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection testConnection = DriverManager.getConnection(
                testConfig.getConnectionUrl(),
                testConfig.getUsername(),
                testConfig.getPassword());
            testConnection.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}