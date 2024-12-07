package org.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TicketingSystem {

    private final TicketPool ticketPool;
    private final ExecutorService executorService;

    /**
     * Main constructor - initializes ticketPool and executorService.
     */
    public TicketingSystem(int maxCapacity, int releaseRateMs, int retrievalRateMs) {
        // Proper initialization of both fields
        this.ticketPool = new TicketPool(maxCapacity); // Initialize the pool with capacity
        this.executorService = Executors.newCachedThreadPool(); // Create a thread pool for system operations

        // Start vendor and customer threads
        initializeVendorsAndCustomers(releaseRateMs, retrievalRateMs);
    }

    /**
     * Initializes vendors and customers in the thread pool.
     */
    private void initializeVendorsAndCustomers(int releaseRateMs, int retrievalRateMs) {
        // Initialize two vendors
        executorService.submit(new Vendor(ticketPool, releaseRateMs, "Vendor-1"));
        executorService.submit(new Vendor(ticketPool, releaseRateMs, "Vendor-2"));

        // Initialize three customers
        executorService.submit(new Customer(ticketPool, retrievalRateMs, "Customer-1"));
        executorService.submit(new Customer(ticketPool, retrievalRateMs, "Customer-2"));
        executorService.submit(new Customer(ticketPool, retrievalRateMs, "Customer-3"));
    }

    /**
     * Log the system startup.
     */
    public void start() {
        Logger.log("Ticketing system started.");
    }

    /**
     * Stops all operations and shuts down the thread pool.
     */
    public void stop() {
        executorService.shutdownNow();
        Logger.log("Ticketing system stopped.");
    }

    /**
     * Retrieves the available number of tickets.
     */
    public int getAvailableTickets() {
        return ticketPool.getAvailableTickets();
    }
}


















