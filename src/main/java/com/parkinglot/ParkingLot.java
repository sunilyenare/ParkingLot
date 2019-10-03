package com.parkinglot;

import java.io.InvalidObjectException;
import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    private final int size;
    private final List<Object> vehicles;

    public ParkingLot(int size) {
        this.size = size;
        this.vehicles = new ArrayList<>();
    }

    public boolean park(Object vehicle) throws ParkingLotException {
        if (isSpaceAvailable()) {
            if (isAlreadyParked(vehicle)) {
                throw new ParkingLotException("vehicle already park");
            }
            vehicles.add(vehicle);
            return true;
        }
       throw new ParkingLotException("capacity is full");
    }

    private boolean isAlreadyParked(Object vehicle) {
        return vehicles.contains(vehicle);
    }

    private boolean isSpaceAvailable() {
        return vehicles.size() < size;
    }

}
