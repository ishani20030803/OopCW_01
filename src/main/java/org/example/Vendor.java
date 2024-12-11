package org.example;

public class Vendor implements Runnable {
    private final TicketPool ticketPool;
    private final int releaseRateMs;
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
        running = false; // Set flag to stop the thread
    }
}