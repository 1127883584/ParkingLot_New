package com.thoughtworks.tdd;

import com.thoughtworks.exception.WrongTicketException;

public class ParkingBoy {
    private ParkingLots parkingLots;
    private String id;

    public ParkingBoy(ParkingLots parkingLots, String id){
        this.parkingLots = parkingLots;
        this.id = id;
    }

    public ParkingLots getParkingLots() {
        return parkingLots;
    }

    public String getId() {
        return id;
    }

    public ParkCarResult park(Car car) {
        ParkCarResult parkCarResult = parkingLots.park(car);
        return parkCarResult;
    }

    public ParkCarResult park(Car car, ServiceManager serviceManager) {
        ParkCarResult parkCarResult = parkingLots.park(car, serviceManager);
        return parkCarResult;
    }

    public GetCarResult fetch(Ticket ticket) throws WrongTicketException {
        GetCarResult getCarResult = parkingLots.getCar(ticket);
        return getCarResult;
    }

    public GetCarResult fetch(Ticket ticket, ServiceManager serviceManager){
        GetCarResult getCarResult = parkingLots.getCar(ticket, serviceManager);
        return getCarResult;
    }
}
