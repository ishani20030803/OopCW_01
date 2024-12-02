package org.example;

import java.util.concurrent.locks.*;

public class TicketPool {
    private int availableTickets;
    private final int maxCapacity;
    private final Lock lock;

    public TicketPool(int maxCapacity) {
        this.maxCapacity = maxCapacity;
        this.availableTickets = 0;
        this.lock = new ReentrantLock();
    }

    // Vendor releases tickets
    public void addTickets(int count) {
        lock.lock();
        try {
            if (availableTickets + count <= maxCapacity) {
                availableTickets += count;
                Logger.log(count + " tickets released. Total tickets: " + availableTickets);
            }
        } finally {
            lock.unlock();
        }
    }

    // Customer retrieves tickets
    public void removeTicket() {
        lock.lock();
        try {
            if (availableTickets > 0) {
                availableTickets--;
                Logger.log("One ticket purchased. Tickets remaining: " + availableTickets);
            }
        } finally {
            lock.unlock();
        }
    }

    public int getAvailableTickets() {
        return availableTickets;
    }
}

