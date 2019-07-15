package com.thoughtworks.tdd;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;

public class SuperSmartParkingBoyTest {
    @Test
    public void should_return_the_right_parking_lot_id_when_the_super_smart_parking_boy_part_car() throws Exception {
        ParkingLot firstParkingLot = new ParkingLot(5);
        ParkingLot secondParkingLot = new ParkingLot(8);
        ParkingLot thirdParkingLot = new ParkingLot(10);
        ParkingLot[] parkingLotArray = new ParkingLot[]{firstParkingLot, secondParkingLot, thirdParkingLot};
        ParkingLots parkingLots = new ParkingLots(parkingLotArray);
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLots, "EL0317");
        for(int i = 0; i < 6; i ++) {
            Car car = new Car();
            superSmartParkingBoy.park(car);
        }

        Car overflowCar = new Car();
        ParkCarResult parkCarResult = superSmartParkingBoy.park(overflowCar);
        Ticket ticket = parkCarResult.getTicket();
        assertSame(2, ticket.getParkingLotId());
    }
}
