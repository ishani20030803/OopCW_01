package org.example;

import javafx.application.Platform;
import javafx.scene.control.Button;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TicketingSystem {

    private final TicketPool ticketPool;
    private final Button ticketsAvailableButton;
    private final ExecutorService executorService;

    public TicketingSystem(int maxCapacity, int releaseRateMs, int retrievalRateMs, Button ticketsAvailableButton) {
        this.ticketsAvailableButton = ticketsAvailableButton;
        ticketPool = new TicketPool(maxCapacity);
        executorService = Executors.newCachedThreadPool();

        // Vendors
        executorService.submit(new Vendor(ticketPool, releaseRateMs, "Vendor-1"));
        executorService.submit(new Vendor(ticketPool, releaseRateMs, "Vendor-2"));

        // Customers
        executorService.submit(new Customer(ticketPool, retrievalRateMs, "Customer-1"));
        executorService.submit(new Customer(ticketPool, retrievalRateMs, "Customer-2"));
        executorService.submit(new Customer(ticketPool, retrievalRateMs, "Customer-3"));
    }

    public void start() {
        updateTicketsAvailable();
    }

    public void stop() {
        executorService.shutdownNow();
    }

    public int getAvailableTickets() {
        return ticketPool.getAvailableTickets();
    }

    private void updateTicketsAvailable() {
        new Thread(() -> {
            while (!executorService.isShutdown()) {
                Platform.runLater(() -> ticketsAvailableButton.setText("Tickets Available: " + ticketPool.getAvailableTickets()));
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    break;
                }
            }
        }).start();
    }
}









