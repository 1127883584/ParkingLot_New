package com.thoughtworks.tdd;

import java.lang.reflect.Array;
import com.thoughtworks.exception.*;
import java.util.Arrays;
import java.util.HashMap;

public class ParkingLots {
    private HashMap<Integer, ParkingLot> parkinglots;
    private int capacity;
    private int parkingLotCapacity = 10;

    public ParkingLots(ParkingLot[] parkingLotArray) {
        this.parkinglots = new HashMap<>();
        this.capacity = parkingLotArray.length;
        generateEveryParkingLot(parkingLotArray);
    }

    public ParkCarResult park(Car car, ServiceManager serviceManager){
        ParkCarResult parkCarResult = new ParkCarResult();
        for (Integer key : parkinglots.keySet()) {
            ParkingLot parkingLot = parkinglots.get(key);
            if (parkingLot.getServiceManager() == serviceManager) {
                if (parkingLot.getParkingCarTicket().size() == parkingLot.getCapacity()) {
                    parkCarResult.setTicket(null);
                    parkCarResult.setMessage("Not enough position.");
                } else {
                    if (car == null || parkingLot.getParkingCarTicket().containsValue(car)) {
                        parkCarResult.setTicket(null);
                        parkCarResult.setMessage("Car not park a parked car or park a null car.");
                    } else {
                        Ticket ticket = new Ticket(key);
                        parkingLot.getParkingCarTicket().put(ticket, car);
                        parkCarResult.setTicket(ticket);
                        parkCarResult.setMessage("Success park car.");
                    }
                }
                break;
            }
        }
        return parkCarResult;
    }

    public ParkCarResult park(Car car){
        ParkCarResult parkCarResult = new ParkCarResult();
        for (Integer key : parkinglots.keySet()) {
            ParkingLot parkingLot = parkinglots.get(key);
            if (parkingLot.getParkingCarTicket().size() == parkingLot.getCapacity()) {
                parkCarResult.setTicket(null);
                parkCarResult.setMessage("Not enough position.");
            } else {
                if (car == null || parkingLot.getParkingCarTicket().containsValue(car)) {
                    parkCarResult.setTicket(null);
                    parkCarResult.setMessage("Car not park a parked car or park a null car.");
                } else {
                    Ticket ticket = new Ticket(key);
                    parkingLot.getParkingCarTicket().put(ticket, car);
                    parkCarResult.setTicket(ticket);
                    parkCarResult.setMessage("Success park car.");
                    break;
                }
            }
        }
        return parkCarResult;
    }

    public GetCarResult getCar(Ticket ticket, ServiceManager serviceManager) {
        GetCarResult getCarResult = new GetCarResult();
        if (ticket == null) {
            getCarResult.setCar(null);
            getCarResult.setMessage("Please provide your parking ticket.");
        } else {
            ParkingLot parkingLot = parkinglots.get(ticket.getParkingLotId());
            if (parkingLot.getServiceManager() == serviceManager) {
                Car car = parkingLot.getParkingCarTicket().get(ticket);
                getCarResult.setCar(car);
                if (car == null) {
                    getCarResult.setMessage("Unrecognized parking ticket.");
                } else {
                    parkingLot.getParkingCarTicket().remove(ticket);
                    getCarResult.setMessage("Success fetch the car.");
                }
            } else {
                getCarResult.setCar(null);
                getCarResult.setMessage("This parking lot is not in your jurisdiction.");
            }
        }
        return getCarResult;
    }

    public GetCarResult getCar(Ticket ticket) throws WrongTicketException {
        GetCarResult getCarResult = new GetCarResult();
        if (ticket == null) {
            getCarResult.setCar(null);
            getCarResult.setMessage("Please provide your parking ticket.");
        } else {
            ParkingLot parkingLot = parkinglots.get(ticket.getParkingLotId());
            Car car = parkingLot.getParkingCarTicket().get(ticket);
            getCarResult.setCar(car);
            if (car == null) {
//                getCarResult.setMessage("Unrecognized parking ticket.");
                throw new WrongTicketException("Unrecognized parking ticket.");
            } else {
                parkingLot.getParkingCarTicket().remove(ticket);
                getCarResult.setMessage("Success fetch the car.");
            }
        }

        return getCarResult;
    }

