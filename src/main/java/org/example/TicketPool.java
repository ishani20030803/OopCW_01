package org.example;

import java.util.LinkedList;
import java.util.Queue;

public class TicketPool {

    private final int maxCapacity;
    private final Queue<Ticket> pool;

    public TicketPool(int maxCapacity) {
        this.maxCapacity = maxCapacity;
        this.pool = new LinkedList<>();
    }

    public synchronized void addTicket(Ticket ticket) {
        while (pool.size() >= maxCapacity) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        pool.add(ticket);
        notifyAll();
    }

    public synchronized Ticket retrieveTicket() {
        while (pool.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        Ticket ticket = pool.poll();
        notifyAll();
        return ticket;
    }

    public synchronized int getAvailableTickets() {
        return pool.size();
    }
}




