package com.mcj010.juc.c_022_RefTypeAndThreadLocal;

import java.io.IOException;

/**
 * @author zenguitar
 * 强引用，当没有引用的时候，遇到gc()就会被清理。否则即使内存溢出，也不会被回收。
 */
public class T01_NormalReference {

    public static void main(String[] args) throws IOException {
        M m = new M();
        m = null;
        System.gc();
        System.in.read();
    }
}
