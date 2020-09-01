package com.mcj010.juc.c_018;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class T01_AtomicInteger {

	AtomicInteger count = new AtomicInteger(0);

	void m() {
		for (int i = 0; i < 10000; i++) {
			count.incrementAndGet();
		}
	}

	public static void main(String[] args) {

		T01_AtomicInteger t = new T01_AtomicInteger();

		List<Thread> threads = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			threads.add(new Thread(t::m, "thread-" + i));
		}

		threads.forEach((o) -> o.start());

		threads.forEach((o) -> {
			try {
				o.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		System.out.println(t.count);
	}
}
