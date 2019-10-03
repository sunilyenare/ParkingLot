package com.parkinglot;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    private final int size;
    private final List<Object> vehicles;

    public ParkingLot(int size) {
        this.size = size;
        this.vehicles = new ArrayList<>();
    }

    public boolean park(Object vehicle) {
        if (isSpaceAvailable()) {
            if (isAlreadyParked(vehicle)) {
                return false;
            }
            vehicles.add(vehicle);
            return true;
        }
        return false;
    }

    private boolean isAlreadyParked(Object vehicle) {
        return vehicles.contains(vehicle);
    }

    private boolean isSpaceAvailable() {
        return vehicles.size() < size;
    }

}
