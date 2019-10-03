package com.parkinglot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ParkingLotTest {

    @Test
    void givenParkingLot_whenIsAvailable_ThenShouldBeAvailable() {
        ParkingLot parkingLot = new ParkingLot(1);

        Assertions.assertTrue(parkingLot.park(new Object()));
    }

    @Test
    void givenParkingLotWithSizeZero_whenCheckIsAvailable_ThenShouldNotBeAvailable() {
        ParkingLot parkingLot = new ParkingLot(1);

        Assertions.assertTrue(parkingLot.park(new Object()));
        Assertions.assertFalse(parkingLot.park(new Object()));
    }
}
