package com.javarush.task.task32.task3210;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/* 
Используем RandomAccessFile
*/

public class Solution {
    public static void main(String... args) throws Exception {
        if (args.length > 2) {
            RandomAccessFile raf = new RandomAccessFile(args[0], "rw");
            raf.seek(Integer.valueOf(args[1]));
            byte[] buf = new byte[args[2].length()];
            raf.read(buf, 0, args[2].length());
            raf.seek(raf.length());
            if (args[2].equals(new String(buf))) {
                raf.write("true".getBytes());
            } else {
                raf.write("false".getBytes());
            }
        }
    }
}
