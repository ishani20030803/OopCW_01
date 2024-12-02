package org.example;

public class Vendor implements Runnable {
    private final TicketPool pool;
    private final int vendorInterval;

    public Vendor(TicketPool pool, int vendorInterval) {
        this.pool = pool;
        this.vendorInterval = vendorInterval;
    }

    @Override
    public void run() {
        int ticketId = 1;
        while (true) {
            try {
                Ticket ticket = new Ticket(ticketId++, "Event Simple", 1000);
                pool.addTicket(ticket);
                Thread.sleep(vendorInterval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}



