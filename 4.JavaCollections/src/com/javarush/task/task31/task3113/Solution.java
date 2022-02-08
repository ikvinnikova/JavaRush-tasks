package com.javarush.task.task31.task3113;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/* 
Что внутри папки?
*/

public class Solution {

    private static class CustomVisitor extends SimpleFileVisitor<Path> {
        List<Path> dirs;
        List<Path> files;
        List<Integer> size;

        public CustomVisitor(List<Path> dirs, List<Path> files, List<Integer> size) {
            this.dirs = dirs;
            this.files = files;
            this.size = size;
        }

        @Override
        public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
            if (Files.isDirectory(dir)) {
                dirs.add(dir);
            }
            return super.postVisitDirectory(dir, exc);
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            if (Files.isDirectory(file)) {
                dirs.add(file);
            } else {
                if (Files.isRegularFile(file)) {
                    files.add(file);
                    byte[] content = Files.readAllBytes(file);
                    size.set(0, size.get(0) + content.length);
                }
            }

            return super.visitFile(file, attrs);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String sDir = reader.readLine();
        Path dir = Paths.get(sDir);
        if (!Files.isDirectory(dir)) {
            System.out.println(sDir + " - не папка");
        } else {
            List<Path> dirs = new ArrayList<>();
            List<Path> files = new ArrayList<>();
            List<Integer> size = new ArrayList<>();
            size.add(0);
            CustomVisitor customVisitor = new CustomVisitor(dirs, files, size);
            Files.walkFileTree(dir, customVisitor);
            System.out.println("Всего папок - " + (customVisitor.dirs.size() - 1));
            System.out.println("Всего файлов - " + customVisitor.files.size());
            System.out.println("Общий размер - " + customVisitor.size.get(0));
        }
    }
}
