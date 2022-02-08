package com.javarush.task.task33.task3310.strategy;

import java.io.Serializable;

public class Entry implements Serializable {
    Long key;
    String value;
    Entry next;
    int hash;

    public Entry(int hash, Long key, String value, Entry next) {
        this.key = key;
        this.value = value;
        this.next = next;
        this.hash = hash;
    }
    public Long getKey() {
        return key;
    }
    public String getValue() {
        return value;
    }
    public int hashCode() {
        return key.hashCode() ^ value.hashCode();
    }
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof Entry) {
            Entry e = (Entry) o;
            return e.getKey().equals(key) && e.getValue().equals(value);
        }
        return false;
    }
    public String toString() {
        return key.toString() + "=" + value;
    }
}
