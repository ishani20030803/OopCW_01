package org.example;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class TicketingUI extends Application {

    private TextField maxCapacityField;
    private TextField releaseRateField;
    private TextField retrievalRateField;
    private Button ticketsAvailableButton;
    private TicketingSystem ticketingSystem;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Event Ticketing System");

        // Input Fields
        maxCapacityField = new TextField();
        maxCapacityField.setPromptText("Max Capacity");

        releaseRateField = new TextField();
        releaseRateField.setPromptText("Release Rate (ms)");

        retrievalRateField = new TextField();
        retrievalRateField.setPromptText("Retrieval Rate (ms)");

        ticketsAvailableButton = new Button("Tickets Available: 0");
        ticketsAvailableButton.setStyle("-fx-background-color: #007bff; -fx-text-fill: white;");

        // Buttons
        Button startButton = new Button("Start System");
        startButton.setStyle("-fx-background-color: #28a745; -fx-text-fill: white;");

        Button stopButton = new Button("Stop System");
        stopButton.setStyle("-fx-background-color: #dc3545; -fx-text-fill: white;");
        stopButton.setDisable(true);

        Button viewCustomersButton = new Button("View Customers");
        Button viewVendorsButton = new Button("View Vendors");

        Button loginButton = new Button("Login");
        Button signUpButton = new Button("Sign Up");

        // Handlers
        startButton.setOnAction(event -> {
            try {
                int maxCapacity = Integer.parseInt(maxCapacityField.getText());
                int releaseRateMs = Integer.parseInt(releaseRateField.getText());
                int retrievalRateMs = Integer.parseInt(retrievalRateField.getText());

                // Initialize the ticketing system
                ticketingSystem = new TicketingSystem(maxCapacity, releaseRateMs, retrievalRateMs, ticketsAvailableButton);
                ticketingSystem.start();

                startButton.setDisable(true);
                stopButton.setDisable(false);

            } catch (NumberFormatException e) {
                showError("Please enter valid numeric values for all fields.");
            }
        });

        stopButton.setOnAction(event -> {
            if (ticketingSystem != null) {
                ticketingSystem.stop();
                startButton.setDisable(false);
                stopButton.setDisable(true);
            }
        });

        ticketsAvailableButton.setOnAction(event -> {
            if (ticketingSystem != null) {
                Platform.runLater(() -> ticketsAvailableButton.setText("Tickets Available: " + ticketingSystem.getAvailableTickets()));
            }
        });

        loginButton.setOnAction(event -> showLoginDialog());
        signUpButton.setOnAction(event -> showSignUpDialog());

        // Layout Configuration
        VBox inputFieldsBox = new VBox(10, maxCapacityField, releaseRateField, retrievalRateField);
        inputFieldsBox.setAlignment(Pos.CENTER);

        VBox buttonsBox = new VBox(10, startButton, stopButton, ticketsAvailableButton, viewCustomersButton, viewVendorsButton, loginButton, signUpButton);
        buttonsBox.setAlignment(Pos.CENTER);

        VBox mainLayout = new VBox(20, inputFieldsBox, buttonsBox);
        mainLayout.setAlignment(Pos.CENTER);
        mainLayout.setPadding(new Insets(20));

        // Scene Setup
        Scene scene = new Scene(mainLayout, 400, 550);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showLoginDialog() {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle("Login");

        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();

        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();

        Button submitButton = new Button("Login");
        submitButton.setOnAction(event -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            System.out.println("Login Attempt: Username: " + username + ", Password: " + password);

            dialog.close();
        });

        VBox layout = new VBox(10, usernameLabel, usernameField, passwordLabel, passwordField, submitButton);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);

        Scene dialogScene = new Scene(layout, 300, 200);
        dialog.setScene(dialogScene);
        dialog.showAndWait();
    }

    private void showSignUpDialog() {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle("Sign Up");

        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();

        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();

        Button submitButton = new Button("Sign Up");
        submitButton.setOnAction(event -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            System.out.println("Sign-Up Attempt: Username: " + username + ", Password: " + password);

            dialog.close();
        });

        VBox layout = new VBox(10, usernameLabel, usernameField, passwordLabel, passwordField, submitButton);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);

        Scene dialogScene = new Scene(layout, 300, 200);
        dialog.setScene(dialogScene);
        dialog.showAndWait();
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}








