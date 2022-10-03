package com.gmail.at.kotamadeo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.Thread.currentThread;
import static java.util.concurrent.TimeUnit.SECONDS;

public class CarDealer {
    private final int TIME_TO_SALE_CAR = 10;
    private final int CAPACITY_OF_GARAGE = 10;
    private final Manager manager = new Manager(this);
    private final List<Car> garageDealer = new ArrayList<>(CAPACITY_OF_GARAGE);
    private static int buyerCounter;
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();

    public void addCarToGarage(Car car) {
        lock.lock();
        try {
            garageDealer.add(car);
            System.out.println("В автосалон поступил новый " + car);
            System.out.println("В гараже автомобилей: " + garageDealer.size());
            condition.signal();
        } finally {
            lock.unlock();
        }
    }

    public void sellCar() {
        lock.lock();
        try {
            buyerCounter++;
            final String buyerName = "Покупатель " + buyerCounter;
            currentThread().setName(buyerName);
            System.out.println(buyerName + " зашёл в автосалон");
            while (garageDealer.isEmpty()) {
                System.out.println("Машин нет");
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Car car = garageDealer.remove(garageDealer.size() - 1);
            System.out.println("Автосалон оформляет документы на " + car);
            try {
                SECONDS.sleep(TIME_TO_SALE_CAR);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(currentThread().getName() + " уехал на " + car);
        } finally {
            lock.unlock();
        }
    }
}
