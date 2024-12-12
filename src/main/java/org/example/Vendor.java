package org.example;

public class Vendor implements Runnable {
    // Allows the `Vendor` class to execute on a different thread by implementing the `Runnable` interface.
    private final TicketPool ticketPool;
    private final int releaseRateMs;// The milliseconds that pass between ticket releases.
    private final String name;
    private volatile boolean running = true; // Flag to stop thread

    public Vendor(TicketPool ticketPool, int releaseRateMs, String name) {
        this.ticketPool = ticketPool;
        this.releaseRateMs = releaseRateMs;
        this.name = name;
    }




    @Override
    public void run() {
        while (running) {
            try {
                Ticket ticket = new Ticket((int) (Math.random() * 1000), "Event", Math.random() * 100);
                ticketPool.addTicket(ticket);
                System.out.println(name + " added ticket: " + ticket);
                Thread.sleep(releaseRateMs);
            } catch (InterruptedException e) {
                break; // Gracefully exit when interrupted
            }
        }
        System.out.println(name + " stopped.");
    }

    public void stop() {
        running = false;
        // The thread leaves the loop in `run` when the `running` flag is set to `false`.
    }
}