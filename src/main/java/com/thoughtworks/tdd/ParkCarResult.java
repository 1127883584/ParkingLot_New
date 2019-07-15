package com.thoughtworks.tdd;

public class ParkCarResult {
    private Ticket ticket;
    private String message;

    public Ticket getTicket() {
        return ticket;
    }

    public String getMessage() {
        return message;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
