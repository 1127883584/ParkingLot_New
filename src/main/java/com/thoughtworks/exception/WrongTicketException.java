package com.thoughtworks.exception;

public class WrongTicketException extends Exception{
    public WrongTicketException(String message) {
        super(message);
    }
}
