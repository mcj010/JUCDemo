package com.mcj010.juc.c_020;

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

public class T08_TestPhaser {

	static Random r = new Random();
	static MarriagePhaser phaser = new MarriagePhaser();

	static void millSleep(int milli) {
		try {
			TimeUnit.MILLISECONDS.sleep(milli);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	static class MarriagePhaser extends Phaser {

		protected boolean onAdvance(int phase, int registeredParties) {

			switch (phase) {
			case 0:
				System.out.println("所有人到齐了！");
				return false;//返回false ，阻塞后面
			case 1:
				System.out.println("所有人吃完了！");
				return false;

			case 2:
				System.out.println("所有人离开了！");
				System.out.println("婚礼结束！");
				return true;//返回 true, 不再阻塞
			default:
				return true;
			}

		}
	}

	static class Person {
		String name;

		public Person(String name) {
			this.name = name;
		}

		public void arrive() {
			millSleep(r.nextInt(1000));
			System.out.printf("%s 到达现场！\n", name);
		}

		public void eat() {
			millSleep(r.nextInt(1000));
			System.out.printf("%s 吃完!\n", name);
		}

		public void leave() {
			millSleep(r.nextInt(1000));
			System.out.printf("%s 离开！\n", name);
		}
	}

	public static void main(String[] args) {
		phaser.bulkRegister(5);//注册5个
		
		for (int i = 0; i < 5; i++) {
			final int nameIndex = i ;
			
			new Thread(()->{
				Person p = new Person("person"+nameIndex);
				p.arrive();
				phaser.arriveAndAwaitAdvance();
				
				p.eat();
				phaser.arriveAndAwaitAdvance();
				
				p.leave();
				phaser.arriveAndAwaitAdvance();
			}).start();
		}
	}
}
