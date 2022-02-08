package com.javarush.task.task25.task2512;

import java.util.LinkedList;
import java.util.List;

/* 
Живем своим умом
*/

public class Solution implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        t.interrupt();
        Throwable el = e;
        StringBuilder sb = new StringBuilder();

        while (el != null) {
            sb.insert(0, el.getClass().getName() + ": " + el.getMessage() + "\n");
            el = el.getCause();
        }
        System.out.print(sb.toString());

    }

    public static void main(String[] args) {
        Thread t = new Thread(){
            @Override
            public void run() {
                throw new RuntimeException("ABC", new Exception("DEF", new IllegalAccessException("GHI")));
            }
        };
        t.setUncaughtExceptionHandler(new Solution());
        t.start();

    }
}
