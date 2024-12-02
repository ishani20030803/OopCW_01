package org.example;

import java.util.concurrent.*;

public class TicketingSystem {
    private final TicketPool ticketPool;
    private final ExecutorService vendorService;
    private final ExecutorService customerService;
    private final Configuration config;

    public TicketingSystem(Configuration config) {
        this.config = config;
        this.ticketPool = new TicketPool(config.getMaxCapacity());
        this.vendorService = Executors.newFixedThreadPool(5); // Max 5 vendors
        this.customerService = Executors.newFixedThreadPool(10); // Max 10 customers
    }

    public void startSystem() {
        // Start vendors and customers
        for (int i = 0; i < 5; i++) {
            vendorService.submit(new Vendor(ticketPool, config.getReleaseRate()));
        }

        for (int i = 0; i < 10; i++) {
            customerService.submit(new Customer(ticketPool, config.getRetrievalRate()));
        }
    }

    public void stopSystem() {
        vendorService.shutdownNow();
        customerService.shutdownNow();
    }
}





