package com.javarush.task.task25.task2508;

import static java.lang.Thread.interrupted;

public class TaskManipulator implements Runnable, CustomThreadManipulator {
    Thread thread;

    @Override
    public void run() {
        boolean isI = false;
        while (!Thread.currentThread().isInterrupted() && !isI) {
            try {
                System.out.println(thread.getName());
                Thread.sleep(100);
            } catch (InterruptedException e) {isI = true;}
        }
    }

    @Override
    public void start(String threadName) {
        thread = new Thread(this, threadName);
        thread.start();
    }

    @Override
    public void stop() {
        thread.interrupt();
    }
}
