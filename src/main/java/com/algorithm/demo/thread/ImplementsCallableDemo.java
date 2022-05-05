package com.algorithm.demo.thread;

import java.util.concurrent.*;

/**
 * 线程测试类2
 */
public class ImplementsCallableDemo implements Callable<String>{

    FutureTask<String> futureTask;

    @Override
    public String call() throws Exception {
        return "hello";
    }

    /**
     * 创建并启动线程
     */
    public void RunTask()
    {
        futureTask = new FutureTask<>(new ImplementsCallableDemo());
        new Thread(futureTask).start();
    }

    /**
     * 获取线程运行结果
     */
    public void GetResult()
    {
        String result = null;
        try {
            result = futureTask.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(result);
    }
}
