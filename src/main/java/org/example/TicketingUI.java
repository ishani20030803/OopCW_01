package org.example;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class TicketingUI extends Application {

    private TicketingSystem ticketingSystem;
    private Scene mainScene;
    private Scene nextScene; // Scene for next step after login/signup
    private Scene loginScene; // Login scene
    private Scene signUpScene; // Sign up scene
    private final List<String> customers = new ArrayList<>(); // To store customer details
    private final List<Vendor> vendors = new ArrayList<>(); // To store vendor details

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Event Ticketing System");

        // Welcome Label
        Label welcomeLabel = new Label("Welcome to Event Ticketing System");
        welcomeLabel.setFont(Font.font("Arial", 24));
        welcomeLabel.setTextFill(Color.DARKBLUE);
        welcomeLabel.setStyle("-fx-background-color: #d6e7f2; -fx-padding: 20px; -fx-border-radius: 10px;");

        // Sign Up and Login Buttons
        Button signUpButton = createStyledButton("Sign Up");
        Button loginButton = createStyledButton("Login");
        Button nextButton = createStyledButton("Next");

        // Layout for the welcome screen
        VBox welcomeLayout = new VBox(15, welcomeLabel, signUpButton, loginButton, nextButton);
        welcomeLayout.setPadding(new Insets(30));
        welcomeLayout.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));

        // Event handling for Next button to go to the next page (parameters input page)
        nextButton.setOnAction(event -> showNextPage(primaryStage));

        // Button Actions for Sign Up and Login (we'll just show the next page for now)
        signUpButton.setOnAction(event -> showSignUpPage(primaryStage));
        loginButton.setOnAction(event -> showLoginPage(primaryStage));

        mainScene = new Scene(welcomeLayout, 600, 600); // Initial Scene with Welcome and Buttons
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }

    /**
     * Creates a styled button with decoration.
     */
    private Button createStyledButton(String text) {
        Button button = new Button(text);
        button.setStyle("-fx-background-color: #0078d7; -fx-text-fill: white; -fx-font-size: 14; -fx-padding: 10px 20px; -fx-border-radius: 5px;");

        button.setOnMouseEntered(event -> button.setStyle("-fx-background-color: #005ea6; -fx-text-fill: white; -fx-font-size: 14; -fx-padding: 10px 20px; -fx-border-radius: 5px;"));
        button.setOnMouseExited(event -> button.setStyle("-fx-background-color: #0078d7; -fx-text-fill: white; -fx-font-size: 14; -fx-padding: 10px 20px; -fx-border-radius: 5px;"));

        return button;
    }

    /**
     * Displays the Next Page with system parameters input fields and the Start/Stop buttons.
     */
    private void showNextPage(Stage primaryStage) {
        // Text fields for system parameters
        TextField maxCapacityField = new TextField();
        maxCapacityField.setPromptText("Max Capacity");
        maxCapacityField.setStyle("-fx-background-color: #e8f0fe; -fx-border-color: #0078d7; -fx-border-radius: 5px; -fx-padding: 8px;");

        TextField releaseRateField = new TextField();
        releaseRateField.setPromptText("Release Rate (ms)");
        releaseRateField.setStyle("-fx-background-color: #e8f0fe; -fx-border-color: #0078d7; -fx-border-radius: 5px; -fx-padding: 8px;");

        TextField retrievalRateField = new TextField();
        retrievalRateField.setPromptText("Retrieval Rate (ms)");
        retrievalRateField.setStyle("-fx-background-color: #e8f0fe; -fx-border-color: #0078d7; -fx-border-radius: 5px; -fx-padding: 8px;");

        // Start and Stop buttons
        Button startButton = createStyledButton("Start System");
        Button stopButton = createStyledButton("Stop System");
        stopButton.setDisable(true);

        // Button actions for start and stop
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

        // Back, Booking Ticket, and Cancel Ticket Buttons
        Button backButton = createStyledButton("Back");
        Button bookingTicketButton = createStyledButton("Booking Ticket");
        Button cancelTicketButton = createStyledButton("Cancel Ticket");

        // Button actions for Back, Booking Ticket, and Cancel Ticket
        backButton.setOnAction(event -> primaryStage.setScene(mainScene)); // Go back to main scene
        bookingTicketButton.setOnAction(event -> Logger.getInstance().log("Ticket booked successfully."));
        cancelTicketButton.setOnAction(event -> Logger.getInstance().log("Ticket has been canceled."));

        // Layout for the next page (system parameters)
        VBox nextLayout = new VBox(15,
                createStyledLabel("System Configuration", 20, Color.DARKBLUE),
                maxCapacityField, releaseRateField, retrievalRateField,
                new HBox(10, startButton, stopButton),
                new HBox(10, backButton, bookingTicketButton, cancelTicketButton)
        );
        nextLayout.setPadding(new Insets(30));
        nextLayout.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));

        nextScene = new Scene(nextLayout, 600, 700); // Scene for system parameters input page
        primaryStage.setScene(nextScene);
    }

    /**
     * Creates a styled label with custom font size and color.
     */
    private Label createStyledLabel(String text, int fontSize, Color color) {
        Label label = new Label(text);
        label.setFont(Font.font("Arial", fontSize));
        label.setTextFill(color);
        label.setStyle("-fx-background-color: #d6e7f2; -fx-padding: 10px; -fx-border-radius: 10px;");
        return label;
    }

    /**
     * Displays the Login Page where users can enter their username and password.
     */
    private void showLoginPage(Stage primaryStage) {
        VBox loginLayout = new VBox(15);
        loginLayout.setPadding(new Insets(30));
        loginLayout.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));

        Label loginLabel = createStyledLabel("Login", 20, Color.DARKBLUE);
        TextField usernameField = new TextField();
        usernameField.setPromptText("Enter your username");
        usernameField.setStyle("-fx-background-color: #f4f4f4; -fx-border-color: #0078d7; -fx-border-radius: 5px; -fx-padding: 8px;");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter your password");
        passwordField.setStyle("-fx-background-color: #f4f4f4; -fx-border-color: #0078d7; -fx-border-radius: 5px; -fx-padding: 8px;");

        ToggleGroup roleGroup = new ToggleGroup();
        RadioButton customerButton = new RadioButton("Customer");
        customerButton.setToggleGroup(roleGroup);
        RadioButton vendorButton = new RadioButton("Vendor");
        vendorButton.setToggleGroup(roleGroup);

        Button loginButton = createStyledButton("Login");
        Button backButton = createStyledButton("Back");

        loginButton.setOnAction(event -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            String role = roleGroup.getSelectedToggle() != null ? ((RadioButton) roleGroup.getSelectedToggle()).getText() : "";

            // Handle login logic here (check user credentials)
            Logger.getInstance().log("Logged in as " + username + " with role " + role);
        });

        backButton.setOnAction(event -> primaryStage.setScene(mainScene));

        loginLayout.getChildren().addAll(loginLabel, usernameField, passwordField, customerButton, vendorButton, loginButton, backButton);
        loginScene = new Scene(loginLayout, 600, 600);
        primaryStage.setScene(loginScene);
    }

    /**
     * Displays the Sign-Up Page where users can enter their details to create an account.
     */
    private void showSignUpPage(Stage primaryStage) {
        VBox signUpLayout = new VBox(15);
        signUpLayout.setPadding(new Insets(30));
        signUpLayout.setBackground(new Background(new BackgroundFill(Color.LIGHTYELLOW, CornerRadii.EMPTY, Insets.EMPTY)));

        Label signUpLabel = createStyledLabel("Sign Up", 20, Color.DARKBLUE);
        TextField signUpUsernameField = new TextField();
        signUpUsernameField.setPromptText("Enter your username");
        signUpUsernameField.setStyle("-fx-background-color: #f4f4f4; -fx-border-color: #0078d7; -fx-border-radius: 5px; -fx-padding: 8px;");

        PasswordField signUpPasswordField = new PasswordField();
        signUpPasswordField.setPromptText("Enter your password");
        signUpPasswordField.setStyle("-fx-background-color: #f4f4f4; -fx-border-color: #0078d7; -fx-border-radius: 5px; -fx-padding: 8px;");

        ToggleGroup signUpRoleGroup = new ToggleGroup();
        RadioButton signUpCustomerButton = new RadioButton("Customer");
        signUpCustomerButton.setToggleGroup(signUpRoleGroup);
        RadioButton signUpVendorButton = new RadioButton("Vendor");
        signUpVendorButton.setToggleGroup(signUpRoleGroup);

        Button signUpButton = createStyledButton("Sign Up");
        Button signUpBackButton = createStyledButton("Back");

        signUpButton.setOnAction(event -> {
            String username = signUpUsernameField.getText();
            String password = signUpPasswordField.getText();
            String role = signUpRoleGroup.getSelectedToggle() != null ? ((RadioButton) signUpRoleGroup.getSelectedToggle()).getText() : "";

            // Handle sign-up logic here (save user details to the system)
            Logger.getInstance().log("Signed up as " + username + " with role " + role);
        });

        signUpBackButton.setOnAction(event -> primaryStage.setScene(mainScene));

        signUpLayout.getChildren().addAll(signUpLabel, signUpUsernameField, signUpPasswordField, signUpCustomerButton, signUpVendorButton, signUpButton, signUpBackButton);
        signUpScene = new Scene(signUpLayout, 600, 600);
        primaryStage.setScene(signUpScene);
    }

    public static void main(String[] args) {
        launch(args);
    }
}


