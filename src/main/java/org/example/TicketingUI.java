package org.example;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class TicketingUI extends Application {

    private TextArea logArea;
    private TicketingSystem ticketingSystem;
    private Scene mainScene; // Store the main scene for the Back button functionality
    private final List<String> customers = new ArrayList<>(); // To store customer details
    private final List<String> vendors = new ArrayList<>(); // To store vendor details

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Event Ticketing System");

        // Log Area
        logArea = new TextArea();
        logArea.setEditable(false);
        logArea.setPrefHeight(300);

        // Text Fields for Ticketing System Configurations
        TextField maxCapacityField = new TextField();
        maxCapacityField.setPromptText("Max Capacity");

        TextField releaseRateField = new TextField();
        releaseRateField.setPromptText("Release Rate (ms)");

        TextField retrievalRateField = new TextField();
        retrievalRateField.setPromptText("Retrieval Rate (ms)");

        // Buttons
        Button startButton = new Button("Start System");
        Button stopButton = new Button("Stop System");
        stopButton.setDisable(true);

        Button signUpButton = new Button("Sign Up");
        Button loginButton = new Button("Login");
        Button customersButton = new Button("Customers");
        Button vendorsButton = new Button("Vendors");

        // Log Listener
        Logger.getInstance().addListener(message -> Platform.runLater(() -> logArea.appendText(message + "\n")));

        // Button Actions
        startButton.setOnAction(event -> {
            try {
                int maxCapacity = Integer.parseInt(maxCapacityField.getText());
                int releaseRateMs = Integer.parseInt(releaseRateField.getText());
                int retrievalRateMs = Integer.parseInt(retrievalRateField.getText());

                ticketingSystem = new TicketingSystem(maxCapacity, releaseRateMs, retrievalRateMs);
                ticketingSystem.start();
                Logger.getInstance().log("System started.");

                startButton.setDisable(true);
                stopButton.setDisable(false);
            } catch (NumberFormatException e) {
                Logger.getInstance().log("Invalid input. Please enter numbers only.");
            }
        });

        stopButton.setOnAction(event -> {
            if (ticketingSystem != null) {
                ticketingSystem.stop();
                Logger.getInstance().log("System stopped.");
                startButton.setDisable(false);
                stopButton.setDisable(true);
            }
        });

        // Sign Up Button Action
        signUpButton.setOnAction(event -> showSignUpPage(primaryStage));

        // Login Button Action
        loginButton.setOnAction(event -> showLoginPage(primaryStage));

        // Customers Button Action
        customersButton.setOnAction(event -> showCustomerDetails(primaryStage));

        // Vendors Button Action
        vendorsButton.setOnAction(event -> showVendorDetails(primaryStage));

        // Layout
        VBox layout = new VBox(10,
                maxCapacityField, releaseRateField, retrievalRateField,
                startButton, stopButton,
                signUpButton, loginButton, customersButton, vendorsButton,
                logArea
        );
        layout.setPadding(new Insets(20));

        mainScene = new Scene(layout, 400, 600); // Save the main scene
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }

    /**
     * Displays the Sign-Up Page where users can select their role.
     */
    private void showSignUpPage(Stage primaryStage) {
        VBox signUpLayout = new VBox(10);
        signUpLayout.setPadding(new Insets(20));

        Label signUpLabel = new Label("Sign Up");
        TextField nameField = new TextField();
        nameField.setPromptText("Enter your name");

        ToggleGroup roleGroup = new ToggleGroup();
        RadioButton customerButton = new RadioButton("Customer");
        customerButton.setToggleGroup(roleGroup);
        RadioButton vendorButton = new RadioButton("Vendor");
        vendorButton.setToggleGroup(roleGroup);

        Button submitButton = new Button("Submit");
        Button backButton = new Button("Back");

        // Submit button action
        submitButton.setOnAction(event -> {
            String name = nameField.getText().trim();
            RadioButton selectedRole = (RadioButton) roleGroup.getSelectedToggle();

            if (name.isEmpty() || selectedRole == null) {
                Logger.getInstance().log("Please fill in all fields.");
                return;
            }

            String role = selectedRole.getText();
            if ("Customer".equals(role)) {
                customers.add(name);
            } else if ("Vendor".equals(role)) {
                vendors.add(name);
            }

            Logger.getInstance().log("Sign-Up Successful! Name: " + name + ", Role: " + role);

            // Return to the main page
            primaryStage.setScene(mainScene);
        });

        // Back button action
        backButton.setOnAction(event -> primaryStage.setScene(mainScene));

        signUpLayout.getChildren().addAll(signUpLabel, nameField, customerButton, vendorButton, submitButton, backButton);

        Scene signUpScene = new Scene(signUpLayout, 400, 300);
        primaryStage.setScene(signUpScene);
    }

    /**
     * Displays the Login Page where users can enter their username and password.
     */
    private void showLoginPage(Stage primaryStage) {
        VBox loginLayout = new VBox(10);
        loginLayout.setPadding(new Insets(20));

        Label loginLabel = new Label("Login");
        TextField usernameField = new TextField();
        usernameField.setPromptText("Enter your username");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter your password");

        Button loginButton = new Button("Login");
        Button backButton = new Button("Back");

        // Login button action
        loginButton.setOnAction(event -> {
            String username = usernameField.getText().trim();
            String password = passwordField.getText().trim();

            if (username.isEmpty() || password.isEmpty()) {
                Logger.getInstance().log("Please enter both username and password.");
                return;
            }

            Logger.getInstance().log("Login Successful! Username: " + username);
            primaryStage.setScene(mainScene); // Return to the main page
        });

        // Back button action
        backButton.setOnAction(event -> primaryStage.setScene(mainScene));

        loginLayout.getChildren().addAll(loginLabel, usernameField, passwordField, loginButton, backButton);

        Scene loginScene = new Scene(loginLayout, 400, 300);
        primaryStage.setScene(loginScene);
    }

    /**
     * Displays the customer details.
     */
    private void showCustomerDetails(Stage primaryStage) {
        VBox customerLayout = new VBox(10);
        customerLayout.setPadding(new Insets(20));

        Label customerLabel = new Label("Customer Details");
        TextArea customerDetailsArea = new TextArea(String.join("\n", customers));
        customerDetailsArea.setEditable(false);

        Button backButton = new Button("Back");
        backButton.setOnAction(event -> primaryStage.setScene(mainScene));

        customerLayout.getChildren().addAll(customerLabel, customerDetailsArea, backButton);

        Scene customerScene = new Scene(customerLayout, 400, 300);
        primaryStage.setScene(customerScene);
    }

    /**
     * Displays the vendor details.
     */
    private void showVendorDetails(Stage primaryStage) {
        VBox vendorLayout = new VBox(10);
        vendorLayout.setPadding(new Insets(20));

        Label vendorLabel = new Label("Vendor Details");
        TextArea vendorDetailsArea = new TextArea(String.join("\n", vendors));
        vendorDetailsArea.setEditable(false);

        Button backButton = new Button("Back");
        backButton.setOnAction(event -> primaryStage.setScene(mainScene));

        vendorLayout.getChildren().addAll(vendorLabel, vendorDetailsArea, backButton);

        Scene vendorScene = new Scene(vendorLayout, 400, 300);
        primaryStage.setScene(vendorScene);
    }

    public static void main(String[] args) {
        launch(args);
    }
}










