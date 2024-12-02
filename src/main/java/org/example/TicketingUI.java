package org.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class TicketingUI extends Application {
    private TicketingSystem system;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Ticketing System");

        // Input fields for configuration
        TextField totalTicketsField = new TextField();
        TextField maxCapacityField = new TextField();
        TextField releaseRateField = new TextField();
        TextField retrievalRateField = new TextField();

        Button startButton = new Button("Start System");
        Button stopButton = new Button("Stop System");

        // Start system button action
        startButton.setOnAction(e -> {
            Configuration config = new Configuration();
            config.loadConfig(); // Load configuration
            system = new TicketingSystem(config);
            system.startSystem();
        });

        // Stop system button action
        stopButton.setOnAction(e -> {
            if (system != null) {
                system.stopSystem();
            }
        });

        // Layout setup
        VBox layout = new VBox(10, totalTicketsField, maxCapacityField, releaseRateField, retrievalRateField, startButton, stopButton);
        Scene scene = new Scene(layout, 300, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}



