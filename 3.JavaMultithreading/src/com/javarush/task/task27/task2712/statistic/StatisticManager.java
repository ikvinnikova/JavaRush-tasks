package com.javarush.task.task27.task2712.statistic;

import com.javarush.task.task27.task2712.ad.Advertisement;
import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventType;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;

import java.util.*;

public class StatisticManager {
    private static StatisticManager instance = new StatisticManager();
    private StatisticManager.StatisticStorage statisticStorage = new StatisticStorage();
    private Set<Cook> cooks = new HashSet<>();

    private StatisticManager() {

    }

    public static StatisticManager getInstance() {
        return instance;
    }
    public void register(EventDataRow data) {
        statisticStorage.put(data);
    }
    public void register(Cook cook) {
        cooks.add(cook);
    }

    public Date removeTime (Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public Map<Date, Map<String, Integer>> getCookWorkloading () {
        Map<Date, Map<String, Integer>> occupancy = new HashMap<>();
        List<EventDataRow> storage = statisticStorage.get().get(EventType.COOKED_ORDER);
        for (EventDataRow e: storage
             ) {
            CookedOrderEventDataRow row = (CookedOrderEventDataRow) e;
            Date date = removeTime(e.getDate());
            if (occupancy.containsKey(date)) {
                Map<String, Integer> entry = new TreeMap<>(occupancy.get(date));
                if (entry.containsKey(row.getCookName())) {
                    entry.replace(row.getCookName(), entry.get(row.getCookName()) + row.getTime());
                    occupancy.replace(date, entry);
                } else {
                    entry.put(row.getCookName(), e.getTime());
                    occupancy.replace(date, entry);
                }
            } else {
                Map<String, Integer> entry = new TreeMap<>();
                entry.put(row.getCookName(), row.getTime());
                occupancy.put(date, entry);
            }
        }
        return occupancy;
    }

    public Map<Date, Long> getTotalIncome () {
        Map<Date, Long> totalIncome = new HashMap<>();
        List<EventDataRow> storage = statisticStorage.get().get(EventType.SELECTED_VIDEOS);
        for (EventDataRow row: storage
             ) {
            VideoSelectedEventDataRow videoRow = (VideoSelectedEventDataRow) row;
            Date date = removeTime(row.getDate());
            if (totalIncome.containsKey(date)) {
                long sum = totalIncome.get(date) + videoRow.getAmount();
                totalIncome.replace(date, sum);
            } else {

                totalIncome.put(date, getAdAmount(videoRow.getOptimalVideoSet()));
            }
        }
        return totalIncome;
    }

    private long getAdAmount(List<Advertisement> list) {
        long res = 0;
        for (Advertisement ad :
                list) {
            if (ad != null) {
                res = res + ad.getAmountPerOneDisplaying();
            }
        }
        return res;
    }

    private static class StatisticStorage {
        private Map<EventType, List<EventDataRow>> storage = new HashMap<>();
        public StatisticStorage() {
            for (EventType e: EventType.values()
                 ) {
                storage.put(e, new ArrayList<EventDataRow>());
            }
        }
        private Map<EventType, List<EventDataRow>> get() {return storage;}
        private void put(EventDataRow data) {
            List<EventDataRow> list = storage.get(data.getType());
            list.add(data);
            storage.replace(data.getType(), list);
        }
    }
}
