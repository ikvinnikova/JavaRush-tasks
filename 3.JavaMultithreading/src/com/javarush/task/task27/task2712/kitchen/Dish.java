package com.javarush.task.task27.task2712.kitchen;

public enum Dish {
    FISH(25),
    STEAK(30),
    SOUP(13),
    JUICE(5),
    WATER(3);

    private int duration;

    public int getDuration() {
        return duration;
    }

    Dish(int i) {
        duration = i;
    }

    public static String allDishesToString() {
        String s = "";
        for (Dish dish:
             Dish.values()) {
            s = s + dish.toString() + ", ";
        }
        return s.substring(0, s.length() - 2);
    }
}
