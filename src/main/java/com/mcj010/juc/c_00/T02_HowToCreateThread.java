package com.mcj010.juc.c_00;

public class T02_HowToCreateThread {
	
	static class MyThread extends Thread{
		public void run() {
			System.out.println("Hello MyTread");
		}
	}
	
	static class MyRun implements Runnable{
		public void run() {
			System.out.println("Hello MyRun");
		}
	}
	
	public static void main(String[] args) {
		new MyThread().start();
		new Thread(new MyRun()).start();
		new Thread(()->{
			System.out.println("Hello Lambda!");
		}).start();
	}

}
