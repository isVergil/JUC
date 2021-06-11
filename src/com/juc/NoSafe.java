package com.juc;


import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @ClassName NoSafe
 * @Description TODO
 * @Author bill
 * @Date 2021/6/10 14:03
 * @Version 1.0
 * List  Set  Map 线程不安全  会引起并发修改异常 java.util.ConcurrentModificationException
 * 原因：多线程并发争抢同一个资源， 且没加锁🔒
 * 解决方法：
 * 1、new Vector<>();   （使用 synchronized 关键字来修改数据）
 * 2、Collections.synchronizedList(new ArrayList<>());
 * 3、new CopyOnWriteArrayList<>();  写时复制技术 ArrayList  读写操作分离（乐观锁？？？） add加了锁
 **/
public class NoSafe {
    public static void main(String[] args) {
        //Collections 是一个工具类 Collections 带 synchronized 的都不安全(map,set,arraylist)
        List<String> list = new CopyOnWriteArrayList<>();//Collections.synchronizedList(new ArrayList<>()); //new Vector<>();
        for (int i = 0; i <= 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }

    //List 不安全
    public static void listNotSafe() {
        //new CopyOnWriteArrayList<>()
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }).start();
        }
    }

    //Set 不安全
    public static void setNotSafe() {
        //new CopyOnWriteArraySet<>()
        Set<String> set = new HashSet<>();
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(set);
            }).start();
        }
    }

    //Map 不安全
    public static void mapNotSafe() {
        //new ConcurrentHashMap<>()
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0, 8));
                System.out.println(map);
            }).start();
        }
    }
}