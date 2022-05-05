package com.algorithm.demo.thread;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程测试类1
 */
public class ExtendsThreadDemo extends Thread {
    private int i = 0;
    public static AtomicInteger count = new AtomicInteger(0);
    public int k1 = 10;

    public ExtendsThreadDemo()
    {
    }

    @Override
    synchronized public void run()
    {
        super.run();
        for (int k = 0; k < 1000; k++)
        {
            System.out.println("i=" + (count.incrementAndGet()) + " ThreadName=" + Thread.currentThread().getName());
            //System.out.println("i=" + (count.addAndGet(1)) + " ThreadName=" + Thread.currentThread().getName());
            //System.out.println("i=" + (++i) + " aaThreadName=" + Thread.currentThread().getName());
        }
//        super.run();
//        int k = 0;
//        for(int i=0; i<10; i++)
//        {
//            k = k + 1;
//        }
//        System.out.println("demo1");

        //System.out.println("vvvvvvvvvvvvvvvvvvvv：" + count.get());
    }

    /**
     * 打印线程名称
     */
    public void Method_1()
    {
        System.out.println(Thread.currentThread().getName());
    }

}
