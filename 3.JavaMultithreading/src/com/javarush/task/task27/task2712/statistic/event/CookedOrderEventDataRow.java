package com.javarush.task.task27.task2712.statistic.event;

import com.javarush.task.task27.task2712.kitchen.Dish;

import java.util.Date;
import java.util.List;

public class CookedOrderEventDataRow implements EventDataRow {
    private List<Dish> cookingDishes;
    private int cookingTimeSeconds;
    private String cookName;
    private String tabletName;
    private Date currentDate;

    public CookedOrderEventDataRow(String tabletName, String cookName, int cookingTimeSeconds, List<Dish> cookingDishes) {
        this.cookingDishes = cookingDishes;
        this.cookingTimeSeconds = cookingTimeSeconds;
        this.cookName = cookName;
        this.tabletName = tabletName;
        this.currentDate = new Date();
    }

    @Override
    public EventType getType() {
        return EventType.COOKED_ORDER;
    }

    @Override
    public Date getDate() {
        return currentDate;
    }

    public String getCookName() {
        return cookName;
    }

    @Override
    public int getTime() {
        return cookingTimeSeconds;
    }
}
