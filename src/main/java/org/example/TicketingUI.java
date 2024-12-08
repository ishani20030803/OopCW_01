package org.example;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TicketingUI extends Application {

    private TextArea logArea;
    private TicketingSystem ticketingSystem;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Event Ticketing System");

        // Log Area
        logArea = new TextArea();
        logArea.setEditable(false);
        logArea.setPrefHeight(300);

        // Input Fields
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

        startButton.setOnAction(event -> {
            try {
                int maxCapacity = Integer.parseInt(maxCapacityField.getText());
                int releaseRateMs = Integer.parseInt(releaseRateField.getText());
                int retrievalRateMs = Integer.parseInt(retrievalRateField.getText());

                ticketingSystem = new TicketingSystem(maxCapacity, releaseRateMs, retrievalRateMs);
                ticketingSystem.start();
                Logger.getInstance().log("System started!");

                startButton.setDisable(true);
                stopButton.setDisable(false);
            } catch (NumberFormatException e) {
                Logger.getInstance().log("Invalid input. Please provide numeric values.");
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

        VBox layout = new VBox(10, maxCapacityField, releaseRateField, retrievalRateField, startButton, stopButton, logArea);
        layout.setPadding(new Insets(20));

        // Attach Logger to TextArea
        Logger.getInstance().addListener(message -> Platform.runLater(() -> logArea.appendText(message + "\n")));

        primaryStage.setScene(new Scene(layout, 400, 500));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}











