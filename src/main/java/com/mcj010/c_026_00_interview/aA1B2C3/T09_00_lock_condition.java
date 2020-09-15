package com.mcj010.c_026_00_interview.aA1B2C3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class T09_00_lock_condition {

    public static void main(String[] args) {

        Lock lock = new ReentrantLock();
        Condition conditionT1 = lock.newCondition();
        Condition conditionT2 = lock.newCondition();
        //T1
        new Thread(() -> {
            try {
                lock.lock();
                for (int i = 1; i <= 26; i++) {
                    System.out.print(i);
                    conditionT2.signal();
                    conditionT1.await();
                }
                conditionT2.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        },"t1").start();

        //T2
        new Thread(() -> {
            try {
                lock.lock();
                for (char i = 'A'; i <= 'Z'; i++) {
                    System.out.print(i);
                    conditionT1.signal();
                    conditionT2.await();
                }
                conditionT1.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }


        },"t2").start();
    }
}
