package com.javarush.task.task32.task3201;

import java.io.IOException;
import java.io.RandomAccessFile;

/* 
Запись в существующий файл
*/

public class Solution {
    public static void main(String... args) throws Exception {
        if (args.length > 2) {
            RandomAccessFile raf = new RandomAccessFile(args[0], "rw");
            if (Integer.valueOf(args[1]) > raf.length()) {
                raf.seek(raf.length());
            } else {
                raf.seek(Integer.valueOf(args[1]));
            }
            raf.write(args[2].getBytes());
            raf.close();
        }

    }
}
