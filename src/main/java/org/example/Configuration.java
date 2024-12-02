package org.example;

public class Configuration {
    private int totalTickets = 1000;
    private int maxCapacity = 1000;
    private int releaseRate = 1000;  // milliseconds for vendors
    private int retrievalRate = 500; // milliseconds for customers

    // Method to load configuration (can be extended to read from a file)
    public void loadConfig() {
        // Hardcoded values for simplicity
        this.totalTickets = 1000;
        this.maxCapacity = 1000;
        this.releaseRate = 1000;
        this.retrievalRate = 500;
    }

    public int getTotalTickets() {
        return totalTickets;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public int getReleaseRate() {
        return releaseRate;
    }

    public int getRetrievalRate() {
        return retrievalRate;
    }
}

