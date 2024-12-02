package org.example;

public class Ticket {
    private static int idCounter = 0;
    private final int ticketId;

    public Ticket() {
        this.ticketId = idCounter++;
    }

    public int getTicketId() {
        return ticketId;
    }
}



