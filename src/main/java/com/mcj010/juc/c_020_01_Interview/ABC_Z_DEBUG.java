package com.mcj010.juc.c_020_01_Interview;

import java.util.concurrent.TimeUnit;

//两个线程交替打印1A2B3C....26Z
public class ABC_Z_DEBUG {
    public static void main(String[] args) {

        Object lock = new Object();

        new Thread(() -> {
            synchronized (lock) {
                for (int i = 1; i <= 26; i++) {
                    System.out.println(i);

                    try {
                        lock.notify();
                        System.out.println(Thread.currentThread().getName()+" into wait");
                        lock.wait();//只有进阻塞，才能释放锁
                        System.out.println(Thread.currentThread().getName()+" 唤醒");

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    lock.notify();//最后用来唤醒用
                }
            }

        }, "T1").start();

        new Thread(() -> {
            synchronized (lock) {
                for (char p = 'A'; p <= 'Z'; p++) {
                    System.out.println(p);
                    try {
                        lock.notify();
                        //TimeUnit.SECONDS.sleep(10);
                        System.out.println(Thread.currentThread().getName()+" into wait");
                        lock.wait();
                        System.out.println(Thread.currentThread().getName()+" 唤醒");

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //lock.notify();

                }
            }

        }, "T2").start();
    }
}
