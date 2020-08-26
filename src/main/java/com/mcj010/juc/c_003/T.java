package com.mcj010.juc.c_003;

public class T {

	private int count = 10;


	public synchronized void m() {
	
			count--;
			System.out.println(Thread.currentThread().getName() + " count=" + count);
	
	}
	
}
