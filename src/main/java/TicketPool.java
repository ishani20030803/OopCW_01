package org.example;

import java.util.LinkedList;
import java.util.Queue;

public class TicketPool {
    private final Queue<org.example.Ticket> pool = new LinkedList<>();
    private final int capacity;

    public TicketPool(int capacity) {
        this.capacity = capacity;
    }

    public synchronized void addTicket(org.example.Ticket ticket) throws InterruptedException {
        while (pool.size() == capacity) {
            wait(); // Wait until there's space in the pool
        }
        pool.add(ticket);
        System.out.println("Vendor has added a ticket to the Pool. Current size is " + pool.size());
        notifyAll(); // Notify customers waiting to buy tickets
    }

    public synchronized org.example.Ticket buyTicket() throws InterruptedException {
        while (pool.isEmpty()) {
            wait(); // Wait until a ticket is available
        }
        org.example.Ticket ticket = pool.poll();
        System.out.println("Customer has bought a ticket from the pool. Current size is " + pool.size());
        notifyAll(); // Notify vendors waiting to add tickets
        return ticket;
    }
}


