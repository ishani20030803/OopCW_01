package org.example;// Defines the package that contains this class.



import java.util.ArrayList;
import java.util.List;

public class Customer implements Runnable {
    // Declares the `Customer` class, which enables instances to
    //execute in a different thread by implementing the `Runnable` interface.
    private final TicketPool ticketPool;
    // A reference to the shared `TicketPool` object that will be used to have tickets
    private final int retrievalRateMs;
    private final String name;
    private final List<Ticket> purchasedTickets = new ArrayList<>();
    private volatile boolean running = true;
    // A signal to show if the thread should keep running. {volatile` is marked to provide thread safety.



    public Customer(TicketPool ticketPool, int retrievalRateMs, String name) {
        this.ticketPool = ticketPool;
        this.retrievalRateMs = retrievalRateMs;
        this.name = name;
    }

    @Override
    public void run() {
        while (running) {// Continue running as long as the `running` variable is set to true.


            try {
                Ticket ticket;
                ticket = ticketPool.retrieveTicket();
                if (ticket != null) {
                    // Check whether a ticket was obtained successfully.
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

    public String getName() {// To stop the `run` loop, set the `running` flag to false.

        return name;
    }

    public List<Ticket> getPurchasedTickets() {
        // The list of tickets that have been purchased is obtained.



        return purchasedTickets;
    }
}



