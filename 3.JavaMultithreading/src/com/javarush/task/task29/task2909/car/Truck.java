package com.javarush.task.task29.task2909.car;

public class Truck extends Car{
    @Override
    public int getMaxSpeed() {
        int MAX_TRUCK_SPEED = 80;
        return MAX_TRUCK_SPEED;
    }

    public Truck(int numberOfPassengers) {
        super(0, numberOfPassengers);
    }
}
