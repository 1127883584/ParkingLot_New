package com.thoughtworks.tdd;

import com.thoughtworks.exception.WrongTicketException;

import java.util.HashMap;

public class ServiceManager extends ParkingBoy{
    private HashMap<String, ParkingBoy> managementList;

    public ServiceManager(ParkingLots parkingLots, String id){
        super(parkingLots, id);
        managementList = new HashMap<>();
    }

    public ParkCarResult park(Car car) throws Exception {
        ParkCarResult parkCarResult = super.getParkingLots().park(car, this);
        return parkCarResult;
    }

    public GetCarResult fetch(Ticket ticket) throws Exception {
        GetCarResult getCarResult = super.getParkingLots().getCar(ticket, this);
        return getCarResult;
    }

    public void addParkingBoysToList(ParkingBoy parkingBoy) {
        managementList.put(parkingBoy.getId(), parkingBoy);
    }

    public ParkCarResult orderParkingBoyToPark(Car car, ParkingBoy parkingBoy) throws Exception {
        ParkingBoy parkingBoyTemp = managementList.get(parkingBoy.getId());
        ParkCarResult parkCarResult = new ParkCarResult();
        if (parkingBoyTemp == null) {
            parkCarResult.setMessage("This parking boy is not in your management list.");
            parkCarResult.setTicket(null);
        } else {
            parkCarResult = parkingBoy.park(car, this);
        }
        return parkCarResult;
    }

    public GetCarResult orderParkingBoyToFetch(Ticket ticket, ParkingBoy parkingBoy) throws Exception {
        ParkingBoy parkingBoyTemp = managementList.get(parkingBoy.getId());
        GetCarResult getCarResult = new GetCarResult();
        if (parkingBoyTemp == null) {
            getCarResult.setMessage("This parking boy is not in your management list.");
            getCarResult.setCar(null);
        } else {
            getCarResult = parkingBoy.fetch(ticket, this);
        }
        return getCarResult;
    }
}
