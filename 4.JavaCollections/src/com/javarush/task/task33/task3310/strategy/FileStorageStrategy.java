package com.javarush.task.task33.task3310.strategy;

import java.io.File;

public class FileStorageStrategy implements StorageStrategy {
    private FileBucket[] table;
    private long bucketSizeLimit = DEFAULT_BUCKET_SIZE_LIMIT;
    static final long DEFAULT_BUCKET_SIZE_LIMIT = 10000;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    private int size;
    long maxBucketSize;

    public FileStorageStrategy() {
        table = new FileBucket[DEFAULT_INITIAL_CAPACITY];
        for (int i = 0; i < table.length; i++) {
            table[i] = new FileBucket();
        }
    }

    public long getBucketSizeLimit() {
        return bucketSizeLimit;
    }

    public void setBucketSizeLimit(long bucketSizeLimit) {
        this.bucketSizeLimit = bucketSizeLimit;
    }

    public int hash(Long key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    public int indexFor(int hash, int length) {
        return hash & (length - 1);
    }

    public Entry getEntry(Long key) {
        int hash = (key == null) ? 0 : hash(key);
        FileBucket bucket = table[indexFor(hash, table.length)];
        Entry e = bucket.getEntry();
        do {
            Object k;
            if (e.hash == hash &&
                    ((k = e.key) == key || (key != null && key.equals(k))))
                return e;
        } while ((e = e.next) != null);
        return null;
    }

    void resize(int newCapacity) {
        FileBucket[] oldTable = table;
        int oldCapacity = oldTable.length;
        if (oldCapacity == 1 << 30) {
            maxBucketSize = Integer.MAX_VALUE;
            return;
        }

        FileBucket[] newTable = new FileBucket[newCapacity];
        transfer(newTable);

        table = newTable;
        maxBucketSize = newCapacity;
    }

    void transfer(FileBucket[] newTable) {
        try {
            FileBucket[] src = table;
            int newCapacity = newTable.length;
            for (int j = 0; j < src.length; j++) {
                if (src[j] == null)
                    continue;
                Entry e = src[j].getEntry();
                if (e != null) {
                    do {
                        Entry next = e.next;
                        int i = indexFor(e.hash, newCapacity);
                        e.next = newTable[i].getEntry();
                        newTable[i].putEntry(e);
                        e = next;
                    } while (e != null);
                    src[j].remove();
                    src[j] = null;
                }
            }
        } catch (Exception e) {}
    }

    void addEntry(int hash, Long key, String value, int bucketIndex) {
        FileBucket bucket = table[bucketIndex];
        if (bucket == null) {
            table[bucketIndex] = new FileBucket();
        }
        Entry e = table[bucketIndex].getEntry();
        table[bucketIndex].putEntry(new Entry(hash, key, value, e));
        maxBucketSize++;
        if (maxBucketSize >= bucketSizeLimit)
            resize(2 * table.length);
    }

    void createEntry(int hash, Long key, String value, int bucketIndex) {
        Entry e = table[bucketIndex].getEntry();
        table[bucketIndex] = new FileBucket();
        table[bucketIndex].putEntry(new Entry(hash, key, value, e));
        maxBucketSize++;
    }

    @Override
    public boolean containsKey(Long key) {
        Entry e = getEntry(key);
        return e != null;
    }

    @Override
    public boolean containsValue(String value) {
        FileBucket[] tab = table;
        for (int i = 0; i < tab.length ; i++)
            for (Entry e = tab[i].getEntry() ; e != null ; e = e.next)
                if (value.equals(e.value))
                    return true;
        return false;
    }

    @Override
    public void put(Long key, String value) {
        if (key == null)
            return;
        int hash = hash(key);
        int i = indexFor(hash, table.length);
        for (Entry e = table[i].getEntry(); e != null; e = e.next) {
            Object k;
            if (e.hash == hash && ((k = e.key) == key || key.equals(k))) {
                return;
            }
        }
        addEntry(hash, key, value, i);
    }

    @Override
    public Long getKey(String value) {
        for (FileBucket bucket: table
        ) {
            Entry entry = bucket.getEntry();
            if (entry != null) {
                if (entry.getValue().equals(value)) {
                    return entry.getKey();
                }
            }
        }
        return null;
    }

    @Override
    public String getValue(Long key) {
        Entry e = getEntry(key);
        if (e != null) {
            return e.getValue();
        }
        return null;
    }
}
