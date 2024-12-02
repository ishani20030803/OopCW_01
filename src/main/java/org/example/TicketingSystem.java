package org.example;

public class TicketingSystem {
    public static void runSystem(Configuration config) {
        TicketPool pool = new org.example.TicketPool(config.getPoolCapacity());

        // Create vendor threads
        for (int i = 0; i < 5; i++) { // 5 Vendors
            new Thread(new Vendor(pool, config.getVendorInterval()), "Vendor-" + i).start();
        }

        // Create customer threads
        for (int i = 0; i < 5; i++) { // 5 Customers
            new Thread(new Customer(pool, config.getCustomerInterval()), "Customer-" + i).start();
        }
    }
}




