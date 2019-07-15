package com.thoughtworks.tdd;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertSame;

public class ParkingBoyTest {
    @Test
    public void should_return_car_when_park_car_to_parking_lot_then_get_it_back() {
        //given
        ParkingLot firstParkingLot = new ParkingLot(5);
        ParkingLot[] parkingLotArray = new ParkingLot[]{firstParkingLot};
        Car car = new Car();
        ParkingLots parkingLots = new ParkingLots(parkingLotArray);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots, "EL0315");

        //when
        Ticket ticket = parkingBoy.park(car).getTicket();
        Car fetchedCar = parkingBoy.fetch(ticket).getCar();

        //then
        assertSame(car, fetchedCar);
    }

    @Test
    public void should_multiple_cars_when_use_correspond_ticket() {
        //given
        Car firstCar = new Car();
        Car secondCar = new Car();
        ParkingLot firstParkingLot = new ParkingLot(5);
        ParkingLot[] parkingLotArray = new ParkingLot[]{firstParkingLot};
        ParkingLots parkingLots = new ParkingLots(parkingLotArray);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots, "EL0315");

        //when
        Ticket firstTicket = parkingBoy.park(firstCar).getTicket();
        Ticket secondTicket = parkingBoy.park(secondCar).getTicket();
        Car fetchedFirstCar = parkingBoy.fetch(firstTicket).getCar();
        Car fetchedSecondCar = parkingBoy.fetch(secondTicket).getCar();

        //then
        assertSame(firstCar, fetchedFirstCar);
        assertSame(secondCar, fetchedSecondCar);
    }
//
    @Test
    public void should_not_fetch_car_when_ticket_is_wrong() {
        ParkingLot firstParkingLot = new ParkingLot(5);
        ParkingLot[] parkingLotArray = new ParkingLot[]{firstParkingLot};
        Car car = new Car();
        ParkingLots parkingLots = new ParkingLots(parkingLotArray);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots, "EL0315");
        Ticket wrongTicket = new Ticket(0);

        //when
        parkingBoy.park(car);

        assertSame(null, parkingBoy.fetch(wrongTicket).getCar());
    }

    @Test
    public void should_not_fetch_when_ticket_has_been_used() {
        //given
        ParkingLot firstParkingLot = new ParkingLot(5);
        ParkingLot[] parkingLotArray = new ParkingLot[]{firstParkingLot};
        Car car = new Car();
        ParkingLots parkingLots = new ParkingLots(parkingLotArray);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots, "EL0315");

        //when
        Ticket ticket = parkingBoy.park(car).getTicket();
        parkingBoy.fetch(ticket);

        assertSame(null, parkingBoy.fetch(ticket).getCar());
    }

    @Test
    public void should_not_park_car_when_parking_lot_capacity_is_full() {
        ParkingLot firstParkingLot = new ParkingLot(5);
        ParkingLot[] parkingLotArray = new ParkingLot[]{firstParkingLot};
        ParkingLots parkingLots = new ParkingLots(parkingLotArray);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots, "EL0315");
        for(int i = 0; i < 10; i ++) {
            Car car = new Car();
            parkingBoy.park(car);
        }
        Car overflowCar = new Car();

        assertSame(null, parkingBoy.park(overflowCar).getTicket());
    }

    @Test
    public void should_not_park_car_when_park_a_parked_car() {
        //given
        ParkingLot firstParkingLot = new ParkingLot(5);
        ParkingLot[] parkingLotArray = new ParkingLot[]{firstParkingLot};
        Car car = new Car();
        ParkingLots parkingLots = new ParkingLots(parkingLotArray);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots, "EL0315");

        //when
        parkingBoy.park(car);

        assertSame(null, parkingBoy.park(car).getTicket());
    }

    @Test
    public void should_not_park_car_when_park_a_null_car(){
        ParkingLot firstParkingLot = new ParkingLot(5);
        ParkingLot[] parkingLotArray = new ParkingLot[]{firstParkingLot};
        ParkingLots parkingLots = new ParkingLots(parkingLotArray);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots, "EL0315");

        assertSame(null, parkingBoy.park(null).getTicket());
    }

    @Test
    public void should_return_error_message_when_ticket_is_wrong() {
        ParkingLot firstParkingLot = new ParkingLot(5);
        ParkingLot[] parkingLotArray = new ParkingLot[]{firstParkingLot};
        Car car = new Car();
        ParkingLots parkingLots = new ParkingLots(parkingLotArray);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots, "EL0315");

        Ticket ticket = new Ticket(0);
        parkingBoy.park(car);

        assertThat(parkingBoy.fetch(ticket).getMessage(), is("Unrecognized parking ticket."));
    }

    @Test
    public void should_return_error_message_when_ticket_has_been_used() {
        ParkingLot firstParkingLot = new ParkingLot(5);
        ParkingLot[] parkingLotArray = new ParkingLot[]{firstParkingLot};
        Car car = new Car();
        ParkingLots parkingLots = new ParkingLots(parkingLotArray);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots, "EL0315");

        Ticket ticket = parkingBoy.park(car).getTicket();
        parkingBoy.fetch(ticket);

        assertThat(parkingBoy.fetch(ticket).getMessage(), is("Unrecognized parking ticket."));
    }

    @Test
    public void should_return_error_message_when_not_provide_ticket() {
        ParkingLot firstParkingLot = new ParkingLot(5);
        ParkingLot[] parkingLotArray = new ParkingLot[]{firstParkingLot};
        Car car = new Car();
        ParkingLots parkingLots = new ParkingLots(parkingLotArray);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots, "EL0315");

        parkingBoy.park(car);

        assertThat(parkingBoy.fetch(null).getMessage(), is("Please provide your parking ticket."));
    }

    @Test
    public void should_return_error_message_when_park_car_into_parking_lot_without_position() {
        ParkingLot firstParkingLot = new ParkingLot(5);
        ParkingLot[] parkingLotArray = new ParkingLot[]{firstParkingLot};
        ParkingLots parkingLots = new ParkingLots(parkingLotArray);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots, "EL0315");
        for(int i = 0; i < 5; i ++) {
            Car car = new Car();
            parkingBoy.park(car);
        }
        Car overflowCar = new Car();

        assertThat(parkingBoy.park(overflowCar).getMessage(), is("Not enough position."));
    }

    @Test
    public void should_return_the_second_parking_lot_when_the_first_parking_lot_is_full() {
        ParkingLot firstParkingLot = new ParkingLot(5);
        ParkingLot secondParkingLot = new ParkingLot(8);
        ParkingLot thirdParkingLot = new ParkingLot(10);
        ParkingLot[] parkingLotArray = new ParkingLot[]{firstParkingLot, secondParkingLot, thirdParkingLot};
        ParkingLots parkingLots = new ParkingLots(parkingLotArray);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots, "EL0315");

        for(int i = 0; i < 10; i ++) {
            Car car = new Car();
            parkingBoy.park(car);
        }

        Car overflowCar = new Car();
        Ticket ticket = parkingBoy.park(overflowCar).getTicket();
        assertSame(1, ticket.getParkingLotId());
    }
}
