package org.example;

import com.google.gson.Gson;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Configuration {
    private static final String CONFIG_FILE = "config.json";

    private int maxCapacity;
    private int releaseRateMs;
    private int retrievalRateMs;

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
            System.out.println("Configuration file not found. Using default settings.");
            return new Configuration(DEFAULT_MAX_CAPACITY, DEFAULT_RELEASE_RATE_MS, DEFAULT_RETRIEVAL_RATE_MS);
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
    public int getMaxCapacity() {
        return maxCapacity;
    }

    public int getReleaseRateMs() {
        return releaseRateMs;
    }

    public int getRetrievalRateMs() {
        return retrievalRateMs;
    }
}





