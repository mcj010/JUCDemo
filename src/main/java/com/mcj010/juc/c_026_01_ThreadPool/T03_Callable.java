package com.mcj010.juc.c_026_01_ThreadPool;

import java.util.concurrent.*;

public class T03_Callable {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<String> c = new Callable() {
            @Override
            public String call() throws Exception {
               // TimeUnit.SECONDS.sleep(10);
                return "Hello Callable";
            }
        };

        ExecutorService service = Executors.newCachedThreadPool();

        //异步
        Future<String> future = service.submit(c);

        System.out.println(future.get());//阻塞

        service.shutdown();
    }
}
