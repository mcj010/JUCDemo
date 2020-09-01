package com.mcj010.juc.c_020;

import java.util.concurrent.TimeUnit;

public class T01_ReentrantLock1 {

	synchronized void m1() {
		for (int i = 0; i < 10; i++) {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(i);
			if(i ==2) m2();
		}
	}
	
	synchronized void m2() {
		System.out.println("m2 ...");
	}
	
	public static void main(String[] args) {
		T01_ReentrantLock1 r = new T01_ReentrantLock1();
		new Thread(r::m1).start();
		
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		new Thread(r::m2).start();
	}
}
