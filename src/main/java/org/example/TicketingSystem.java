package org.example;

public class TicketingSystem {
    private final TicketPool ticketPool;
    private Thread vendorThread;
    private Thread customerThread;

    public TicketingSystem(Configuration configuration) {
        this.ticketPool = new TicketPool(configuration.getMaxCapacity());
        setupThreads(configuration);
    }

    private void setupThreads(Configuration configuration) {
        Vendor vendor = new Vendor(ticketPool, configuration.getReleaseRate());
        Customer customer = new Customer(ticketPool, configuration.getRetrievalRate());

        vendorThread = new Thread(vendor, "Vendor-Thread");
        customerThread = new Thread(customer, "Customer-Thread");
    }

    public void start() {
        if (vendorThread != null && customerThread != null) {
            vendorThread.start();
            customerThread.start();
            Logger.log("Ticketing system started.");
        }
    }

    public void stop() {
        if (vendorThread != null) {
            vendorThread.interrupt();
        }
        if (customerThread != null) {
            customerThread.interrupt();
        }
        Logger.log("Ticketing system stopped.");
    }

    public int getAvailableTickets() {
        return ticketPool.getAvailableTickets();
    }
}






