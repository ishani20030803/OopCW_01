package org.example;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class TicketingUI extends Application {
    private TicketPool ticketPool;
    private Thread vendorThread;
    private Thread customerThread;
    private Label ticketCountLabel;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Ticketing System");

        VBox root = new VBox(10);
        root.setStyle("-fx-padding: 10;");

        TextField totalTicketsField = new TextField();
        totalTicketsField.setPromptText("Total Tickets");

        TextField maxCapacityField = new TextField();
        maxCapacityField.setPromptText("Max Capacity");

        TextField releaseRateField = new TextField();
        releaseRateField.setPromptText("Release Rate (ms)");

        TextField retrievalRateField = new TextField();
        retrievalRateField.setPromptText("Retrieval Rate (ms)");

        ticketCountLabel = new Label("Tickets Available: 0");

        Button startButton = new Button("Start System");
        Button stopButton = new Button("Stop System");
        stopButton.setDisable(true);

        startButton.setOnAction(event -> {
            try {
                int totalTickets = Integer.parseInt(totalTicketsField.getText());
                int maxCapacity = Integer.parseInt(maxCapacityField.getText());
                int releaseRate = Integer.parseInt(releaseRateField.getText());
                int retrievalRate = Integer.parseInt(retrievalRateField.getText());

                if (totalTickets <= 0 || maxCapacity <= 0 || releaseRate <= 0 || retrievalRate <= 0) {
                    throw new NumberFormatException();
                }

                ticketPool = new TicketPool(maxCapacity);
                vendorThread = new Thread(new Vendor(ticketPool, releaseRate));
                customerThread = new Thread(new Customer(ticketPool, retrievalRate));

                vendorThread.start();
                customerThread.start();

                new Thread(() -> {
                    while (!Thread.currentThread().isInterrupted()) {
                        Platform.runLater(() -> ticketCountLabel.setText("Tickets Available: " + ticketPool.getAvailableTickets()));
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }).start();

                startButton.setDisable(true);
                stopButton.setDisable(false);
            } catch (NumberFormatException e) {
                ticketCountLabel.setText("Invalid input. Please check values.");
            }
        });

        stopButton.setOnAction(event -> {
            if (vendorThread != null) vendorThread.interrupt();
            if (customerThread != null) customerThread.interrupt();

            startButton.setDisable(false);
            stopButton.setDisable(true);
        });

        root.getChildren().addAll(totalTicketsField, maxCapacityField, releaseRateField, retrievalRateField, ticketCountLabel, startButton, stopButton);

        stage.setScene(new Scene(root, 300, 300));
        stage.show();
    }
}




