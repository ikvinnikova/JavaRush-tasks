package com.javarush.task.task30.task3004;

import java.math.BigInteger;
import java.util.concurrent.RecursiveTask;

public class BinaryRepresentationTask extends RecursiveTask<String> {
    private int i;

    public BinaryRepresentationTask(int i) {
        this.i = i;
    }

    @Override
    protected String compute() {
        if (i<0)
            return "0";
        if (i == 0)
            return "";
        int a = i % 2;
        int b = i / 2;
        BinaryRepresentationTask task = new BinaryRepresentationTask(b);
        task.fork();
        return task.join() + a;
    }
}
