package org.example;

public class Main {
    public static void main(String[] args) {
        // Initialize the system configuration
        Configuration config = new Configuration();
        config.loadConfig(); // Load configuration with hardcoded values or from file

        // Initialize the Ticketing System with the loaded configuration
        TicketingSystem ticketingSystem = new TicketingSystem(config);

        // Start the system (Simulating concurrent vendors and customers)
        System.out.println("Starting Ticketing System...");
        ticketingSystem.startSystem();

        // For testing purposes, let's wait for a while before stopping
        try {
            Thread.sleep(10000); // Run the system for 10 seconds for example
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Stop the system
        System.out.println("Stopping Ticketing System...");
        ticketingSystem.stopSystem();
    }
}







