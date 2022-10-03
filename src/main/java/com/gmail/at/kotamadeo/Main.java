package com.gmail.at.kotamadeo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(6);
        CarDealer carDealer = new CarDealer();
        Buyer[] buyers = new Buyer[5];
        for (int i = 0; i < buyers.length; i++) {
            buyers[i] = new Buyer(carDealer);
            executorService.execute(buyers[i]);
        }
        CarFactory[] carFactories = new CarFactory[1];
        for (int i = 0; i < carFactories.length; i++) {
            carFactories[i] = new CarFactory(carDealer);
            executorService.execute(carFactories[i]);
        }
        executorService.shutdown();
    }
}