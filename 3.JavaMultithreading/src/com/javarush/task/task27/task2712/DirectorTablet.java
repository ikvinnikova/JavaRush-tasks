package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.statistic.StatisticManager;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class DirectorTablet {
    public void printAdvertisementProfit() {
        Map<Date, Long> totalIncome = new TreeMap<>(StatisticManager.getInstance().getTotalIncome());
        List<Date> list = new ArrayList<>(totalIncome.keySet());
        list.sort(new Comparator<Date>() {
            @Override
            public int compare(Date o1, Date o2) {
                return o2.compareTo(o1);
            }
        });

        double sum = 0;
        for (Date date : list
             ) {
            //SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy HH:mm", Locale.ENGLISH);
            SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
            ConsoleHelper.writeMessage(format.format(date) + " - " + String.format("%.2f", (double)totalIncome.get(date)/100).replace(",", "."));
            sum = sum + (double)totalIncome.get(date)/100;
        }
        ConsoleHelper.writeMessage("Total - " + String.format("%.2f", sum).replace(",", "."));
        //ConsoleHelper.writeMessage(System.lineSeparator());
    }

    public void printCookWorkloading() {
        Map<Date, Map<String, Integer>> occupancy = new TreeMap<>(StatisticManager.getInstance().getCookWorkloading());
        List<Date> list = new ArrayList<>(occupancy.keySet());
        list.sort(new Comparator<Date>() {
            @Override
            public int compare(Date o1, Date o2) {
                return o2.compareTo(o1);
            }
        });

        for (Date date:
                list) {
         //                if (c != 0) {

           // }

            SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
            //SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy hh:mm", Locale.ENGLISH);
            ConsoleHelper.writeMessage("");
            ConsoleHelper.writeMessage(format.format(date));

            for (Map.Entry<String, Integer> e:
                 occupancy.get(date).entrySet()) {
                String s = String.format("%.0f min", Math.ceil(e.getValue()/60));
                ConsoleHelper.writeMessage(e.getKey() + " - " + s);
            }
        }
    }
    public void printActiveVideoSet() {}
    public void printArchivedVideoSet() {}
}
