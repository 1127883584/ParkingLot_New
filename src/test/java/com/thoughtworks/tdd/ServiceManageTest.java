package com.thoughtworks.tdd;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;

public class ServiceManageTest {
    @Test
    public void should_return_the_right_parking_lot_id_when_the_manager_order_parking_boy_to_park_car() {
        ParkingLot firstParkingLot = new ParkingLot(5);
        ParkingLot secondParkingLot = new ParkingLot(8);
        ParkingLot thirdParkingLot = new ParkingLot(10);
        ParkingLot[] parkingLotArray = new ParkingLot[]{firstParkingLot, secondParkingLot, thirdParkingLot};
        ParkingLots parkingLots = new ParkingLots(parkingLotArray);
        ServiceManager serviceManager = new ServiceManager(parkingLots, "EL0001");

        ParkingBoy parkingBoy = new ParkingBoy(parkingLots, "EL0315");
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots, "EL0316");
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLots, "EL0317");

        serviceManager.addParkingBoysToList(parkingBoy);
        serviceManager.addParkingBoysToList(smartParkingBoy);
        serviceManager.addParkingBoysToList(superSmartParkingBoy);

        //serviceManage manage the secondParkingLot
        secondParkingLot.setServiceManager(serviceManager);

        Car car = new Car();

        ParkCarResult parkCarByOrderParkingBoyResult = serviceManager.orderParkingBoyToPark(car, parkingBoy);
        Ticket ticket = parkCarByOrderParkingBoyResult.getTicket();
        assertSame(1, ticket.getParkingLotId());
    }

    @Test
    public void should_return_the_right_car_when_the_manager_order_parking_boy_to_fetch_car() {
        ParkingLot firstParkingLot = new ParkingLot(5);
        ParkingLot secondParkingLot = new ParkingLot(8);
        ParkingLot thirdParkingLot = new ParkingLot(10);
        ParkingLot[] parkingLotArray = new ParkingLot[]{firstParkingLot, secondParkingLot, thirdParkingLot};
        ParkingLots parkingLots = new ParkingLots(parkingLotArray);
        ServiceManager serviceManager = new ServiceManager(parkingLots, "EL0001");

        ParkingBoy parkingBoy = new ParkingBoy(parkingLots, "EL0315");
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots, "EL0316");
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLots, "EL0317");

        serviceManager.addParkingBoysToList(parkingBoy);
        serviceManager.addParkingBoysToList(smartParkingBoy);
        serviceManager.addParkingBoysToList(superSmartParkingBoy);

        //serviceManage manage the secondParkingLot
        secondParkingLot.setServiceManager(serviceManager);

        Car car = new Car();

        ParkCarResult parkCarByOrderParkingBoyResult = serviceManager.orderParkingBoyToPark(car, parkingBoy);
        Ticket ticket = parkCarByOrderParkingBoyResult.getTicket();

        GetCarResult getCarByOrderParkingBoyResult = serviceManager.orderParkingBoyToFetch(ticket, parkingBoy);
        Car fetchedCar = getCarByOrderParkingBoyResult.getCar();
        assertSame(car, fetchedCar);
    }

    @Test
    public void should_return_error_message_when_the_manager_order_parking_boy_to_fetch_car_not_in_manage_parking_lot() {
        ParkingLot firstParkingLot = new ParkingLot(5);
        ParkingLot secondParkingLot = new ParkingLot(8);
        ParkingLot thirdParkingLot = new ParkingLot(10);
        ParkingLot[] parkingLotArray = new ParkingLot[]{firstParkingLot, secondParkingLot, thirdParkingLot};
        ParkingLots parkingLots = new ParkingLots(parkingLotArray);
        ServiceManager serviceManager = new ServiceManager(parkingLots, "EL0001");

        ParkingBoy parkingBoy = new ParkingBoy(parkingLots, "EL0315");
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots, "EL0316");
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLots, "EL0317");

        serviceManager.addParkingBoysToList(smartParkingBoy);

        //serviceManage manage the secondParkingLot
        secondParkingLot.setServiceManager(serviceManager);

        Car carParkByManage = new Car();
        ParkCarResult parkCarByOrderSmartParkingBoyResult = serviceManager.orderParkingBoyToPark(carParkByManage, smartParkingBoy);
        Ticket ticketParkByManage = parkCarByOrderSmartParkingBoyResult.getTicket();

        Car carParkByBoyOwn = new Car();
        ParkCarResult parkCarByBoyOwn = smartParkingBoy.park(carParkByBoyOwn);
        Ticket ticketParkByBoyOwn = parkCarByBoyOwn.getTicket();

        GetCarResult getCarByOrderSmartParkingBoyResult = serviceManager.orderParkingBoyToFetch(ticketParkByBoyOwn, smartParkingBoy);
        assertSame("This parking lot is not in your jurisdiction.", getCarByOrderSmartParkingBoyResult.getMessage());
    }

    @Test
    public void should_return_the_right_car_when_the_manager_fetch_car() {
        ParkingLot firstParkingLot = new ParkingLot(5);
        ParkingLot secondParkingLot = new ParkingLot(8);
        ParkingLot thirdParkingLot = new ParkingLot(10);
        ParkingLot[] parkingLotArray = new ParkingLot[]{firstParkingLot, secondParkingLot, thirdParkingLot};
        ParkingLots parkingLots = new ParkingLots(parkingLotArray);
        ServiceManager serviceManager = new ServiceManager(parkingLots, "EL0001");

        //serviceManage manage the secondParkingLot
        secondParkingLot.setServiceManager(serviceManager);

        Car car = new Car();

        ParkCarResult parkCarResult = serviceManager.park(car);
        Ticket ticket = parkCarResult.getTicket();

        GetCarResult getCarResult = serviceManager.fetch(ticket);
        Car fetchedCar = getCarResult.getCar();
        assertSame(car, fetchedCar);
    }

    @Test
    public void should_return_error_message_when_the_manager_fetch_car_not_in_manage_parking_lot() {
        ParkingLot firstParkingLot = new ParkingLot(5);
        ParkingLot secondParkingLot = new ParkingLot(8);
        ParkingLot thirdParkingLot = new ParkingLot(10);
        ParkingLot[] parkingLotArray = new ParkingLot[]{firstParkingLot, secondParkingLot, thirdParkingLot};
        ParkingLots parkingLots = new ParkingLots(parkingLotArray);
        ServiceManager firstServiceManager = new ServiceManager(parkingLots, "EL0001");
        ServiceManager secondServiceManager = new ServiceManager(parkingLots, "EL0002");

        //serviceManage manage the secondParkingLot
        firstParkingLot.setServiceManager(firstServiceManager);
        secondParkingLot.setServiceManager(secondServiceManager);

        Car firstCar = new Car();
        Car secondCar = new Car();

        ParkCarResult firstParkCarResult = firstServiceManager.park(firstCar);
        Ticket firstTicket = firstParkCarResult.getTicket();

        ParkCarResult secondParkCarResult = secondServiceManager.park(secondCar);
        Ticket secondTicket = secondParkCarResult.getTicket();

        //the manage fetch the car is not in his management ,it is error
        GetCarResult getCarResult = firstServiceManager.fetch(secondTicket);
        assertSame("This parking lot is not in your jurisdiction.", getCarResult.getMessage());
    }
}
