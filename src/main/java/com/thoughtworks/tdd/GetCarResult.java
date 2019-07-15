package com.thoughtworks.tdd;

public class GetCarResult {
    private Car car;
    private String message;

    public Car getCar() {
        return car;
    }

    public String getMessage() {
        return message;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
