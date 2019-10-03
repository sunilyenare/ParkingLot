package com.parkinglot;

public class Consumer {
    public static void park(ParkingLot parkingLotOne, ParkingLot parkingLotTwo) throws ParkingLotException {
        Object carA = new Object();
        Object carB = new Object();
        Object carC = new Object();
        try {
            try {
                parkingLotOne.park(carA);
                parkingLotOne.park(carA);
            } catch (ParkingLotException e) {
                System.out.println(e);
            }
            parkingLotOne.park(carC);
        } catch (ParkingLotException e) {

            System.out.println(e);
        }

//        try {
//            parkingLotTwo.park(carA);
//            parkingLotTwo.park(carA);
//        } catch (ParkingLotException e) {
//            System.out.println(e);
//        }


    }

    public static void main(String args[]) throws ParkingLotException {
        park(new ParkingLot(2), new ParkingLot(3));
    }
}
