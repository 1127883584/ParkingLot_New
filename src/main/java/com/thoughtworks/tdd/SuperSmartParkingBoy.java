package com.thoughtworks.tdd;

public class SuperSmartParkingBoy extends SmartParkingBoy {
    public SuperSmartParkingBoy(ParkingLots parkingLots, String id){
        super(parkingLots, id);
    }

    public ParkCarResult park(Car car) {
        return super.getParkingLots().superSmartPark(car);
    }
}
