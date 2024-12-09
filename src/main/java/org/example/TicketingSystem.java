package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TicketingSystem {

    private final TicketPool ticketPool;
    private final ExecutorService executorService;
    private final List<Vendor> vendors = new ArrayList<>();
    private final List<Customer> customers = new ArrayList<>();

    public TicketingSystem(int maxCapacity, int releaseRateMs, int retrievalRateMs) {
        this.ticketPool = new TicketPool(maxCapacity);
        this.executorService = Executors.newCachedThreadPool();
        initializeVendorsAndCustomers(releaseRateMs, retrievalRateMs);
    }

    private void initializeVendorsAndCustomers(int releaseRateMs, int retrievalRateMs) {
        // Create vendors
        for (int i = 1; i <= 2; i++) {
            Vendor vendor = new Vendor(ticketPool, releaseRateMs, "Vendor-" + i);
            vendors.add(vendor);
            executorService.submit(vendor);
        }

        // Create customers
        for (int i = 1; i <= 2; i++) {
            Customer customer = new Customer(ticketPool, retrievalRateMs, "Customer-" + i);
            customers.add(customer);
            executorService.submit(customer);
        }
    }

    public void start() {
        Logger.getInstance().log("Ticketing system is starting...");
    }

    public void stop() {
        // Stop all vendors
        for (Vendor vendor : vendors) {
            vendor.stop();
        }

        // Stop all customers
        for (Customer customer : customers) {
            customer.stop();
        }

        executorService.shutdownNow();
        Logger.getInstance().log("Ticketing system stopped.");
    }

    public int getAvailableTickets() {
        return ticketPool.getAvailableTickets();
    }
}


















