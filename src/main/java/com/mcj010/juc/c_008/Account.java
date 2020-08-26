package com.mcj010.juc.c_008;

import java.util.concurrent.TimeUnit;

public class Account {

	String name;
	double balance;

	public synchronized void set(String name, double balance) {
		this.name = name;

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		this.balance = balance;
	}

	public synchronized double getBalance(String name) {
		System.out.print(name + " ");
		return this.balance;
	}

	public static void main(String[] args) {

		Account a = new Account();

		new Thread(() -> a.set("sz", 100.0)).start();

		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}

		System.out.println(a.getBalance("sz"));

		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}

		System.out.println(a.getBalance("sz"));

	}

}
