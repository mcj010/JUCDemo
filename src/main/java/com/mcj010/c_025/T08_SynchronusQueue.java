package com.mcj010.c_025;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class T08_SynchronusQueue {
	public static void main(String[] args) throws InterruptedException {
		BlockingQueue<String> queue = new SynchronousQueue<>();

		new Thread(() -> {
			try {
				//System.out.println(queue.take());
				queue.put("bbb");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();

		//queue.put("aaa");

		System.out.println(queue.take());
		// queue.add("aaa");
		System.out.println(queue.size());
	}

}
