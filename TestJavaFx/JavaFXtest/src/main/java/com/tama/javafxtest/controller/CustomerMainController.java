package com.tama.javafxtest.controller;

import com.tama.javafxtest.dao.CustomerDAO;
import com.tama.javafxtest.model.Customer;
import com.tama.javafxtest.util.CSVUtil;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class CustomerMainController implements Initializable {
    @FXML private TextField searchField;
    @FXML private Button searchButton;
    @FXML private Button clearSearchButton;
    @FXML private Button addButton;
    @FXML private Button uploadCsvButton;
    @FXML private Button downloadCsvButton;
    @FXML private ComboBox<Integer> rowsPerPageComboBox;
    @FXML private TableView<Customer> customerTable;
    @FXML private TableColumn<Customer, Integer> idxColumn;
    @FXML private TableColumn<Customer, String> nikColumn;
    @FXML private TableColumn<Customer, String> nameColumn;
    @FXML private TableColumn<Customer, LocalDate> bornColumn;
    @FXML private TableColumn<Customer, Boolean> activeColumn;
    @FXML private TableColumn<Customer, Integer> salaryColumn;
    @FXML private Button previousButton;
    @FXML private Button nextButton;
    @FXML private Label pageInfoLabel;
    @FXML private Label recordCountLabel;

    private CustomerDAO customerDAO;
    private ObservableList<Customer> customerList;
    private int currentPage = 1;
    private int rowsPerPage = 10;
    private int totalRecords = 0;
    private String currentSearchTerm = "";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        customerDAO = new CustomerDAO();
        customerList = FXCollections.observableArrayList();
        
        setupTableColumns();
        setupRowsPerPageComboBox();
        loadCustomers();
        
        // Setup search field listener
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.trim().isEmpty()) {
                handleClearSearch();
            }
        });
    }

    private void setupTableColumns() {
        idxColumn.setCellValueFactory(new PropertyValueFactory<>("idx"));
        nikColumn.setCellValueFactory(new PropertyValueFactory<>("nik"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        bornColumn.setCellValueFactory(new PropertyValueFactory<>("born"));
        activeColumn.setCellValueFactory(new PropertyValueFactory<>("active"));
        salaryColumn.setCellValueFactory(new PropertyValueFactory<>("salary"));
        
        // Format active column to show Yes/No
        activeColumn.setCellFactory(column -> new TableCell<Customer, Boolean>() {
            @Override
            protected void updateItem(Boolean item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item ? "Yes" : "No");
                }
            }
        });
        
        // Format salary column with currency
        salaryColumn.setCellFactory(column -> new TableCell<Customer, Integer>() {
            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(String.format("Rp %,d", item));
                }
            }
        });
        
        customerTable.setItems(customerList);
    }

    private void setupRowsPerPageComboBox() {
        rowsPerPageComboBox.setItems(FXCollections.observableArrayList(1, 5, 10, 25, 50, 100));
        rowsPerPageComboBox.setValue(rowsPerPage);
    }

    private void loadCustomers() {
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                ObservableList<Customer> customers;
                int totalCount;
                
                if (currentSearchTerm.isEmpty()) {
                    customers = customerDAO.getAllCustomers(rowsPerPage, (currentPage - 1) * rowsPerPage);
                    totalCount = customerDAO.getTotalCount();
                } else {
                    customers = customerDAO.searchCustomers(currentSearchTerm, rowsPerPage, (currentPage - 1) * rowsPerPage);
                    totalCount = customerDAO.getSearchCount(currentSearchTerm);
                }
                
                Platform.runLater(() -> {
                    customerList.clear();
                    customerList.addAll(customers);
                    totalRecords = totalCount;
                    updatePaginationControls();
                });
                
                return null;
            }
        };
        
        new Thread(task).start();
    }

    private void updatePaginationControls() {
        int totalPages = (int) Math.ceil((double) totalRecords / rowsPerPage);
        if (totalPages == 0) totalPages = 1;
        
        pageInfoLabel.setText(String.format("Page %d of %d", currentPage, totalPages));
        recordCountLabel.setText(String.format("Total: %d records", totalRecords));
        
        previousButton.setDisable(currentPage <= 1);
        nextButton.setDisable(currentPage >= totalPages);
    }

    @FXML
    private void handleSearch() {
        currentSearchTerm = searchField.getText().trim();
        currentPage = 1;
        loadCustomers();
    }

    @FXML
    private void handleClearSearch() {
        searchField.clear();
        currentSearchTerm = "";
        currentPage = 1;
        loadCustomers();
    }

    @FXML
    private void handleAdd() {
        showCustomerForm(null);
    }

    @FXML
    private void handleTableClick(MouseEvent event) {
        if (event.getClickCount() == 2) {
            Customer selectedCustomer = customerTable.getSelectionModel().getSelectedItem();
            if (selectedCustomer != null) {
                showCustomerForm(selectedCustomer);
            }
        }
    }

    @FXML
    private void handleRowsPerPageChange() {
        Integer newRowsPerPage = rowsPerPageComboBox.getValue();
        if (newRowsPerPage != null && newRowsPerPage != rowsPerPage) {
            rowsPerPage = newRowsPerPage;
            currentPage = 1;
            loadCustomers();
        }
    }

    @FXML
    private void handlePrevious() {
        if (currentPage > 1) {
            currentPage--;
            loadCustomers();
        }
    }

    @FXML
    private void handleNext() {
        int totalPages = (int) Math.ceil((double) totalRecords / rowsPerPage);
        if (currentPage < totalPages) {
            currentPage++;
            loadCustomers();
        }
    }

    @FXML
    private void handleUploadCsv() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select CSV File to Import");
        fileChooser.getExtensionFilters().add(
            new FileChooser.ExtensionFilter("CSV Files", "*.csv")
        );
        
        File file = fileChooser.showOpenDialog(uploadCsvButton.getScene().getWindow());
        if (file != null) {
            importCsvFile(file);
        }
    }

    @FXML
    private void handleDownloadCsv() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save CSV File");
        fileChooser.setInitialFileName("customers.csv");
        fileChooser.getExtensionFilters().add(
            new FileChooser.ExtensionFilter("CSV Files", "*.csv")
        );
        
        File file = fileChooser.showSaveDialog(downloadCsvButton.getScene().getWindow());
        if (file != null) {
            exportCsvFile(file);
        }
    }

    private void importCsvFile(File file) {
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try {
                    List<Customer> customers = CSVUtil.importFromCSV(file);
                    int successCount = 0;
                    int failureCount = 0;
                    
                    for (Customer customer : customers) {
                        if (customerDAO.insertCustomer(customer)) {
                            successCount++;
                        } else {
                            failureCount++;
                        }
                    }
                    
                    final int finalSuccessCount = successCount;
                    final int finalFailureCount = failureCount;
                    
                    Platform.runLater(() -> {
                        showAlert(Alert.AlertType.INFORMATION, "Import Complete", 
                            String.format("Import completed!\nSuccess: %d records\nFailed: %d records", 
                                finalSuccessCount, finalFailureCount));
                        loadCustomers();
                    });
                    
                } catch (Exception e) {
                    Platform.runLater(() -> {
                        showAlert(Alert.AlertType.ERROR, "Import Error", 
                            "Error importing CSV file: " + e.getMessage());
                    });
                }
                return null;
            }
        };
        
        new Thread(task).start();
    }

    private void exportCsvFile(File file) {
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try {
                    List<Customer> customers = customerDAO.getAllCustomersForExport();
                    CSVUtil.exportToCSV(customers, file);
                    
                    Platform.runLater(() -> {
                        showAlert(Alert.AlertType.INFORMATION, "Export Complete", 
                            String.format("Export completed!\n%d records exported to %s", 
                                customers.size(), file.getName()));
                    });
                    
                } catch (Exception e) {
                    Platform.runLater(() -> {
                        showAlert(Alert.AlertType.ERROR, "Export Error", 
                            "Error exporting CSV file: " + e.getMessage());
                    });
                }
                return null;
            }
        };
        
        new Thread(task).start();
    }

    private void showCustomerForm(Customer customer) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/tama/javafxtest/view/customer-form.fxml"));
            Scene scene = new Scene(loader.load());
            
            CustomerFormController controller = loader.getController();
            controller.setCustomer(customer);
            controller.setMainController(this);
            
            Stage stage = new Stage();
            stage.setTitle(customer == null ? "Add Customer" : "Edit Customer");
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.showAndWait();
            
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Could not open customer form: " + e.getMessage());
        }
    }

    public void refreshTable() {
        loadCustomers();
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}