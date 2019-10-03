package com.parkinglot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotTest {

    @Test
    void givenParkingLot_whenIsAvailable_ThenShouldBeAvailable() throws ParkingLotException {
        ParkingLot parkingLot = new ParkingLot(1);

        assertTrue(parkingLot.park(new Object()));
    }

    @Test
    void givenParkingLotWithSizeOne_whenCheckIsAvailable_ThenShouldNotBeAvailable() throws ParkingLotException {
        ParkingLot parkingLot = new ParkingLot(1);
        Object vehicle1=new Object();
        Object vehicle2=new Object();
        assertTrue(parkingLot.park(vehicle1));
        ParkingLotException thrown = assertThrows(ParkingLotException.class, () -> {
            parkingLot.park(vehicle2);
        });
        assertEquals("capacity is full", thrown.getMessage());
    }

    @Test
    void givenParkingLotWithCapacityTwo_whenParkSameTwoVehicles_thenShouldNotBePark() throws ParkingLotException {
        ParkingLot parkingLot = new ParkingLot(2);
        Object vehicle = new Object();

        assertTrue(parkingLot.park(vehicle));
        ParkingLotException thrown = assertThrows(ParkingLotException.class, () -> {
            parkingLot.park(vehicle);
        });
        assertEquals("vehicle already park", thrown.getMessage());
    }


}
