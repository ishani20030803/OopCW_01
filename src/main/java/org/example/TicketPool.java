package org.example;

import java.util.LinkedList;
import java.util.Queue;

public class TicketPool {
    private final int maxCapacity;
    private final Queue<Ticket> tickets;

    public TicketPool(int maxCapacity) {
        this.maxCapacity = maxCapacity;
        this.tickets = new LinkedList<>();
    }

    // Method to add a ticket to the pool
    public synchronized void addTicket(Ticket ticket) throws InterruptedException {
        while (tickets.size() >= maxCapacity) {
            wait(); // Wait if the pool is full
        }
        tickets.offer(ticket);
        notifyAll(); // Notify customers waiting to retrieve tickets
    }

    // Method to retrieve a ticket from the pool
    public synchronized Ticket retrieveTicket() throws InterruptedException {
        while (tickets.isEmpty()) {
            wait(); // Wait if the pool is empty
        }
        Ticket ticket = tickets.poll();
        notifyAll(); // Notify vendors waiting to add tickets
        return ticket;
    }

    // Method to get the current number of available tickets
    public synchronized int getAvailableTickets() {
        return tickets.size();
    }

    // Getter for max capacity
    public int getMaxCapacity() {
        return maxCapacity;
    }
}






