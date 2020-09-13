package com.mcj010.c_025;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class T06_ArrayBlockingQueue {
	
	static BlockingQueue<String> queue = new ArrayBlockingQueue<String>(10);

	public static void main(String[] args) throws InterruptedException {
		
		for (int i = 0; i < 10; i++) {
			queue.put("a"+i);
		}
		
		queue.put("aaa");//满了阻塞
		//queue.add("aaa");// java.lang.IllegalStateException: Queue full
	//	System.out.println(queue.offer("aaaa"));//能插入就返回true,否则false
		//System.out.println(queue.offer("aaa", 1, TimeUnit.SECONDS));
		
		System.out.println(queue);
	}
}
