package com.gmail.at.kotamadeo;

public class Car {
    private final int carSerialNumber;
    private static int counterSerialNumber;

    public Car() {
        counterSerialNumber++;
        carSerialNumber = counterSerialNumber;
    }

    @Override
    public String toString() {
        return "автомобиль " + carSerialNumber;
    }
}

