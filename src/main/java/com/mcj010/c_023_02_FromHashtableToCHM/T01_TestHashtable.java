package com.mcj010.c_023_02_FromHashtableToCHM;

import java.util.Hashtable;
import java.util.UUID;

public class T01_TestHashtable {
    static Hashtable<UUID, UUID> m = new Hashtable<>();
    //1 million
    static int count = Constants.COUNT;
    static UUID[] keys = new UUID[count];
    static UUID[] values = new UUID[count];

    static final int THREAD_COUNT = Constants.THREAD_COUNT;    //100

    //init
    static {
        //生成1百万的数据
        for (int i = 0; i < count; i++) {
            keys[i] = UUID.randomUUID();
            values[i] = UUID.randomUUID();
        }
    }

    static class MyThread extends Thread {
        int start;
        int gap = count / THREAD_COUNT; //1百万除100=10000

        public MyThread(int start) {
            this.start = start;
        }

        @Override
        public void run() {
            for (int i = start; i < start + gap; i++) {
                m.put(keys[i], values[i]);
            }
        }
    }

    public static void main(String[] args) {

        long start = System.currentTimeMillis();
        //100个线程
        Thread[] threads = new Thread[THREAD_COUNT];

        //开启100个线程，每个线程插入1W 条数据
        for (int i = 0; i < threads.length; i++) {
            //0,10000,20000
            threads[i] = new MyThread(i * (count / THREAD_COUNT));
        }

        for (Thread t : threads) {
            t.start();
        }

        for (Thread t : threads) {
            try {
                t.join();//join 线程，阻塞main线程
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long end = System.currentTimeMillis();
        System.out.println(end - start);
        System.out.println(m.size());

        //---------------------------------------------------

        //开启100个线程，每个线程读Hashtable 1000W 次
        start = System.currentTimeMillis();
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 10000000; j++) {
                    m.get(keys[0]);
                }
            });
        }

        for (Thread t : threads) {
            t.start();
        }

        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        end = System.currentTimeMillis();
        System.out.println(end - start);

    }
}
