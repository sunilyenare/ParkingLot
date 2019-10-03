package com.parkinglot;

import com.parkinglot.exception.CapacityFullException;
import com.parkinglot.exception.VehicleAlreadyPark;

public class Consumer {
    public static void park(ParkingLot parkingLotOne, ParkingLot parkingLotTwo) throws CapacityFullException, VehicleAlreadyPark {
        Object carA = new Object();
        Object carB = new Object();
        Object carC = new Object();
        Object[] Vehicles = {carA, carB, carC, carA};
        int counter = 0;
        try {
            for (int entry = 0; entry < Vehicles.length; entry++) {
                parkingLotOne.park(Vehicles[counter]);
                System.out.println(Vehicles[counter].hashCode() + " Park");
                counter++;
            }
        } catch (CapacityFullException e) {
            System.out.println(e.getMessage() + " move your vehicle " + Vehicles[counter].hashCode() + " to another parking lot");
            counter = 0;
        }
        System.out.println("--------------------***********-----------------");
        try {
            for (int entry = 0; entry < Vehicles.length; entry++) {
                parkingLotTwo.park(Vehicles[counter]);
                System.out.println(Vehicles[counter].hashCode() + " Park");
                counter++;
            }
        } catch (VehicleAlreadyPark e) {
            System.out.println(Vehicles[counter].hashCode() + " this " + e.getMessage());
        }
    }

    public static void main(String args[]) throws CapacityFullException, VehicleAlreadyPark {
        park(new ParkingLot(2), new ParkingLot(4));
    }
}
