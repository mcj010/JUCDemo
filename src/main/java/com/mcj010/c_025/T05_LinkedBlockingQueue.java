package com.mcj010.c_025;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class T05_LinkedBlockingQueue {
	
	static BlockingQueue<String> queue = new LinkedBlockingQueue<>();

    static Random r = new Random();

    public static void main(String[] args) {

    	new Thread(()->{
    		for (int i = 0; i < 100; i++) {
    			
    			try {
					queue.put("a" + i);//如果满了，就会阻塞
					TimeUnit.MILLISECONDS.sleep(1000);
				} catch (InterruptedException e) {
				
					e.printStackTrace();
				}
				
			}
    		
    	},"p1").start();
    	
    	for (int i = 0; i < 5; i++) {
    		new Thread(()->{
    			for(;;) {
        			try {
    					System.out.println(Thread.currentThread().getName() + "take -" + queue.take());//如果空了，就会阻塞
    				} catch (InterruptedException e) {
    			
    					e.printStackTrace();
    				}
        		}
    		}).start();
    		
		}
    }
}
