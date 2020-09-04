package com.mcj010.juc.c_020;

import java.util.concurrent.CountDownLatch;

public class T06_TestCountDownLatch {

	private static void usingCountDownLatch() {

		Thread[] threads = new Thread[100];
		CountDownLatch latch = new CountDownLatch(threads.length);

		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(() -> {

				int result = 0;
				for (int j = 0; j < 10000; j++) {
					result += j;
					System.out.println(result);
				}

				latch.countDown();//将count值减1
			});
		}

		for (int i = 0; i < threads.length; i++) {
			threads[i].start();
		}

		try {
			latch.await();//调用await()方法的线程会被挂起，它会等待直到count值为0才继续执行
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("end latch");

	}

	private static void usingJoin() {

		Thread[] threads = new Thread[100];

		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(() -> {
				int result = 0;
				for (int j = 0; j < 10000; j++)
					result += j;
			});
		}

		for (int i = 0; i < threads.length; i++) {
			threads[i].start();
		}

		for (int i = 0; i < threads.length; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("end join");
	}

	public static void main(String[] args) {
		usingJoin();
		usingCountDownLatch();
	}

}
