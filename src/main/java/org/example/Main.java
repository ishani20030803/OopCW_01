package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Select mode:");
        System.out.println("1. Command Line Interface (CLI)");
        System.out.println("2. Graphical User Interface (GUI)");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        if (choice == 1) {
            CLI.startCLI();
        } else if (choice == 2) {
            TicketingUI.main(args);
        } else {
            System.out.println("Invalid choice. Exiting...");
        }
    }
}















