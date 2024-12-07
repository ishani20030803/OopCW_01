package org.example;

public class Customer implements Runnable {
    private final TicketPool ticketPool;
    private final int retrievalRateMs;
    private final String name;

    public Customer(TicketPool ticketPool, int retrievalRateMs, String name) {
        this.ticketPool = ticketPool;
        this.retrievalRateMs = retrievalRateMs;
        this.name = name;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Ticket ticket = ticketPool.retrieveTicket();
                System.out.println(name + " bought ticket: " + ticket);
                Thread.sleep(retrievalRateMs);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}




