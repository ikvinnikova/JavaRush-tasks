package com.javarush.task.task27.task2712.ad;

import java.util.ArrayList;
import java.util.List;

public class AdvertisementStorage {
    private static AdvertisementStorage instance = new AdvertisementStorage();
    private final List<Advertisement> videos = new ArrayList<>();

    private AdvertisementStorage() {
        Object someContent = new Object();
        videos.add(new Advertisement(someContent, "First Video", 5000, 100, 11 * 60)); // 3 min
        videos.add(new Advertisement(someContent, "Second Video", 500, 10, 14 * 60)); //15 min
        videos.add(new Advertisement(someContent, "Third Video", 100, 2, 12 * 60)); //10 min
        videos.add(new Advertisement(someContent, "Fourth Video", 100, 2, 1 * 60));
        videos.add(new Advertisement(someContent, "Fifth Video", 100, 1, 12 * 60));//10 min
        videos.add(new Advertisement(someContent, "Sixth Video", 100, 2, 2 * 60));
    }

    public static AdvertisementStorage getInstance() {
        return instance;
    }

    public List<Advertisement> list() {
        return videos;
    }

    public void add(Advertisement advertisement) {
        videos.add(advertisement);
    }
}
