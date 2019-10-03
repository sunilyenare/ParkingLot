package com.parkinglot;


public class ParkingLot {
    private final int size;
    private int count = 0;

    public ParkingLot(int size) {
        this.size = 1;
    }

    public boolean park(Object vehicle) {

        if (count < size) {
            count ++;
            return true;
        }
        return false;
    }

}
