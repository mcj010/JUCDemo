package com.mcj010.juc.c_018;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

public class T02_AtomicVsSyncVsLongAdder {

	static AtomicLong count1 = new AtomicLong(0L);
	static long count2 = 0L;
	static LongAdder count3 = new LongAdder();

	public static void main(String[] args) throws Exception {
		Thread[] threads = new Thread[1000];

		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(() -> {
				for (int k = 0; k < 100000; k++)
					count1.incrementAndGet();// atomicLong
			});
		}

		long start = System.currentTimeMillis();

		for (Thread t : threads)
			t.start();

		for (Thread t : threads)
			t.join();

		long end = System.currentTimeMillis();

		System.out.println("Atomic: " + count1.get() + " time " + (end - start));

		// -----------------------------------------------------------------------------

		Object lock = new Object();

		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(new Runnable() {
				@Override
				public void run() {
					for (int j = 0; j < 100000; j++) {
						synchronized (lock) {
							count2++;
						}
					}

				}
			});
		}

		start = System.currentTimeMillis();

		for (Thread t : threads)
			t.start();

		for (Thread t : threads)
			t.join();

		end = System.currentTimeMillis();

		System.out.println("Sync: " + count2 + " time " + (end - start));
		
		//--------------------------------------------------------------------------------
		
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(()->{
				for (int j = 0; j < 100000; j++) count3.increment();
			});
		}
		
        start = System.currentTimeMillis();

        for(Thread t : threads ) t.start();

        for (Thread t : threads) t.join();

        end = System.currentTimeMillis();

        //TimeUnit.SECONDS.sleep(10);

        System.out.println("LongAdder: " + count3.longValue() + " time " + (end-start));
		
	}
}
