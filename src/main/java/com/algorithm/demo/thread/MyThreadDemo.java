package com.algorithm.demo.thread;

import java.util.concurrent.atomic.AtomicBoolean;

public class MyThreadDemo implements Runnable
{
    // to stop the thread
    private final AtomicBoolean running = new AtomicBoolean(false);
    private int interval = 500000;
    private String name;
    Thread t;

    public MyThreadDemo(String threadname)
    {
        name = threadname;
        t = new Thread(this, name);
        System.out.println("New thread: " + t);
        t.start(); // Starting the thread
    }

    // execution of thread starts from run() method
    public void run()
    {
        running.set(true);
        int i = 0;
        while (!running.get()) {
            System.out.println(name + ": " + i);
            i++;
            try {
                Thread.sleep(interval);
            }
            catch (InterruptedException e) {
                System.out.println("Caught:" + e);
            }
        }
        System.out.println(name + " Stopped?");
    }

    public void stop() {
        running.set(false);
    }
}
