package com.mcj010.juc.c_020_01_Interview;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class T05_CountDownLatch {

	volatile List lists = new ArrayList<>();

	public void add(Object o) {
		lists.add(o);
	}

	public int size() {
		return lists.size();
	}

	public static void main(String[] args) {

		T05_CountDownLatch c = new T05_CountDownLatch();
		CountDownLatch latch = new CountDownLatch(1);//初始化值为1

		// t2
		new Thread(() -> {

			System.out.println("t2开始");

			if (c.size() != 5) {
				try {
					latch.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("T2结束");
		}, "t2").start();
//		try {
//			TimeUnit.SECONDS.sleep(1);
//		} catch (InterruptedException e1) {
//			e1.printStackTrace();
//		}

		// t1
		new Thread(new Runnable() {

			@Override
			public void run() {

				System.out.println("T1开始");

				for (int i = 0; i < 10; i++) {
					c.add(new Object());
					System.out.println("add " + i);

					if (c.size() == 5) {
						// 打开门闩，让t2得以执行
						latch.countDown();
					}
					
					try {
						TimeUnit.SECONDS.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

				

			}
		}, "t1").start();
		;

	}
}
