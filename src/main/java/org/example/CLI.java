package org.example;

import java.util.Scanner;

public class CLI {
    public static void startCLI() {
        Logger logger = Logger.getInstance();
        Scanner scanner = new Scanner(System.in);

        logger.log("Welcome to the Event Ticketing System CLI!");

        // Prompt user for inputs with validation
        int maxCapacity = promptForInt(scanner, "Enter max capacity of the ticket pool (default: 5, valid range: 1-100): ",
                Configuration.DEFAULT_MAX_CAPACITY, 1, 100);
        int releaseRateMs = promptForInt(scanner, "Enter ticket release rate in milliseconds (default: 2000, valid range: 500-10000): ",
                Configuration.DEFAULT_RELEASE_RATE_MS, 500, 10000);
        int retrievalRateMs = promptForInt(scanner, "Enter ticket retrieval rate in milliseconds (default: 3000, valid range: 500-10000): ",
                Configuration.DEFAULT_RETRIEVAL_RATE_MS, 500, 10000);

        // Initialize and start the ticketing system
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

    /**
     * Prompts the user for an integer input, validates it, and ensures it falls within the specified range.
     *
     * @param scanner      the Scanner object for user input
     * @param message      the prompt message to display to the user
     * @param defaultValue the default value to use if the user provides no input
     * @param min          the minimum valid value
     * @param max          the maximum valid value
     * @return a valid integer input from the user
     */
    private static int promptForInt(Scanner scanner, String message, int defaultValue, int min, int max) {
        while (true) {
            Logger.getInstance().log(message);
            String input = scanner.nextLine().trim();

            // Use the default value if input is empty
            if (input.isEmpty()) {
                if (defaultValue >= min && defaultValue <= max) {
                    return defaultValue;
                } else {
                    Logger.getInstance().log("Default value is out of the valid range. Please enter a valid number.");
                    continue;
                }
            }

            // Parse and validate the input
            try {
                int value = Integer.parseInt(input);
                if (value >= min && value <= max) {
                    return value;
                } else {
                    Logger.getInstance().log("Value must be between " + min + " and " + max + ".");
                }
            } catch (NumberFormatException e) {
                Logger.getInstance().log("Invalid input. Please enter a valid number.");
            }
        }
    }
}

