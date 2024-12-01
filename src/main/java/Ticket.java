package org.example;

public class Ticket {
    private int ticketId;
    private String eventName;
    private int ticketPrice;

    public Ticket(int ticketId, String eventName, int ticketPrice) {
        this.ticketId = ticketId;
        this.eventName = eventName;
        this.ticketPrice = ticketPrice;
    }

    @Override
    public String toString() {
        return "Ticket{ticketId=" + ticketId +
                ", eventName='" + eventName + '\'' +
                ", ticketPrice=" + ticketPrice + '}';
    }
}



