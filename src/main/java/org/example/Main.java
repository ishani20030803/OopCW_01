package org.example;// This helps with organizing the project by declaring the package that contains the Main class.



import java.util.Scanner;// To handle user input, import the Scanner class from the java.util package.

public class Main {// Sets the Main class, which is the application's entry point, to public.


    public static void main(String[] args) {// The primary method that initiates program execution.
        // As command-line arguments, it accepts an array of strings.
        System.out.println("Select mode:");// Displays a prompt asking the user to select the operating mode.
        System.out.println("1. Command Line Interface (CLI)");// Outputs the Command Line Interface (CLI) selection option 1.
        System.out.println("2. Graphical User Interface (GUI)");// Prints the Graphical User Interface (GUI) selection option number two.



        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        if (choice == 1) {
            CLI.startCLI();
            // Starts the CLI mode by calling the `startCLI` function in the CLI class if the user chooses 1.
        } else if (choice == 2) {
            TicketingUI.main(args);
        } else {
            System.out.println("Invalid choice. Exiting...");
            // Shows an error message and ends the application if the user inputs an invalid choice.

        }
    }
}















