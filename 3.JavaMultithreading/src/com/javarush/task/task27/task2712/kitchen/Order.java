package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.Tablet;
import com.javarush.task.task27.task2712.ad.AdvertisementManager;

import java.io.IOException;
import java.util.List;

public class Order {
    private final Tablet tablet;
    protected List<Dish> dishes;

    public Order(Tablet tablet) throws IOException {
        this.tablet = tablet;
        dishes = ConsoleHelper.getAllDishesForOrder();
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    @Override
    public String toString() {
        String res = "Your order: [";
        for (Dish dish:
             dishes) {
            res = res + dish + ", ";
        }
        res = res.substring(0, res.length() - 2) + "] of " + tablet.toString() + ", cooking time " + getTotalCookingTime() + "min";

        return res;
    }

    public int getTotalCookingTime() {
        int time = 0;
        for (Dish dish:
             dishes) {
            time = time + dish.getDuration();
        }
        return time;
    }

    public boolean isEmpty() {
        return dishes.isEmpty();
    }

    public Tablet getTablet() {
        return tablet;
    }
}
