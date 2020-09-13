package com.mcj010.c_025;

import java.util.concurrent.LinkedTransferQueue;

public class T09_TransferQueue {

	public static void main(String[] args) throws InterruptedException {
		LinkedTransferQueue<String> queue = new LinkedTransferQueue<String>();

		new Thread(() -> {
			try {
				System.out.println(queue.take());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();
		
		queue.transfer("aaa");
	}

}
