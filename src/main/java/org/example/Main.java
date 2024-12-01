package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // CLI Interface for user input
        Configuration config = getConfigurationFromCLI();

        // Running the system with provided configuration
        TicketingSystem.runSystem(config);
    }

    private static Configuration getConfigurationFromCLI() {
        Scanner scanner = new Scanner(System.in);
        Configuration config = new Configuration();

        System.out.print("Enter total number of tickets: ");
        config.setTotalTickets(Integer.parseInt(scanner.nextLine()));

        System.out.print("Enter ticket release rate (in milliseconds): ");
        config.setVendorInterval(Integer.parseInt(scanner.nextLine()));

        System.out.print("Enter customer retrieval rate (in milliseconds): ");
        config.setCustomerInterval(Integer.parseInt(scanner.nextLine()));

        System.out.print("Enter maximum ticket capacity: ");
        config.setPoolCapacity(Integer.parseInt(scanner.nextLine()));

        // Optionally save to a file
        saveConfigurationToFile(config);

        return config;
    }

    private static void saveConfigurationToFile(Configuration config) {
        // Save configuration to file (serialization or JSON)
        config.saveConfigurationToJSON("config.json");
    }
}







