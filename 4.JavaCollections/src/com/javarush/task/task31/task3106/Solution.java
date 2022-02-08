package com.javarush.task.task31.task3106;

import java.io.*;
import java.nio.file.Files;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/* 
Разархивируем файл
*/

public class Solution {
    public static void main(String[] args) throws FileNotFoundException {
        List<FileInputStream> files = new ArrayList<>();
        List<String> fil = new ArrayList<>();
        for (int i = 1; i < args.length; i++) {
            fil.add(args[i]);
        }
        Collections.sort(fil);
        for (String s:
             fil) {
            files.add(new FileInputStream(s));
        }
        Enumeration<FileInputStream> en = Collections.enumeration(files);

        SequenceInputStream sequenceInputStream = new SequenceInputStream(en);
        ZipInputStream inputStream = new ZipInputStream(sequenceInputStream);
        try (FileOutputStream outputStream = new FileOutputStream(args[0])) {

            try {
                while (inputStream.available() > 0) {
                    ZipEntry zipEntry = inputStream.getNextEntry();
//                    inputStream.skip(4);
                    byte[] buf = new byte[1000];
                    int c;
                    while ((c = inputStream.read(buf)) != -1) {
//                   ZipEntry writeEntry = new ZipEntry(zipEntry);
                        outputStream.write(buf, 0, c);

                    }
                    inputStream.closeEntry();
                }
                inputStream.close();
                sequenceInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {

        }

    }
}
