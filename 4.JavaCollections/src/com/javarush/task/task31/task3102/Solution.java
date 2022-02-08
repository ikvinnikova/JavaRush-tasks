package com.javarush.task.task31.task3102;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

/* 
Находим все файлы
*/

public class Solution {
    public static List<String> getFileTree(String root) throws IOException {
        File dir = new File(root);
        List<String> fileList = new ArrayList<>();
        Queue<File> fileQueue = new PriorityQueue<>();
        fileQueue.offer(dir);
        while (!fileQueue.isEmpty()) {
            for (File f:
                 fileQueue.poll().listFiles()) {
                if (f.isDirectory()) {
                    fileQueue.offer(f);
                } else {
                    fileList.add(f.getAbsolutePath());
                }
            }
        }
        return fileList;

    }

    public static void main(String[] args) throws IOException {
        List<String> list = getFileTree("D:/arx");
        for (String s:
             list) {
            System.out.println(s);
        }
    }
}
