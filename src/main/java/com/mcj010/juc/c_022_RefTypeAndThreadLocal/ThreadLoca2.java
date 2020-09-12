package com.mcj010.juc.c_022_RefTypeAndThreadLocal;

import java.util.concurrent.TimeUnit;

public class ThreadLoca2 {

	static ThreadLocal<Person> tl = new ThreadLocal<Person>();

	class Person {
		String name = "zhangsan";
	}

	public static void main(String[] args) {

		new Thread(() -> {
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			System.out.println(tl.get());
		}).start();

		new Thread(() -> {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			tl.set(new ThreadLoca2().new Person());
			//System.out.println(tl.get());
		}).start();

	}

}
