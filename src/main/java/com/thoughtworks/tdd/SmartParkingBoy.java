package com.thoughtworks.tdd;

public class SmartParkingBoy extends ParkingBoy {
    public SmartParkingBoy(ParkingLots parkingLots, String id){
        super(parkingLots, id);
    }

    public ParkCarResult park(Car car) {
        return super.getParkingLots().smartPark(car);
    }
}
