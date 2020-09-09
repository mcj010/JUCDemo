package com.mcj010.juc.c_022_RefTypeAndThreadLocal;

import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

public class WeakHashMapTest {

    public static void weakHashMapTest() {
        Integer key = new Integer(1);
        String value = "李四";

        Map<Integer, String> weakHashMap = new WeakHashMap();
        weakHashMap.put(key, value);

        System.out.println(weakHashMap); //{1=李四}

        key = null;
        System.gc();
        System.out.println(weakHashMap);
    }

    public static void hashMapTest(){
        Integer key = new Integer(1);
        String value = "张三";

        Map<Integer, String> map = new HashMap();
        map.put(key, value);

        System.out.println(map);

        key = null;
        System.gc();
        System.out.println(map);
    }

    public static void main(String[] args) {
        weakHashMapTest();
        hashMapTest();
    }
}
