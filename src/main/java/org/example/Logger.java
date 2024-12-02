package org.example;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {
    private static final String LOG_FILE = "system_log.txt";

    public static void log(String message) {
        System.out.println(message); // Log to console
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE, true))) {
            writer.write(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + ": " + message);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error writing log: " + e.getMessage());
        }
    }
}