    public ParkCarResult smartPark(Car car){
        ParkCarResult parkCarResult = new ParkCarResult();
        int[] remainingCapacity = new int[capacity];
        Arrays.fill(remainingCapacity, parkingLotCapacity);
        int i = 0;
        for (Integer key : parkinglots.keySet()) {
            ParkingLot parkingLot = parkinglots.get(key);
            remainingCapacity[i] = parkingLot.getCapacity() - parkingLot.getParkingCarTicket().size();
            i ++;
            if (parkingLot.getParkingCarTicket().size() == parkingLot.getCapacity()) {
                parkCarResult.setTicket(null);
                parkCarResult.setMessage("Not enough position.");
            } else {
                if (car == null || parkingLot.getParkingCarTicket().containsValue(car)) {
                    parkCarResult.setTicket(null);
                    parkCarResult.setMessage("Car not park a parked car or park a null car.");
                    return parkCarResult;
                }
            }
        }
        int maxRemainingCapacityIndex = getMaxIndex(remainingCapacity);
        if (remainingCapacity[maxRemainingCapacityIndex] != 0) {
            Ticket ticket = new Ticket(maxRemainingCapacityIndex);
            parkinglots.get(maxRemainingCapacityIndex).getParkingCarTicket().put(ticket, car);
            parkCarResult.setTicket(ticket);
            parkCarResult.setMessage("Success park car.");
        }
        return parkCarResult;
    }

    public ParkCarResult superSmartPark(Car car){
        ParkCarResult parkCarResult = new ParkCarResult();
        double[] availablePositionRate = new double[capacity];
        Arrays.fill(availablePositionRate, 1);
        int i = 0;
        for (Integer key : parkinglots.keySet()) {
            ParkingLot parkingLot = parkinglots.get(key);
            availablePositionRate[i] = ((double)(parkingLot.getCapacity() - parkingLot.getParkingCarTicket().size()))/parkingLot.getCapacity();
            i ++;
            if (parkingLot.getParkingCarTicket().size() == parkingLot.getCapacity()) {
                parkCarResult.setTicket(null);
                parkCarResult.setMessage("Not enough position.");
            } else {
                if (car == null || parkingLot.getParkingCarTicket().containsValue(car)) {
                    parkCarResult.setTicket(null);
                    parkCarResult.setMessage("Car not park a parked car or park a null car.");
                    return parkCarResult;
                }
            }
        }
        int maxRemainingCapacityIndex = getMaxIndex(availablePositionRate);
        if (availablePositionRate[maxRemainingCapacityIndex] > 0) {
            Ticket ticket = new Ticket(maxRemainingCapacityIndex);
            parkinglots.get(maxRemainingCapacityIndex).getParkingCarTicket().put(ticket, car);
            parkCarResult.setTicket(ticket);
            parkCarResult.setMessage("Success park car.");
        }
        return parkCarResult;
    }

    public void generateEveryParkingLot(ParkingLot[] parkingLotArray) {
        for (int i = 0; i < parkingLotArray.length; i ++) {
            parkinglots.put(i, parkingLotArray[i]);
        }
    }

    public int getMaxIndex(int[] arr) {
        int max = arr[0];
        int maxIndex = 0;
        for(int i = 0; i < arr.length; i ++) {
            if(arr[i] > max) {
                max = arr[i];
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    public int getMaxIndex(double[] arr) {
        double max = arr[0];
        int maxIndex = 0;
        for(int i = 0; i < arr.length; i ++) {
            if(arr[i] > max) {
                max = arr[i];
                maxIndex = i;
            }
        }
        return maxIndex;
    }

}
