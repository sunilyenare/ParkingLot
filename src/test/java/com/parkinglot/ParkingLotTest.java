package com.parkinglot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ParkingLotTest {

    @Test
    void  givenParkingLot_whenIsAvailable_ThenShouldBeAvailable(){
        ParkingLot parkingLot=new ParkingLot(10);

        Assertions.assertTrue(parkingLot.park(new Object()));

    }

}
