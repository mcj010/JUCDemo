package com.mcj010.c_025;

import java.util.PriorityQueue;

public class T07_01_PriorityQueque {
	public static void main(String[] args) {
		PriorityQueue<String> q = new PriorityQueue<String>();
		
		q.add("c");
		q.add("e");
		q.add("a");
		q.add("d");
		q.add("z");
		q.add("1");
		
		for (int i = 0; i < 6; i++) {
			System.out.println(q.poll());
			
		}
	}

}
