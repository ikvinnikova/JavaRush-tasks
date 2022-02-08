package com.javarush.task.task31.task3105;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/* 
Добавление файла в архив
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        // Проверяем существует ли zip файл
        class MyEntry {
            String entryName;
            ByteArrayOutputStream stream;

            public MyEntry(String entry, ByteArrayOutputStream stream) {
                this.entryName = entry;
                this.stream = stream;
            }
        };

        try(ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(args[1]))) {
            List<MyEntry> entries = new ArrayList<>();

            ZipEntry entry = zipInputStream.getNextEntry();
            while (entry != null) {
                byte[] buf = new byte[1024*4];
                int c = zipInputStream.read(buf);
                if (!entry.isDirectory()) {
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    while (zipInputStream.available() > 0) {
                        stream.write(buf, 0, c);
                        c = zipInputStream.read(buf);
                    }
                    entries.add(new MyEntry(entry.getName(), stream));
                    stream.close();
                }
                zipInputStream.closeEntry();
                entry = zipInputStream.getNextEntry();
            }

            try (ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(args[1]))) {
                Path newFile = Paths.get(args[0]);
                ZipEntry newZipEntry = new ZipEntry("new/" + newFile.getFileName().toString());
                zipOutputStream.putNextEntry(newZipEntry);
                Files.copy(Paths.get(args[0]), zipOutputStream);
                zipOutputStream.closeEntry();
                for (MyEntry myEntry:
                     entries) {
                    if (!myEntry.entryName.equals(newZipEntry.getName())) {

                            zipOutputStream.putNextEntry(new ZipEntry(myEntry.entryName));
                            myEntry.stream.writeTo(zipOutputStream);
                            zipOutputStream.closeEntry();

                            //zipOutputStream.write(myEntry.buf);
                        //}

                    }
                }

                /*
                try (ZipInputStream zipInputStream1 = new ZipInputStream(new FileInputStream(tempZipFile.toFile()))) {
                    zipEntry = zipInputStream1.getNextEntry();
                    while (zipEntry != null) {
                        if (!zipEntry.getName().equals(newZipEntry.getName())) {
                            zipOutputStream.putNextEntry(zipEntry);
                            copyData(zipInputStream1, zipOutputStream);
                            zipInputStream1.closeEntry();
                            zipOutputStream.closeEntry();
                        }
                        zipEntry = zipInputStream1.getNextEntry();
                    }

                }
*/
            }
        }
    }

    private static void copyData(InputStream in, OutputStream out) throws Exception {
        byte[] buffer = new byte[8 * 1024];
        int len;
        while ((len = in.read(buffer)) > 0) {
            out.write(buffer, 0, len);
        }
    }


}
