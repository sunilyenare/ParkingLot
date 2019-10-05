package com.parkinglot;

import com.parkinglot.exception.CapacityFullException;
import com.parkinglot.exception.CarNotFoundException;
import com.parkinglot.exception.VehicleAlreadyPark;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotTest {

    @Test
    void givenParkingLot_whenIsAvailable_ThenShouldBeAvailable() throws Exception {
        ParkingLot parkingLot = new ParkingLot(1, Arrays.asList());

        assertDoesNotThrow(() -> parkingLot.park(new Object()));
    }

    @Test
    void givenParkingLotWithSizeOne_whenCheckIsAvailable_ThenShouldNotBeAvailable() throws Exception {
        ParkingLot parkingLot = new ParkingLot(1, Arrays.asList());
        Object vehicle1 = new Object();
        Object vehicle2 = new Object();
        parkingLot.park(vehicle1);
        assertThrows(CapacityFullException.class, () -> {
            parkingLot.park(vehicle2);
        });

    }

    @Test
    void givenParkingLotWithCapacityTwo_whenParkSameTwoVehicles_thenShouldNotBePark() throws Exception {
        ParkingLot parkingLot = new ParkingLot(2, Arrays.asList());
        Object vehicle = new Object();

        parkingLot.park(vehicle);
        assertThrows(VehicleAlreadyPark.class, () -> {
            parkingLot.park(vehicle);
        });

    }

    @Test
    void givenParkingLotWithCapacityOne_whenUnParkVehicle_thenShouldReturnVehicle() throws Exception {
        ParkingLot parkingLot = new ParkingLot(1, Arrays.asList());
        Object vehicle = new Object();

        parkingLot.park(vehicle);
        assertEquals(vehicle, parkingLot.unPark(vehicle));
    }

    @Test
    void givenParkingLotWithCapacityTwo_whenUnParkOneVehicle_thenShouldReturnVehicle() throws Exception {
        ParkingLot parkingLot = new ParkingLot(2, Arrays.asList());
        Object vehicleOne = new Object();
        Object vehicleTwo = new Object();
        parkingLot.park(vehicleOne);
        parkingLot.park(vehicleTwo);

        assertEquals(vehicleTwo, parkingLot.unPark(vehicleTwo));
    }

    @Test
    void givenParkingLotWithCapacityTwo_whenUnParkNotAvailableVehicle_thenShouldThrowException() throws Exception {
        ParkingLot parkingLot = new ParkingLot(2, Arrays.asList());
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
    void givenParkingLotWithCapacityTwo_whenUnParkThreeVehicle_thenShouldThrowException() throws Exception {

        ParkingLot parkingLot = new ParkingLot(2, Arrays.asList());
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
    void givenParkingLotWithCapacityTwo_WhenReachToCapacity_ThenNotifyToOwner() throws Exception {
        DummyParkingLotOwner owner = new DummyParkingLotOwner();


        ParkingLot parkingLot = new ParkingLot(2, Arrays.asList(owner));
        Object vehicleOne = new Object();
        Object vehicleTwo = new Object();
        parkingLot.park(vehicleOne);
        parkingLot.park(vehicleTwo);


        assertEquals(1, owner.isParkingLotFullNotify);
    }

    @Test
    void givenParkingLotWithCapacityTwo_WhenUnPark_ThenNotifyToOwner() throws Exception {
        DummyParkingLotOwner owner = new DummyParkingLotOwner();

        ParkingLot parkingLot = new ParkingLot(2, Arrays.asList(owner));
        Object vehicleOne = new Object();
        Object vehicleTwo = new Object();
        parkingLot.park(vehicleOne);
        parkingLot.park(vehicleTwo);

        assertEquals(1, owner.isParkingLotFullNotify);

        parkingLot.unPark(vehicleTwo);

        assertEquals(1, owner.isSpaceIsAvailableNotify);

    }

    @Test
    void givenParkingLotWithCapacityTwo_WhenReachToCapacity_ThenNotifyToSecurityPerson() throws Exception {
        DummyParkingLotSecurityPerson parkingLotSecurityPerson = new DummyParkingLotSecurityPerson();

        ParkingLot parkingLot = new ParkingLot(2, Arrays.asList(parkingLotSecurityPerson));
        Object vehicleOne = new Object();
        Object vehicleTwo = new Object();
        parkingLot.park(vehicleOne);
        parkingLot.park(vehicleTwo);


        assertEquals(1, parkingLotSecurityPerson.isParkingLotFullNotify);
    }

    @Test
    void givenParkingLotWithCapacityTwo_WhenUnPark_ThenNotifyToSecurityPerson() throws Exception {
        DummyParkingLotSecurityPerson parkingLotSecurityPerson = new DummyParkingLotSecurityPerson();

        ParkingLot parkingLot = new ParkingLot(2, Arrays.asList(parkingLotSecurityPerson));
        Object vehicleOne = new Object();
        Object vehicleTwo = new Object();
        parkingLot.park(vehicleOne);
        parkingLot.park(vehicleTwo);

        assertEquals(1, parkingLotSecurityPerson.isParkingLotFullNotify);

        parkingLot.unPark(vehicleTwo);

        assertEquals(1, parkingLotSecurityPerson.isSpaceIsAvailableNotify);

    }

    @Test
    void givenParkingLotWithCapacityTwo_WhenReachToCapacity_ThenNotifyToSecurityPersonAndOwner() throws Exception {

        DummyParkingLotSecurityPerson parkingLotSecurityPerson = new DummyParkingLotSecurityPerson();
        DummyParkingLotOwner owner = new DummyParkingLotOwner();

        ParkingLot parkingLot = new ParkingLot(2, Arrays.asList(parkingLotSecurityPerson, owner));

        Object vehicleOne = new Object();
        Object vehicleTwo = new Object();
        parkingLot.park(vehicleOne);
        parkingLot.park(vehicleTwo);

        assertEquals(1, parkingLotSecurityPerson.isParkingLotFullNotify);
        assertEquals(1, owner.isParkingLotFullNotify);
    }

    @Test
    void givenParkingLotWithCapacity_WhenUnPark_ThenNotifyToSecurityPersonAndOwner() throws Exception {

        DummyParkingLotSecurityPerson parkingLotSecurityPerson = new DummyParkingLotSecurityPerson();
        DummyParkingLotOwner owner = new DummyParkingLotOwner();

        ParkingLot parkingLot = new ParkingLot(2, Arrays.asList(parkingLotSecurityPerson, owner));


        Object vehicleOne = new Object();
        Object vehicleTwo = new Object();
        parkingLot.park(vehicleOne);
        parkingLot.park(vehicleTwo);

        parkingLot.unPark(vehicleTwo);

        assertEquals(1, parkingLotSecurityPerson.isSpaceIsAvailableNotify);
        assertEquals(1, owner.isSpaceIsAvailableNotify);

    }

    @Test
    void givenParkingLot_whenRegisterAnotherPerson_theyShouldGetNotification() throws Exception {

        DummyParkingLotOwner owner = new DummyParkingLotOwner();

        List<Observer> observers = new ArrayList<>();
        observers.add(owner);
        ParkingLot parkingLot = new ParkingLot(2, observers);

        DummyParkingLotSecurityPerson parkingLotSecurityPerson = new DummyParkingLotSecurityPerson();

        Object vehicleOne = new Object();
        Object vehicleTwo = new Object();
        parkingLot.park(vehicleOne);
        parkingLot.park(vehicleTwo);

        assertEquals(1, owner.isParkingLotFullNotify);

        parkingLot.register(parkingLotSecurityPerson);
        parkingLot.unPark(vehicleTwo);

        assertEquals(1, parkingLotSecurityPerson.isSpaceIsAvailableNotify);

    }

    @Test
    void givenParkingLot_whenUnRegisterPerson_theyShouldNotGetNotification() throws Exception {

        DummyParkingLotOwner owner = new DummyParkingLotOwner();
        DummyParkingLotSecurityPerson parkingLotSecurityPerson = new DummyParkingLotSecurityPerson();
        List<Observer> observers = new ArrayList<>();
        observers.add(owner);
        observers.add(parkingLotSecurityPerson);

        ParkingLot parkingLot = new ParkingLot(2, observers);


        Object vehicleOne = new Object();
        Object vehicleTwo = new Object();
        parkingLot.park(vehicleOne);
        parkingLot.park(vehicleTwo);

        assertEquals(1, owner.isParkingLotFullNotify);
        assertEquals(1, parkingLotSecurityPerson.isParkingLotFullNotify);

        parkingLot.unRegister(parkingLotSecurityPerson);
        parkingLot.unPark(vehicleTwo);



        assertEquals(1, owner.isSpaceIsAvailableNotify);
        assertEquals(0, parkingLotSecurityPerson.isSpaceIsAvailableNotify);
    }


}
