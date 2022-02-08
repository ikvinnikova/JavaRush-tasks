package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.kitchen.Waiter;

import java.util.Observer;

public class Restaurant {
    public static void main(String[] args) {
        Tablet tablet = new Tablet(1);
        Tablet tablet2 = new Tablet(2);
        Cook cook = new Cook("Ivan");
        Cook cook1 = new Cook("Igor");
        cook1.addObserver(new Waiter());
        cook.addObserver(new Waiter());
        tablet.addObserver(cook);
        tablet2.addObserver(cook1);
        tablet.createOrder();
        tablet.createOrder();
        tablet2.createOrder();
        tablet2.createOrder();
        DirectorTablet directorTablet = new DirectorTablet();
        directorTablet.printActiveVideoSet();
        directorTablet.printAdvertisementProfit();
        directorTablet.printArchivedVideoSet();
        directorTablet.printCookWorkloading();
    }
}
