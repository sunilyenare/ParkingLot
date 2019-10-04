package com.parkinglot;

public class ParkingLotOwner implements Observer {
    private String message;


    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }
}
