package com.thoughtworks.tdd;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;

public class SmartParkingBoyTest {
    @Test
    public void should_return_the_right_parking_lot_id_when_the_smart_parking_boy_part_car() {
        ParkingLot firstParkingLot = new ParkingLot(5);
        ParkingLot secondParkingLot = new ParkingLot(8);
        ParkingLot thirdParkingLot = new ParkingLot(10);
        ParkingLot[] parkingLotArray = new ParkingLot[]{firstParkingLot, secondParkingLot, thirdParkingLot};        ParkingLots parkingLots = new ParkingLots(parkingLotArray);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots, "EL0316");
        for(int i = 0; i < 6; i ++) {
            Car car = new Car();
            smartParkingBoy.park(car);
        }

        Car overflowCar = new Car();
        Ticket ticket = smartParkingBoy.park(overflowCar).getTicket();
        assertSame(1, ticket.getParkingLotId());
    }
}
