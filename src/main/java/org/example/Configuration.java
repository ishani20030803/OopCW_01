package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.*;

public class Configuration {
    private int totalTickets = 50; // Default total tickets
    private int poolCapacity = 10; // Default pool capacity
    private int vendorInterval = 1000; // Interval in ms
    private int customerInterval = 800; // Interval in ms

    // Getters and Setters
    public int getTotalTickets() { return totalTickets; }
    public void setTotalTickets(int totalTickets) {
        if(totalTickets <= 0) {
            System.out.println("Total tickets must be greater than 0!");
        } else {
            this.totalTickets = totalTickets;
        }
    }

    public int getPoolCapacity() { return poolCapacity; }
    public void setPoolCapacity(int poolCapacity) {
        if(poolCapacity <= 0) {
            System.out.println("Pool capacity must be greater than 0!");
        } else {
            this.poolCapacity = poolCapacity;
        }
    }

    public int getVendorInterval() { return vendorInterval; }
    public void setVendorInterval(int vendorInterval) {
        if(vendorInterval <= 0) {
            System.out.println("Vendor interval must be greater than 0!");
        } else {
            this.vendorInterval = vendorInterval;
        }
    }

    public int getCustomerInterval() { return customerInterval; }
    public void setCustomerInterval(int customerInterval) {
        if(customerInterval <= 0) {
            System.out.println("Customer interval must be greater than 0!");
        } else {
            this.customerInterval = customerInterval;
        }
    }

    // Serialization to JSON format
    public void saveConfigurationToJSON(String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(this, writer);
            System.out.println("Configuration saved to " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Load configuration from JSON file
    public static Configuration loadConfigurationFromJSON(String filename) {
        try (FileReader reader = new FileReader(filename)) {
            Gson gson = new Gson();
            return gson.fromJson(reader, Configuration.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

