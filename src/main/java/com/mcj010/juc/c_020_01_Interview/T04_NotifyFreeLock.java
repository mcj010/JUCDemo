package com.mcj010.juc.c_020_01_Interview;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class T04_NotifyFreeLock {

	// 添加volatile，使t2能够得到通知
	// volatile
	List lists = new ArrayList();

	public void add(Object o) {
		lists.add(o);
	}

	public int size() {
		return lists.size();
	}

	public static void main(String[] args) {
		T04_NotifyFreeLock c = new T04_NotifyFreeLock();

		Object lock = new Object();
		new Thread(() -> {

			synchronized (lock) {
				System.out.println("T2启动");

				if (c.size() != 5) {
					try {
						lock.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

				System.out.println("T2结束");
				
				lock.notify();
			}

		}, "t2").start();
		new Thread(() -> {
			
			System.out.println("t1启动");
			
			synchronized (lock) {
				for (int i = 0; i < 10; i++) {
					c.add(new Object());
					System.out.println("add " + i);

					if (c.size() == 5) {
						lock.notify();//释放锁，让t2得以执行

						try {
							lock.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}

					try {
						TimeUnit.SECONDS.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
				System.out.println("t1结束");
			}
		}, "t1").start();

		

	}
}
