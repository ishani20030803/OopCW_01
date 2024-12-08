package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Logger logger = Logger.getInstance();
        Scanner scanner = new Scanner(System.in);

        logger.log("Welcome to the Event Ticketing System CLI!");

        // Prompt user for inputs
        int maxCapacity = promptForInt(scanner, "Enter max capacity of the ticket pool (default: 5): ", Configuration.DEFAULT_MAX_CAPACITY);
        int releaseRateMs = promptForInt(scanner, "Enter ticket release rate in milliseconds (default: 2000): ", Configuration.DEFAULT_RELEASE_RATE_MS);
        int retrievalRateMs = promptForInt(scanner, "Enter ticket retrieval rate in milliseconds (default: 3000): ", Configuration.DEFAULT_RETRIEVAL_RATE_MS);

        // Save configuration to file
        Configuration config = new Configuration(maxCapacity, releaseRateMs, retrievalRateMs);
        config.saveToFile();

        // Load configuration and initialize the ticketing system
        TicketingSystem ticketingSystem = new TicketingSystem(maxCapacity, releaseRateMs, retrievalRateMs);
        ticketingSystem.start();

        logger.log("System started! Press Enter to check ticket availability or type 'exit' to stop.");
        while (true) {
            String input = scanner.nextLine().trim();
            if ("exit".equalsIgnoreCase(input)) {
                break;
            }
            logger.log("Tickets Available: " + ticketingSystem.getAvailableTickets());
        }

        ticketingSystem.stop();
        logger.log("System stopped. Goodbye!");
    }

    private static int promptForInt(Scanner scanner, String message, int defaultValue) {
        while (true) {
            Logger.getInstance().log(message);
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                return defaultValue;
            }

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                Logger.getInstance().log("Invalid input. Please enter a valid number.");
            }
        }
    }
}














