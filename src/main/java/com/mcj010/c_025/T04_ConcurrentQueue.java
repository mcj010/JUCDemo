package com.mcj010.c_025;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class T04_ConcurrentQueue {
    public static void main(String[] args) {
        Queue<String> strs = new ConcurrentLinkedQueue<>();

        for (int i = 0; i < 10; i++) {
            strs.offer("a" + i);
        }
        //[a0, a1, a2, a3, a4, a5, a6, a7, a8, a9]
        System.out.println(strs);
        //10
        System.out.println(strs.size());
        //a0
        System.out.println(strs.poll());// 移除并返问队列头部的元素, 如果队列为空，则返回null
        //9
        System.out.println(strs.size());
        //a1
        System.out.println(strs.peek());//取但不会remove
        //9
        System.out.println(strs.size());
        //[a1, a2, a3, a4, a5, a6, a7, a8, a9]
        System.out.println(strs);
    }
}
