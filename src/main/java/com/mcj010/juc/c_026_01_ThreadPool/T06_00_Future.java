package com.mcj010.juc.c_026_01_ThreadPool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class T06_00_Future {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> task = new FutureTask<>(()->{
            TimeUnit.MILLISECONDS.sleep(500);
            return 1000;
        });

        new Thread(task).start();

        System.out.println(task.get()); //阻塞
    }
}
