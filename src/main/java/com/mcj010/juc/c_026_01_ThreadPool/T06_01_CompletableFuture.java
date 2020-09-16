package com.mcj010.juc.c_026_01_ThreadPool;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
/**
 * 假设你能够提供一个服务
 * 这个服务查询各大电商网站同一类产品的价格并汇总展示
 */

public class T06_01_CompletableFuture {
    public static void main(String[] args) throws InterruptedException {
        long start, end;

        start = System.currentTimeMillis();

        CompletableFuture<Double> futureTM = CompletableFuture.supplyAsync(() -> priceOfTM());//马上执行
        CompletableFuture<Double> futureTB = CompletableFuture.supplyAsync(() -> priceOfTB());
        CompletableFuture<Double> futureJD = CompletableFuture.supplyAsync(() -> priceOfJD());

         CompletableFuture.allOf(futureTM, futureTB, futureJD).join();//集中管理的作用,所有线程执行完毕
        //  CompletableFuture.anyOf(futureTM, futureTB, futureJD).join();//只要某一个形成执行完毕
        end = System.currentTimeMillis();
        System.out.println("use completable future! " + (end - start));


        CompletableFuture.supplyAsync(() -> priceOfTM())
                .thenApply(String::valueOf)
                .thenApply(str -> "price " + str)
                .thenAccept(System.out::println).join();

//        TimeUnit.SECONDS.sleep(2);

    }

    private static double priceOfTM() {
        delay();
        return 1.00;
    }

    private static double priceOfTB() {
        delay();
        return 2.00;
    }

    private static double priceOfJD() {
        delay();
        return 3.00;
    }


    private static void delay() {
        int time = new Random().nextInt(500);
        try {
            TimeUnit.MILLISECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("After %s sleep!\n", time);
    }
}
