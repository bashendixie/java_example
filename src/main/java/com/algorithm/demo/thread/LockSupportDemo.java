package com.algorithm.demo.thread;

import java.util.concurrent.locks.LockSupport;

public class LockSupportDemo extends Thread{

    @Override
    public void run()
    {
            System.out.println("begin" + System.currentTimeMillis());
            LockSupport.park();
            System.out.println("end" + System.currentTimeMillis());
    }
}
