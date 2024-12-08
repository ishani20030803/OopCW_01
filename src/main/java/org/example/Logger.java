package org.example;

import javafx.application.Platform;

import java.util.ArrayList;
import java.util.List;

public class Logger {
    private static Logger instance;
    private final List<LogListener> listeners = new ArrayList<>();

    private Logger() {
    }

    public static synchronized Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    public synchronized void log(String message) {
        System.out.println(message); // Always print to the console
        notifyListeners(message);    // Notify listeners, e.g., GUI
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
    }
}





