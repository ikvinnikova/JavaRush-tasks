package com.javarush.task.task25.task2502;

import java.util.*;

/* 
Машину на СТО не повезем!
*/

public class Solution {
    public static enum Wheel {
        FRONT_LEFT,
        FRONT_RIGHT,
        BACK_LEFT,
        BACK_RIGHT
    }

    public static class Car {
        protected List<Wheel> wheels;

        public Car() throws IllegalArgumentException {
            //init wheels here
            wheels = new LinkedList<>();
            String [] wheelsStr = loadWheelNamesFromDB();
            if (wheelsStr.length != 4) {
                throw new IllegalArgumentException();
            }
            for (String wheelName: wheelsStr
                 ) {
                if (Wheel.valueOf(wheelName) == null) {
                    throw new IllegalArgumentException();
                } else {
                    wheels.add(Wheel.valueOf(wheelName));
                }
            }
        }

        protected String[] loadWheelNamesFromDB() {
            //this method returns mock data
            return new String[]{"FRONT_LEFT", "FRONT_RIGHT", "BACK_LEFT", "BACK_RIGHT"};
        }
    }

    public static void main(String[] args) {
        Car car = new Car();
    }
}
