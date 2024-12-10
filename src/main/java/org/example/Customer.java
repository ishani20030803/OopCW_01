package org.example;

import java.util.ArrayList;
import java.util.List;

public class Customer implements Runnable {
    private final TicketPool ticketPool;
    private final int retrievalRateMs;
    private final String name;
    private final List<Ticket> purchasedTickets = new ArrayList<>();
    private volatile boolean running = true; // Flag to stop thread

    public Customer(TicketPool ticketPool, int retrievalRateMs, String name) {
        this.ticketPool = ticketPool;
        this.retrievalRateMs = retrievalRateMs;
        this.name = name;
    }

    @Override
    public void run() {
        while (running) {
            try {
                Ticket ticket = ticketPool.retrieveTicket();
                if (ticket != null) {
                    purchasedTickets.add(ticket);
                    System.out.println(name + " bought ticket: " + ticket);
                }
                Thread.sleep(retrievalRateMs);
            } catch (InterruptedException e) {
                break; // Gracefully exit when interrupted
            }
        }
        System.out.println(name + " stopped.");
    }

    public void stop() {
        running = false; // Set flag to stop the thread
    }

    public String getName() {
        return name;
    }

    public List<Ticket> getPurchasedTickets() {
        return purchasedTickets;
    }
}



