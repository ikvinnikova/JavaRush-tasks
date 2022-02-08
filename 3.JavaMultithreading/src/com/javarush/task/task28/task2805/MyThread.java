package com.javarush.task.task28.task2805;

public class MyThread extends Thread {
    private static int priority = 1;
    public MyThread() {
            updatePriority();
    }

    private void updatePriority() {
        if (getThreadGroup().getMaxPriority() >= priority) {
            this.setPriority(priority);
        } else {
            this.setPriority(getThreadGroup().getMaxPriority());
        }
        priority = priority % 10 + 1;
    }

    public MyThread(Runnable target) {
        super(target);
        updatePriority();

    }

    public MyThread(ThreadGroup group, Runnable target) {
        super(group, target);
        updatePriority();
    }

    public MyThread(String name) {
        super(name);
        updatePriority();

    }

    public MyThread(ThreadGroup group, String name) {
        super(group, name);
        updatePriority();

    }

    public MyThread(Runnable target, String name) {
        super(target, name);
        updatePriority();

    }

    public MyThread(ThreadGroup group, Runnable target, String name) {
        super(group, target, name);
        updatePriority();

    }

    public MyThread(ThreadGroup group, Runnable target, String name, long stackSize) {
        super(group, target, name, stackSize);
        updatePriority();
    }

//    public MyThread(ThreadGroup group, Runnable target, String name, long stackSize, boolean inheritThreadLocals) {
//        super(group, target, name, stackSize, inheritThreadLocals);
//        updatePriority();
//    }
}
