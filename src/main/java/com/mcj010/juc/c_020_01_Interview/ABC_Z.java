package com.mcj010.juc.c_020_01_Interview;

//两个线程交替打印1A2B3C....26Z
public class ABC_Z {
    public static void main(String[] args) {

        Object lock = new Object();

        new Thread(() -> {
            synchronized (lock) {
                for (int i = 1; i <= 26; i++) {
                    System.out.print(i);

                    try {
                        lock.notify();
                        lock.wait();

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    lock.notify();
                }
            }

        }, "T1").start();

        new Thread(() -> {
            synchronized (lock) {
                for (char p = 'A'; p <= 'Z'; p++) {
                    System.out.print(p);
                    try {
                        lock.notify();
                        lock.wait();

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    lock.notify();

                }
            }

        }, "T2").start();
    }
}
