package com.parkinglot;

import com.parkinglot.exception.CapacityFullException;
import com.parkinglot.exception.CarNotFoundException;
import com.parkinglot.exception.VehicleAlreadyPark;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    private final int size;
    private final List<Object> vehicles;
    private List<Observer> observer;


    public ParkingLot(int size, List<Observer> observer) {
        this.size = size;
        this.observer = observer;
        this.vehicles = new ArrayList<>();
    }

    public void park(Object vehicle) throws CapacityFullException, VehicleAlreadyPark {
        if (!isSpaceAvailable()) {
            throw new CapacityFullException();
        }

        if (isAlreadyParked(vehicle)) {
            throw new VehicleAlreadyPark();
        }
        vehicles.add(vehicle);

        if (parkingLotFull()) { // TODO - name conditons

            for (Observer o : observer) { // TODO - name loops
                o.isParkingLotFull();
            }
        }
    }

    private boolean parkingLotFull() {
        return vehicles.size() == size;
    }

    private boolean isAlreadyParked(Object vehicle) {
        return vehicles.contains(vehicle);
    }

    private boolean isSpaceAvailable() {
        return vehicles.size() < size;
    }

    public Object unPark(Object vehicle) throws CarNotFoundException {
        if (vehicles.size() == 0) {
            throw new CarNotFoundException();
        }
        if (!isAlreadyParked(vehicle)) {
            throw new CarNotFoundException();
        }
        Object unParkVehicle = vehicles.remove(vehicles.indexOf(vehicle));
        if (!parkingLotFull()) {

            for (Observer o : observer) {
                o.isSpaceIsAvailable();
            }
        }
        return unParkVehicle;
    }


}
