package com.tama.javafxtest.controller;

import com.tama.javafxtest.model.DatabaseConfig;
import com.tama.javafxtest.util.DatabaseConnection;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class DatabaseConfigController implements Initializable {
    @FXML private TextField hostField;
    @FXML private TextField portField;
    @FXML private TextField databaseField;
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Button testButton;
    @FXML private Button saveButton;
    @FXML private Button cancelButton;
    @FXML private Label errorMessageLabel;
    
    private boolean connectionSuccess = false;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize with current config
        DatabaseConfig currentConfig = DatabaseConnection.getConfig();
        hostField.setText(currentConfig.getHost());
        portField.setText(String.valueOf(currentConfig.getPort()));
        databaseField.setText(currentConfig.getDatabase());
        usernameField.setText(currentConfig.getUsername());
        passwordField.setText(currentConfig.getPassword());
        
        // Add validation for port (numbers only)
        portField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                portField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        
        // Hide error message initially
        errorMessageLabel.setVisible(false);
    }
    
    @FXML
    private void handleTest() {
        if (validateInput()) {
            DatabaseConfig testConfig = createConfigFromInput();
            boolean testSuccess = DatabaseConnection.testConnection(testConfig);
            
            if (testSuccess) {
                showAlert(Alert.AlertType.INFORMATION, "Connection Test", "Connection successful!");
            } else {
                showAlert(Alert.AlertType.ERROR, "Connection Test", "Failed to connect to the database. Please check your settings.");
            }
        }
    }
    
    @FXML
    private void handleSave() {
        if (validateInput()) {
            DatabaseConfig newConfig = createConfigFromInput();
            boolean testSuccess = DatabaseConnection.testConnection(newConfig);
            
            if (testSuccess) {
                DatabaseConnection.setConfig(newConfig);
                connectionSuccess = true;
                closeWindow();
            } else {
                showAlert(Alert.AlertType.ERROR, "Connection Error", "Failed to connect to the database. Please check your settings.");
            }
        }
    }
    
    @FXML
    private void handleCancel() {
        closeWindow();
    }
    
    private boolean validateInput() {
        StringBuilder errors = new StringBuilder();
        
        if (hostField.getText().trim().isEmpty()) {
            errors.append("Host/Server is required.\n");
        }
        
        if (portField.getText().trim().isEmpty()) {
            errors.append("Port is required.\n");
        } else {
            try {
                int port = Integer.parseInt(portField.getText().trim());
                if (port <= 0 || port > 65535) {
                    errors.append("Port must be between 1 and 65535.\n");
                }
            } catch (NumberFormatException e) {
                errors.append("Port must be a valid number.\n");
            }
        }
        
        if (databaseField.getText().trim().isEmpty()) {
            errors.append("Database name is required.\n");
        }
        
        if (errors.length() > 0) {
            errorMessageLabel.setText(errors.toString());
            errorMessageLabel.setVisible(true);
            return false;
        }
        
        errorMessageLabel.setVisible(false);
        return true;
    }
    
    private DatabaseConfig createConfigFromInput() {
        String host = hostField.getText().trim();
        int port = Integer.parseInt(portField.getText().trim());
        String database = databaseField.getText().trim();
        String username = usernameField.getText().trim();
        String password = passwordField.getText();
        
        return new DatabaseConfig(host, port, database, username, password);
    }
    
    private void closeWindow() {
        Stage stage = (Stage) saveButton.getScene().getWindow();
        stage.close();
    }
    
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    public boolean isConnectionSuccessful() {
        return connectionSuccess;
    }
}