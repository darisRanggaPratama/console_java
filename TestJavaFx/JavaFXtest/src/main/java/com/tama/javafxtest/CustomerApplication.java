package com.tama.javafxtest;

import com.tama.javafxtest.controller.DatabaseConfigController;
import com.tama.javafxtest.util.DatabaseConnection;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class CustomerApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // Try to establish database connection first
        boolean connected = false;
        
        try {
            DatabaseConnection.getInstance();
            connected = true;
        } catch (SQLException e) {
            // Show error message
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Database Connection Error");
            alert.setHeaderText("Failed to connect to the database");
            alert.setContentText("Would you like to configure the database connection?");
            alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
            
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.YES) {
                connected = showDatabaseConfigDialog();
            }
        }
        
        if (!connected) {
            // If still not connected, show final error and exit
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Database Connection Error");
            alert.setHeaderText("Cannot continue without database connection");
            alert.setContentText("The application will now close.");
            alert.showAndWait();
            Platform.exit();
            return;
        }
        
        // Continue with normal application startup
        FXMLLoader fxmlLoader = new FXMLLoader(CustomerApplication.class.getResource("/com/tama/javafxtest/view/customer-main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
        // Add this line to load the CSS file
        scene.getStylesheets().add(getClass().getResource("/com/tama/javafxtest/css/application.css").toExternalForm());
        stage.setTitle("Customer Management System");
        stage.setScene(scene);
        stage.setMinWidth(1000);
        stage.setMinHeight(600);
        stage.show();
    }
    
    private boolean showDatabaseConfigDialog() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/tama/javafxtest/view/database-config.fxml"));
            Scene scene = new Scene(loader.load());
            scene.getStylesheets().add(getClass().getResource("/com/tama/javafxtest/css/application.css").toExternalForm());
            
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Database Configuration");
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.setScene(scene);
            
            DatabaseConfigController controller = loader.getController();
            
            dialogStage.showAndWait();
            
            return controller.isConnectionSuccessful();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        launch();
    }
}