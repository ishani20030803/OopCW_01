package org.example;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TicketingUI extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Load saved configuration or use default values if not available
        Configuration config = loadConfigurationFromFile();

        // UI Components
        Label titleLabel = new Label("Ticketing System Configuration");
        TextField totalTicketsField = new TextField(String.valueOf(config.getTotalTickets()));
        TextField poolCapacityField = new TextField(String.valueOf(config.getPoolCapacity()));
        TextField vendorIntervalField = new TextField(String.valueOf(config.getVendorInterval()));
        TextField customerIntervalField = new TextField(String.valueOf(config.getCustomerInterval()));
        Button startButton = new Button("Start System");

        // Event Handler for Start Button
        startButton.setOnAction(event -> {
            try {
                // Parse Inputs and Configure System
                config.setTotalTickets(Integer.parseInt(totalTicketsField.getText()));
                config.setPoolCapacity(Integer.parseInt(poolCapacityField.getText()));
                config.setVendorInterval(Integer.parseInt(vendorIntervalField.getText()));
                config.setCustomerInterval(Integer.parseInt(customerIntervalField.getText()));

                // Save the configuration to a file (JSON)
                saveConfigurationToFile(config);

                // Launch the backend system in a new thread
                new Thread(() -> TicketingSystem.runSystem(config)).start();

                // Show success message
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "System started successfully!");
                alert.showAndWait();
            } catch (NumberFormatException e) {
                // Handle invalid input
                Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid input. Please enter numbers only.");
                alert.showAndWait();
            }
        });

        // Layout
        VBox root = new VBox(10,
                titleLabel,
                new Label("Total Tickets:"), totalTicketsField,
                new Label("Pool Capacity:"), poolCapacityField,
                new Label("Vendor Interval (ms):"), vendorIntervalField,
                new Label("Customer Interval (ms):"), customerIntervalField,
                startButton);
        root.setPadding(new Insets(10));

        // Scene and Stage
        primaryStage.setScene(new Scene(root, 300, 300));
        primaryStage.setTitle("Ticketing System");
        primaryStage.show();
    }

    // Load the configuration from a saved JSON file if it exists
    private Configuration loadConfigurationFromFile() {
        Configuration config = Configuration.loadConfigurationFromJSON("config.json");
        if (config == null) {
            // If no saved configuration exists, use default values
            config = new Configuration();
        }
        return config;
    }

    // Save the configuration to a file (JSON)
    private void saveConfigurationToFile(Configuration config) {
        config.saveConfigurationToJSON("config.json");
    }

    public static void main(String[] args) {
        launch(args);
    }
}



