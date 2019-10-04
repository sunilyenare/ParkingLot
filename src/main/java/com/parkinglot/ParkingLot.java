package com.parkinglot;

import com.parkinglot.exception.CapacityFullException;
import com.parkinglot.exception.CarNotFoundException;
import com.parkinglot.exception.VehicleAlreadyPark;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot implements Notification {
    private final int size;
    private final List<Object> vehicles;

    public ParkingLot(int size) {
        this.size = size;
        this.vehicles = new ArrayList<>();
    }


    public boolean park(Object vehicle) throws CapacityFullException, VehicleAlreadyPark {
        if (!isSpaceAvailable()) {
            throw new CapacityFullException("capacity is full");
        }

        if (isAlreadyParked(vehicle)) {
            throw new VehicleAlreadyPark("vehicle already park");
        }
        vehicles.add(vehicle);
        return true;
    }

    private boolean isAlreadyParked(Object vehicle) {
        return vehicles.contains(vehicle);
    }

    private boolean isSpaceAvailable() {
        return vehicles.size() < size;
    }

    public Object unPark(Object vehicle) throws CarNotFoundException {
        if (vehicles.size() == 0) {
            throw new CarNotFoundException("VEHICLE NO LONGER AVAILABLE IN PARKING LOT");
        }
        if (!isAlreadyParked(vehicle)) {
            throw new CarNotFoundException("VEHICLE NO LONGER AVAILABLE IN PARKING LOT");
        }
        return vehicles.remove(vehicles.indexOf(vehicle));
    }


    @Override
    public String update(String message) {
        return message;
    }
}
