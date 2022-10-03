package com.gmail.at.kotamadeo;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Buyer implements Runnable {
    private CarDealer carDealer;

    @Override
    public void run() {
        carDealer.sellCar();
    }


}