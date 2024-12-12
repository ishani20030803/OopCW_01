package org.example;

public class Ticket {
    private final int ticketId;
    private final String eventName;// The event name that is linked to the ticket.


    private final double ticketPrice;

    public Ticket(int ticketId, String eventName, double ticketPrice) {
        // Constructor to set the properties of a new `Ticket` object.
        this.ticketId = ticketId;
        this.eventName = eventName;
        this.ticketPrice = ticketPrice;
    }

    @Override
    public String toString() {
        // Provides a string representation of the `Ticket` by overriding the `toString` method.
        return "Ticket{" +
                "ticketId=" + ticketId +
                ", eventName='" + eventName + '\'' +
                ", ticketPrice=" + ticketPrice +
                '}';// Provides a formatted string with the price, event name, and ticket ID.
    }
}













