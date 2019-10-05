package com.parkinglot;

import com.parkinglot.exception.CapacityFullException;
import com.parkinglot.exception.CarNotFoundException;
import com.parkinglot.exception.VehicleAlreadyPark;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class DummyParkingLotOwner implements Observer {
    public int isParkingLotFullNotify = 0;
    public int isSpaceIsAvailableNotify = 0;

    @Override
    public void isParkingLotFull() {
        isParkingLotFullNotify++;
    }

    @Override
    public void isSpaceIsAvailable() {
        isSpaceIsAvailableNotify++;
    }
}

class DummyParkingLotSecurityPerson implements Observer {
    public int isParkingLotFullNotify = 0;
    public int isSpaceIsAvailableNotify = 0;

    @Override
    public void isParkingLotFull() {
        isParkingLotFullNotify++;
    }

    @Override
    public void isSpaceIsAvailable() {
        isSpaceIsAvailableNotify++;
    }
}

public class ParkingLotTest {

    @Test
    void givenParkingLot_whenIsAvailable_ThenShouldBeAvailable() throws Exception {
        ParkingLot parkingLot = new ParkingLot(1);

        assertDoesNotThrow(() -> parkingLot.park(new Object()));
    }

    @Test
    void givenParkingLotWithSizeOne_whenCheckIsAvailable_ThenShouldNotBeAvailable() throws Exception {
        ParkingLot parkingLot = new ParkingLot(1);
        Object vehicle1 = new Object();
        Object vehicle2 = new Object();
        parkingLot.park(vehicle1);
        assertThrows(CapacityFullException.class, () -> {
            parkingLot.park(vehicle2);
        });

    }

    @Test
    void givenParkingLotWithCapacityTwo_whenParkSameTwoVehicles_thenShouldNotBePark() throws Exception {
        ParkingLot parkingLot = new ParkingLot(2);
        Object vehicle = new Object();

        parkingLot.park(vehicle);
        assertThrows(VehicleAlreadyPark.class, () -> {
            parkingLot.park(vehicle);
        });

    }

    @Test
    void givenParkingLotWithCapacityOne_whenUnParkVehicle_thenShouldReturnVehicle() throws VehicleAlreadyPark, CapacityFullException, CarNotFoundException {
        ParkingLot parkingLot = new ParkingLot(1);
        Object vehicle = new Object();

        parkingLot.park(vehicle);
        assertEquals(vehicle, parkingLot.unPark(vehicle));
    }

    @Test
    void givenParkingLotWithCapacityTwo_whenUnParkOneVehicle_thenShouldReturnVehicle() throws VehicleAlreadyPark, CapacityFullException, CarNotFoundException {
        ParkingLot parkingLot = new ParkingLot(2);
        Object vehicleOne = new Object();
        Object vehicleTwo = new Object();
        parkingLot.park(vehicleOne);
        parkingLot.park(vehicleTwo);

        assertEquals(vehicleTwo, parkingLot.unPark(vehicleTwo));
    }

    @Test
    void givenParkingLotWithCapacityTwo_whenUnParkNotAvailableVehicle_thenShouldThrowException() throws VehicleAlreadyPark, CapacityFullException, CarNotFoundException {
        ParkingLot parkingLot = new ParkingLot(2);
        Object vehicleOne = new Object();
        Object vehicleTwo = new Object();
        parkingLot.park(vehicleOne);
        parkingLot.park(vehicleTwo);

        assertEquals(vehicleOne, parkingLot.unPark(vehicleOne));
        assertEquals(vehicleTwo, parkingLot.unPark(vehicleTwo));

        assertThrows(CarNotFoundException.class, () -> {
            parkingLot.unPark(vehicleTwo);
        });

    }

    @Test
    void givenParkingLotWithCapacityTwo_whenUnParkThreeVehicle_thenShouldThrowException() throws VehicleAlreadyPark, CapacityFullException, CarNotFoundException {
        ParkingLot parkingLot = new ParkingLot(2);
        Object vehicleOne = new Object();
        Object vehicleTwo = new Object();
        parkingLot.park(vehicleOne);
        parkingLot.park(vehicleTwo);

        assertEquals(vehicleOne, parkingLot.unPark(vehicleOne));
        assertEquals(vehicleTwo, parkingLot.unPark(vehicleTwo));


        assertThrows(CarNotFoundException.class, () -> {
            parkingLot.unPark(vehicleTwo);
        });

    }

