package com.javarush.task.task25.task2506;

public class LoggingStateThread extends Thread{
    Thread thread;

    public LoggingStateThread(Thread thread) {
        super();
        this.thread = thread;
    }

    @Override
    public void run() {
        State state = thread.getState();
        State newState = null;
        System.out.println(state);
        while (state != State.TERMINATED) {
            newState = thread.getState();
            if (state != newState) {
                System.out.println(newState);
                state = newState;
            }
        }
       // System.out.println(thread.getState());
    }
}
