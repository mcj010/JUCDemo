package com.mcj010.c_025;

import java.util.Arrays;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class T01_ConcurrentMap {

    public static void main(String[] args) {
        Map<String, String> map = new ConcurrentHashMap<>();

        Random r = new Random();
        Thread[] ths = new Thread[100];
        CountDownLatch latch = new CountDownLatch(ths.length);

        long start = System.currentTimeMillis();

        for (int i = 0; i < ths.length; i++) {
            ths[i] = new Thread(() -> {
                //每个线程生成1W数据
                for (int j = 0; j < 10000; j++) {
                    map.put("a" + r.nextInt(100000), "a" + r.nextInt(100000));//重复的话就会少了
                }
                latch.countDown();
            });
        }

        Arrays.asList(ths).forEach(t -> t.start());

        try {
            latch.await();//阻塞main线程，等待上面执行完毕解锁
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        System.out.println(end - start);
        System.out.println(map.size());


    }
}
