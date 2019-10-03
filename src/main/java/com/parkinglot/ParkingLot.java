package com.parkinglot;

import com.parkinglot.exception.CapacityFullException;
import com.parkinglot.exception.VehicleAlreadyPark;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    private final int size;
    private final List<Object> vehicles;

    public ParkingLot(int size) {
        this.size = size;
        this.vehicles = new ArrayList<>();
    }

    public boolean park(Object vehicle) throws CapacityFullException, VehicleAlreadyPark {
        if (isSpaceAvailable()) {
            if (isAlreadyParked(vehicle)) {
                throw new VehicleAlreadyPark("vehicle already park");
            }
            vehicles.add(vehicle);
            return true;
        }
       throw new CapacityFullException("capacity is full");
    }

    private boolean isAlreadyParked(Object vehicle) {
        return vehicles.contains(vehicle);
    }

    private boolean isSpaceAvailable() {
        return vehicles.size() < size;
    }
}
