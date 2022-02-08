package com.javarush.task.task36.task3605;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.TreeSet;

/* 
Использование TreeSet
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            throw new IOException();
        }
        FileInputStream fs = new FileInputStream(args[0]);
        TreeSet<Character> treeSet = new TreeSet();
        while (fs.available() > 0) {
            char c = Character.toLowerCase((char) fs.read());
            if (c >= 'a' && c <= 'z') {
                if (!treeSet.contains(c)) {
                    treeSet.add(c);
                }
            }
        }
        fs.close();
        int counter = Integer.min(treeSet.size(), 5);
        Iterator iterator = treeSet.iterator();

        for (int i = 0; i < counter && iterator.hasNext(); i++) {
            System.out.print(iterator.next());
        }
    }
}

