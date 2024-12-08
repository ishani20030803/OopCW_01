package org.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javafx.scene.control.Button;

public class TicketingSystem {

    private final TicketPool ticketPool;
    private final ExecutorService executorService;
    private final Button ticketsAvailableButton; // Optional for GUI updates

    // Constructor for CLI without Button
    public TicketingSystem(int maxCapacity, int releaseRateMs, int retrievalRateMs) {
        this(maxCapacity, releaseRateMs, retrievalRateMs, null); // Call main constructor
    }

    // Main constructor with Button for GUI
    public TicketingSystem(int maxCapacity, int releaseRateMs, int retrievalRateMs, Button ticketsAvailableButton) {
        this.ticketPool = new TicketPool(maxCapacity);
        this.executorService = Executors.newCachedThreadPool();
        this.ticketsAvailableButton = ticketsAvailableButton;

        initializeVendorsAndCustomers(releaseRateMs, retrievalRateMs);
        if (ticketsAvailableButton != null) {
            updateTicketsAvailableUI();
        }
    }

    private void initializeVendorsAndCustomers(int releaseRateMs, int retrievalRateMs) {
        executorService.submit(new Vendor(ticketPool, releaseRateMs, "Vendor-1"));
        executorService.submit(new Vendor(ticketPool, releaseRateMs, "Vendor-2"));
        executorService.submit(new Customer(ticketPool, retrievalRateMs, "Customer-1"));
        executorService.submit(new Customer(ticketPool, retrievalRateMs, "Customer-2"));
    }

    public void start() {
        Logger.getInstance().log("Ticketing system started."); // Fixed: Accessing the Logger instance
    }

    public void stop() {
        executorService.shutdownNow();
        Logger.getInstance().log("Ticketing system stopped."); // Fixed: Accessing the Logger instance
    }

    public int getAvailableTickets() {
        return ticketPool.getAvailableTickets();
    }

    private void updateTicketsAvailableUI() {
        new Thread(() -> {
            while (!executorService.isShutdown()) {
                javafx.application.Platform.runLater(() -> {
                    if (ticketsAvailableButton != null) {
                        ticketsAvailableButton.setText("Tickets Available: " + getAvailableTickets());
                    }
                });
                try {
                    Thread.sleep(500); // Update every 500 ms
                } catch (InterruptedException e) {
                    break;
                }
            }
        }).start();
    }
}





















