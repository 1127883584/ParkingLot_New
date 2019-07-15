package com.thoughtworks.tdd;

public class Ticket {
    private int parkingLotId;

    public Ticket(int parkingLotId) {
        this.parkingLotId = parkingLotId;
    }

    public void setParkingLotId(int parkingLotId) {
        this.parkingLotId = parkingLotId;
    }

    public int getParkingLotId() {
        return parkingLotId;
    }
}
