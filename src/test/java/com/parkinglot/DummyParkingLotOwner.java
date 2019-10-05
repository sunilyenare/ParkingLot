package com.parkinglot;

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
