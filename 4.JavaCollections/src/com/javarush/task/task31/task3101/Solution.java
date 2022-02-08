package com.javarush.task.task31.task3101;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

/* 
Проход по дереву файлов
*/

public class Solution {
    private static List<File> getAllFiles(File dir, List<File> res) {
        for (File f :
                dir.listFiles()) {
            if (f.isDirectory()) {
                getAllFiles(f, res);
            } else {
                if (f.length() <= 50) {
                    res.add(f);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        if (args.length > 1) {
            File result = new File(args[1]);
            File dir = new File(args[0]);
            File resFile = new File(result.getParent() + "\\allFilesContent.txt");
            if (FileUtils.isExist(resFile)) {
                FileUtils.deleteFile(resFile);
            }
            FileUtils.renameFile(result, resFile);
            File[] files = dir.listFiles();
            List<File> fileList = getAllFiles(dir, new ArrayList<>());
            Collections.sort(fileList, new Comparator<File>() {
                @Override
                public int compare(File o1, File o2) {
                    return o1.getName().compareTo(o2.getName());
                }
            });

                //BufferedWriter writer = new BufferedWriter(new FileWriter(result,false));
                //writer.write("");
                //writer.close();
            try(FileOutputStream outputStream = new FileOutputStream(resFile)) {
                for (File f : fileList) {
                    BufferedReader reader = new BufferedReader(new FileReader(f));
                    while (reader.ready()) {
                        outputStream.write(reader.read());
                    }
                    outputStream.write(("\n").getBytes());
                    reader.close();
                }
            } catch (IOException e) {
            }
        }
    }
}