    @Test
    void givenParkingLotWithCapacityTwo_WhenReachToCapacity_ThenNotifyToOwner() throws VehicleAlreadyPark, CapacityFullException {
        DummyParkingLotOwner owner = new DummyParkingLotOwner();
        ParkingLot parkingLot = new ParkingLot(2, owner);
        Object vehicleOne = new Object();
        Object vehicleTwo = new Object();
        parkingLot.park(vehicleOne);
        parkingLot.park(vehicleTwo);


        assertEquals(1, owner.isParkingLotFullNotify);
    }

    @Test
    void givenParkingLotWithCapacityTwo_WhenUnPark_ThenNotifyToOwner() throws VehicleAlreadyPark, CapacityFullException, CarNotFoundException {
        DummyParkingLotOwner owner = new DummyParkingLotOwner();
        ParkingLot parkingLot = new ParkingLot(2, owner);
        Object vehicleOne = new Object();
        Object vehicleTwo = new Object();
        parkingLot.park(vehicleOne);
        parkingLot.park(vehicleTwo);

        assertEquals(1, owner.isParkingLotFullNotify);

        parkingLot.unPark(vehicleTwo);

        assertEquals(1, owner.isSpaceIsAvailableNotify);

    }

    @Test
    void givenParkingLotWithCapacityTwo_WhenReachToCapacity_ThenNotifyToSecurityPerson() throws VehicleAlreadyPark, CapacityFullException {
        DummyParkingLotSecurityPerson parkingLotSecurityPerson = new DummyParkingLotSecurityPerson();
        ParkingLot parkingLot = new ParkingLot(2, parkingLotSecurityPerson);
        Object vehicleOne = new Object();
        Object vehicleTwo = new Object();
        parkingLot.park(vehicleOne);
        parkingLot.park(vehicleTwo);


        assertEquals(1, parkingLotSecurityPerson.isParkingLotFullNotify);
    }

    @Test
    void givenParkingLotWithCapacityTwo_WhenUnPark_ThenNotifyToSecurityPerson() throws VehicleAlreadyPark, CapacityFullException, CarNotFoundException {
        DummyParkingLotSecurityPerson parkingLotSecurityPerson = new DummyParkingLotSecurityPerson();
        ParkingLot parkingLot = new ParkingLot(2, parkingLotSecurityPerson);
        Object vehicleOne = new Object();
        Object vehicleTwo = new Object();
        parkingLot.park(vehicleOne);
        parkingLot.park(vehicleTwo);

        assertEquals(1, parkingLotSecurityPerson.isParkingLotFullNotify);

        parkingLot.unPark(vehicleTwo);

        assertEquals(1, parkingLotSecurityPerson.isSpaceIsAvailableNotify);

    }
    @Test
    void givenParkingLotWithCapacity_WhenUnPark_ThenNotifyToSecurityPersonAndOwner() throws VehicleAlreadyPark, CapacityFullException, CarNotFoundException {

        DummyParkingLotSecurityPerson parkingLotSecurityPerson = new DummyParkingLotSecurityPerson();

        DummyParkingLotOwner owner = new DummyParkingLotOwner();

        ParkingLot parkingLot = new ParkingLot(2, parkingLotSecurityPerson);
        new ParkingLot(2, owner);

        Object vehicleOne = new Object();
        Object vehicleTwo = new Object();
        parkingLot.park(vehicleOne);
        parkingLot.park(vehicleTwo);

        assertEquals(1, parkingLotSecurityPerson.isParkingLotFullNotify);
        assertEquals(1, owner.isParkingLotFullNotify);
        parkingLot.unPark(vehicleTwo);

        assertEquals(1, parkingLotSecurityPerson.isSpaceIsAvailableNotify);
        assertEquals(1, owner.isSpaceIsAvailableNotify);

    }
}
