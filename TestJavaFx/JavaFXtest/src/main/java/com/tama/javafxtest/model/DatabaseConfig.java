package com.tama.javafxtest.model;

public class DatabaseConfig {
    private String host;
    private int port;
    private String database;
    private String username;
    private String password;
    
    public DatabaseConfig() {
        // Default values
        this.host = "localhost";
        this.port = 3306;
        this.database = "testing";
        this.username = "";
        this.password = "";
    }
    
    public DatabaseConfig(String host, int port, String database, String username, String password) {
        this.host = host;
        this.port = port;
        this.database = database;
        this.username = username;
        this.password = password;
    }
    
    public String getHost() {
        return host;
    }
    
    public void setHost(String host) {
        this.host = host;
    }
    
    public int getPort() {
        return port;
    }
    
    public void setPort(int port) {
        this.port = port;
    }
    
    public String getDatabase() {
        return database;
    }
    
    public void setDatabase(String database) {
        this.database = database;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getConnectionUrl() {
        return String.format("jdbc:mysql://%s:%d/%s", host, port, database);
    }
}