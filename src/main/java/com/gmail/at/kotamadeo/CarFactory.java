package com.gmail.at.kotamadeo;

import static java.lang.Thread.currentThread;
import static java.util.concurrent.TimeUnit.SECONDS;

public class CarFactory implements Runnable {
    private final int NUMBER_OF_CAR_PRODUCED = 10;
    private final int TIME_TO_PRODUCE_CAR = 5;
    private final CarDealer carDealer;
    private static int carFactoryCounter;
    private final String carFactoryName;

    public CarFactory(CarDealer carDealer) {
        this.carDealer = carDealer;
        carFactoryCounter++;
        this.carFactoryName = "Производитель " + carFactoryCounter;
    }

    public void run() {
        currentThread().setName(carFactoryName);
        Car car;
        for (int i = 0; i < NUMBER_OF_CAR_PRODUCED; i++) {
            car = releaseCar();
            carDealer.addCarToGarage(car);
        }
    }

    public Car releaseCar() {
        String name = currentThread().getName();
        System.out.println(name + " начал изготовление автомобиля");
        try {
            SECONDS.sleep(TIME_TO_PRODUCE_CAR);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Car car = new Car();
        System.out.println(name + " выпустил " + car);
        return car;
    }
}
