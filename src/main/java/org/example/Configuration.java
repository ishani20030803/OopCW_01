package org.example;

public class Configuration {
    private int totalTickets;
    private int maxCapacity;
    private int releaseRate; // in milliseconds
    private int retrievalRate; // in milliseconds

    public Configuration(int totalTickets, int maxCapacity, int releaseRate, int retrievalRate) {
        this.totalTickets = totalTickets;
        this.maxCapacity = maxCapacity;
        this.releaseRate = releaseRate;
        this.retrievalRate = retrievalRate;
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

