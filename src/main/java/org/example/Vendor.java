package org.example;

public class Vendor implements Runnable {
    private final TicketPool ticketPool;
    private final int releaseRateMs;
    private final String name;

    public Vendor(TicketPool ticketPool, int releaseRateMs, String name) {
        this.ticketPool = ticketPool;
        this.releaseRateMs = releaseRateMs;
        this.name = name;
    }

    @Override
    public void run() {
        int ticketId = 1;
        while (true) {
            try {
                Ticket ticket = new Ticket(ticketId++, "Event Simple", 1000.0);
                ticketPool.addTicket(ticket);
                System.out.println(name + " added ticket: " + ticket);
                Thread.sleep(releaseRateMs);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}



