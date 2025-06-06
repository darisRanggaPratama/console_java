package com.tama.javafxtest.controller;

import com.tama.javafxtest.dao.CustomerDAO;
import com.tama.javafxtest.model.Customer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

public class CustomerFormController implements Initializable {
    @FXML private TextField nikField;
    @FXML private TextField nameField;
    @FXML private DatePicker bornDatePicker;
    @FXML private CheckBox activeCheckBox;
    @FXML private TextField salaryField;
    @FXML private Button saveButton;
    @FXML private Button cancelButton;
    @FXML private Button deleteButton;

    private CustomerDAO customerDAO;
    private Customer currentCustomer;
    private CustomerMainController mainController;
    private boolean isEditMode = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        customerDAO = new CustomerDAO();
        
        // Add input validation
        salaryField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                salaryField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        
        nikField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > 6) {
                nikField.setText(oldValue);
            }
        });
    }

    public void setCustomer(Customer customer) {
        this.currentCustomer = customer;
        this.isEditMode = customer != null;
        
        if (isEditMode) {
            populateFields();
            deleteButton.setVisible(true);
        } else {
            clearFields();
            deleteButton.setVisible(false);
        }
    }

    public void setMainController(CustomerMainController mainController) {
        this.mainController = mainController;
    }

    private void populateFields() {
        if (currentCustomer != null) {
            nikField.setText(currentCustomer.getNik());
            nameField.setText(currentCustomer.getName());
            bornDatePicker.setValue(currentCustomer.getBorn());
            activeCheckBox.setSelected(currentCustomer.isActive());
            salaryField.setText(String.valueOf(currentCustomer.getSalary()));
        }
    }

    private void clearFields() {
        nikField.clear();
        nameField.clear();
        bornDatePicker.setValue(null);
        activeCheckBox.setSelected(false);
        salaryField.clear();
    }

    @FXML
    private void handleSave() {
        if (validateInput()) {
            Customer customer = createCustomerFromInput();
            
            boolean success;
            if (isEditMode) {
                customer.setIdx(currentCustomer.getIdx());
                success = customerDAO.updateCustomer(customer);
            } else {
                success = customerDAO.insertCustomer(customer);
            }
            
            if (success) {
                showAlert(Alert.AlertType.INFORMATION, "Success", 
                    isEditMode ? "Customer updated successfully!" : "Customer added successfully!");
                mainController.refreshTable();
                closeWindow();
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", 
                    "Failed to " + (isEditMode ? "update" : "add") + " customer.");
            }
        }
    }

    @FXML
    private void handleCancel() {
        closeWindow();
    }

    @FXML
    private void handleDelete() {
        if (currentCustomer != null) {
            Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmAlert.setTitle("Confirm Delete");
            confirmAlert.setHeaderText(null);
            confirmAlert.setContentText("Are you sure you want to delete this customer?");
            
            Optional<ButtonType> result = confirmAlert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                boolean success = customerDAO.deleteCustomer(currentCustomer.getIdx());
                
                if (success) {
                    showAlert(Alert.AlertType.INFORMATION, "Success", "Customer deleted successfully!");
                    mainController.refreshTable();
                    closeWindow();
                } else {
                    showAlert(Alert.AlertType.ERROR, "Error", "Failed to delete customer.");
                }
            }
        }
    }

    private boolean validateInput() {
        StringBuilder errors = new StringBuilder();
        
        if (nikField.getText().trim().isEmpty()) {
            errors.append("NIK is required.\n");
        }
        
        if (nameField.getText().trim().isEmpty()) {
            errors.append("Name is required.\n");
        }
        
        if (salaryField.getText().trim().isEmpty()) {
            errors.append("Salary is required.\n");
        } else {
            try {
                Integer.parseInt(salaryField.getText().trim());
            } catch (NumberFormatException e) {
                errors.append("Salary must be a valid number.\n");
            }
        }
        
        if (errors.length() > 0) {
            showAlert(Alert.AlertType.ERROR, "Validation Error", errors.toString());
            return false;
        }
        
        return true;
    }

    private Customer createCustomerFromInput() {
        String nik = nikField.getText().trim();
        String name = nameField.getText().trim();
        LocalDate born = bornDatePicker.getValue();
        boolean active = activeCheckBox.isSelected();
        int salary = Integer.parseInt(salaryField.getText().trim());
        
        return new Customer(0, nik, name, born, active, salary);
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
}