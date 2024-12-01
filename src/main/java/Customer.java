package org.example;

public class Customer implements Runnable {
    private final org.example.TicketPool pool;
    private final int customerInterval;

    // Constructor to initialize the TicketPool and customer interval
    public Customer(org.example.TicketPool pool, int customerInterval) {
        this.pool = pool;
        this.customerInterval = customerInterval;
    }

    @Override
    public void run() {
        while (true) {
            try {
                // Attempt to buy a ticket from the pool
                org.example.Ticket ticket = pool.buyTicket();
                System.out.println("Ticket bought by Customer: " + ticket);

                // Simulate the customer retrieval rate (interval between ticket purchases)
                Thread.sleep(customerInterval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
