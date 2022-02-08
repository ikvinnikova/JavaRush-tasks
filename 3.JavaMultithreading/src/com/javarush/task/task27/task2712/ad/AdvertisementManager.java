package com.javarush.task.task27.task2712.ad;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AdvertisementManager {
    private int timeSeconds;
    private final AdvertisementStorage storage = AdvertisementStorage.getInstance();

    public AdvertisementManager(int timeSeconds) {
        this.timeSeconds = timeSeconds;
    }

    public void processVideos() {
        List<Advertisement> list = storage.list();
        if (list.isEmpty()) {

            throw new NoVideoAvailableException();
        } else {
            Comparator<Advertisement> comparator = new Comparator<Advertisement>() {
                @Override
                public int compare(Advertisement o1, Advertisement o2) {
                    if (o2.getAmountPerOneDisplaying() - o1.getAmountPerOneDisplaying() != 0) {
                        return o2.getAmountPerOneDisplaying() > o1.getAmountPerOneDisplaying() ? 1 : -1;
                    }
                    return o2.getDuration() - o1.getDuration();
                }
            };
            list.sort(comparator);
            //List<List<Advertisement>> resultList = new ArrayList<>();
            //resultList.add(new ArrayList<>());
            getBestList(0, list, new ArrayList<>());
            Comparator<List<Advertisement>> comparator1 = new Comparator<List<Advertisement>>() {
                @Override
                public int compare(List<Advertisement> o1, List<Advertisement> o2) {
                    int res = 0;
                    if (getAdAmount(o2) - getAdAmount(o1) != 0) {
                        return getAdAmount(o2) > getAdAmount(o1) ? 1 : -1;
                    }
                    if (getAdDuration(o2) - getAdDuration(o1) != 0) {
                        return getAdDuration(o2) > getAdDuration(o1) ? 1 : -1;
                    }
                    return o1.size() - o2.size();
                }
            };
            Collections.sort(resultList, comparator1);

            StatisticManager.getInstance().register(new VideoSelectedEventDataRow(resultList.get(0), resultList.get(0).size(), getAdDuration(resultList.get(0))));
            for (Advertisement ad :
                    resultList.get(0)) {
                ConsoleHelper.writeMessage(ad.getName() + " is displaying... "
                        + (int) Math.floor(ad.getAmountPerOneDisplaying())
                        + ", "
                        + (int) ((double) ad.getAmountPerOneDisplaying() / ad.getDuration() * 1000));
                //                   + " " + ad.getDuration());
                ad.revalidate();
            }
        }

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

    private int getAdDuration(List<Advertisement> list) {
        int res = 0;
        for (Advertisement ad :
                list) {
            if (ad != null) {
                res = res + ad.getDuration();
            }
        }
        return res;
    }
    private List<List<Advertisement>> resultList = new ArrayList<>();
    public void getBestList(int i, List<Advertisement> list, List<Advertisement> next) {
        if (i < list.size()) {
            List<Advertisement> next1 = new ArrayList<>(next);
            if (getAdDuration(next) + list.get(i).getDuration() <= timeSeconds && list.get(i).getHits() > 0) {
                next.add(list.get(i));
            }
            getBestList(i + 1, list, next);
            getBestList(i + 1, list, next1);
        } else {
            resultList.add(next);
        }
    }
}
