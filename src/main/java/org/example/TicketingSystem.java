package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
// Brings in the classes required to manage lists and multi-threading.

public class TicketingSystem {
    // Defines the `TicketingSystem` class, which is responsible for overseeing the ticketing procedure.



    private final TicketPool ticketPool;
    private final ExecutorService executorService;
    // Thread pool to control customers' and vendors' concurrent execution.


    private final List<Vendor> vendors = new ArrayList<>();
    private final List<Customer> customers = new ArrayList<>();

    public TicketingSystem(int maxCapacity, int releaseRateMs, int retrievalRateMs) {
        this.ticketPool = new TicketPool(maxCapacity);
        // Constructs a `TicketPool` with the maximum capacity that has been supplied.
        this.executorService = Executors.newCachedThreadPool();
        initializeVendorsAndCustomers(releaseRateMs, retrievalRateMs);
    }

    private void initializeVendorsAndCustomers(int releaseRateMs, int retrievalRateMs) {
        // Create vendors
        for (int i = 1; i <= 2; i++) {
            Vendor vendor = new Vendor(ticketPool, releaseRateMs, "Vendor-" + i);
            // Generates a `Vendor` with a unique name, release rate, and shared ticket pool.
            vendors.add(vendor);
            executorService.submit(vendor);
        }

        // Create customers
        for (int i = 1; i <= 2; i++) {
            Customer customer = new Customer(ticketPool, retrievalRateMs, "Customer-" + i);
            // Generates a `Customer` with a unique name, retrieval rate, and shared ticket pool.


            customers.add(customer);
            executorService.submit(customer);
        }
    }

    public void start() {
        Logger.getInstance().log("Ticketing system is starting...");
    }

    public void stop() {
        // Puts a stop to the ticketing system by stopping all customers and vendors.


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

    public List<Customer> getCustomers() {
        // Makes the system's customer list accessible.
        return customers;
    }
}

















