package com.mcj010.juc.c_026_01_ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class T08_CachedPool {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newCachedThreadPool();
        //[Running, pool size = 0, active threads = 0, queued tasks = 0, completed tasks = 0]
        System.out.println(service);
        for (int i = 0; i < 2; i++) {
            service.execute(()->{
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //pool-1-thread-1
                System.out.println(Thread.currentThread().getName());
            });
        }
        //[Running, pool size = 2, active threads = 2, queued tasks = 0, completed tasks = 0]
        System.out.println(service);

        TimeUnit.SECONDS.sleep(3);
        //[Running, pool size = 2, active threads = 0, queued tasks = 0, completed tasks = 2]
        System.out.println(service);

        service.shutdown();
    }
}
