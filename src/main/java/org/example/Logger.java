package org.example;

import java.util.ArrayList;
import java.util.List;// Imports necessary classes for managing a list of log listeners.

public class Logger {
    private static Logger instance;// To enforce the Singleton pattern, use a static instance of the Logger class.
    private final List<LogListener> listeners = new ArrayList<>();

    private Logger() {}
    // To stop direct instantiation from outside the class, use a private constructor.
    public static synchronized Logger getInstance() {
        if (instance == null) {// Create the instance if it hasn't already been built.
            instance = new Logger();
        }
        return instance;
    }

    public synchronized void log(String message) {
        // Notifies all listeners and logs a message to the terminal (thread-safe).


        System.out.println(message); // Always print to the console
        notifyListeners(message);
    }

    public synchronized void addListener(LogListener listener) {
        listeners.add(listener);
    }

    private void notifyListeners(String message) {
        for (LogListener listener : listeners) {
            listener.onLog(message);
        }
    }

    public interface LogListener {
        void onLog(String message);
        // A method that takes the message as an input and is called when a log event takes place.
    }
}





