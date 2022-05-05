package com.algorithm.demo.thread;

import java.util.concurrent.atomic.AtomicBoolean;

public class ControlSubThread implements Runnable {

    private Thread worker;
    private AtomicBoolean running = new AtomicBoolean(false);
    private AtomicBoolean stopped = new AtomicBoolean(false);
    private int interval = 50000;

    // ...

    public void interrupt() {
        running.set(false);
        worker.interrupt();
    }

    public void start() {
        worker = new Thread(this);
        worker.start();
    }

    boolean isRunning() {
        return running.get();
    }

    boolean isStopped() {
        return stopped.get();
    }

    public void run() {
        running.set(true);
        stopped.set(false);
        while (running.get()) {
            try {
                Thread.sleep(interval);
            } catch (InterruptedException e){
                Thread.currentThread().interrupt();
                System.out.println(
                        "Thread was interrupted, Failed to complete operation");
            }
            // do something
        }
        stopped.set(true);
    }
}
