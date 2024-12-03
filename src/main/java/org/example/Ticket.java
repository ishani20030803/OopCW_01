package org.example;

public class Ticket {
    private static int idCounter = 1;
    private final int id;

    public Ticket() {
        this.id = idCounter++;
    }

    public int getId() {
        return id;
    }
}




