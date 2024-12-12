package org.example;

import com.google.gson.Gson;
// To serialize and deserialize JSON, import the Gson library.

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Configuration {
    // Defines the `Configuration` class, which controls the settings of the program.
    private static final String CONFIG_FILE = "config.json";

    private int maxCapacity;// Maximum capacity of tickets in the pool.
    private int releaseRateMs;// The amount of time (in milliseconds) that vendors take to release tickets.
    private int retrievalRateMs;// The amount of time (in milliseconds) that consumers take to get their tickets back.

    public static final int DEFAULT_MAX_CAPACITY = 5;
    public static final int DEFAULT_RELEASE_RATE_MS = 2000;
    public static final int DEFAULT_RETRIEVAL_RATE_MS = 3000;

    // Constructor
    public Configuration(int maxCapacity, int releaseRateMs, int retrievalRateMs) {
        this.maxCapacity = maxCapacity;
        this.releaseRateMs = releaseRateMs;
        this.retrievalRateMs = retrievalRateMs;
    }

    // Load configuration from file
    public static Configuration loadFromFile() {
        try (FileReader reader = new FileReader(CONFIG_FILE)) {
            Gson gson = new Gson();
            return gson.fromJson(reader, Configuration.class);
        } catch (IOException e) {
             // Handles with exceptions in the event that the file is missing or cannot be read.
            System.out.println("Configuration file not found. Using default settings.");
            return new Configuration(DEFAULT_MAX_CAPACITY, DEFAULT_RELEASE_RATE_MS, DEFAULT_RETRIEVAL_RATE_MS);
            // Provides the default settings in a new configuration object.
        }
    }

    // Save configuration to file
    public void saveToFile() {
        try (FileWriter writer = new FileWriter(CONFIG_FILE)) {
            Gson gson = new Gson();
            gson.toJson(this, writer);
        } catch (IOException e) {
            System.out.println("Failed to save configuration: " + e.getMessage());
        }
    }

    // Getters
    public int getMaxCapacity() {// Getter method for `maxCapacity
        return maxCapacity;
    }

    public int getReleaseRateMs() {// Getter method for `releaseRateMs`
        return releaseRateMs;
    }

    public int getRetrievalRateMs() {  // Getter method for `retrievalRateMs`.
        return retrievalRateMs;
    }
}




