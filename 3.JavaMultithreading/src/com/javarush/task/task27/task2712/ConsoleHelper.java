package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Dish;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ConsoleHelper {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static void writeMessage(String message) {
        System.out.println(message);
    }
    public static String readString() throws IOException {
        return reader.readLine();
    }
    public static List<Dish> getAllDishesForOrder() throws IOException {
        writeMessage(Dish.allDishesToString());
        writeMessage("Выберите блюдо:");
        List<Dish> dishes = new ArrayList<>();
        String str = readString();
        while (!str.equals("exit")) {
            try {
                dishes.add(Dish.valueOf(str));
            } catch (IllegalArgumentException e) {
                writeMessage("Такого блюда нет!");
            }
            str = readString();
        }
        return dishes;
    }
}
