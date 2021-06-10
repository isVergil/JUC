package com.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName SaleTicket
 * @Description 三个售票员 卖出 30 张票
 * @Author bill
 * @Date 2021/6/10 10:12
 * @Version 1.0
 * 1 高内聚 低耦合的前提下  线程 操作 资源类
 * Runnable 是函数式接口因此可以省略直接()->  函数式接口参见 LambdaExpress.java
 **/
public class SaleTicket {

    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        //Runnable 是函数式接口因此可以省略直接()->  函数式接口参见 LambdaExpress.java
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i <= 40; i++) {
//                    ticket.sale();
//                }
//            }
//        }, "A").start();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i <= 40; i++) {
//                    ticket.sale();
//                }
//            }
//        }, "B").start();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i <= 40; i++) {
//                    ticket.sale();
//                }
//            }
//        }, "C").start();

        new Thread(() -> {
            for (int i = 0; i <= 40; i++) ticket.sale();
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i <= 40; i++) ticket.sale();
        }, "B").start();
        new Thread(() -> {
            for (int i = 0; i <= 40; i++) ticket.sale();
        }, "C").start();
    }

}

class Ticket {
    private int number = 30;
    Lock lock = new ReentrantLock();

    public void sale() {
        lock.lock();
        try {
            if (number > 0)
                System.out.println(Thread.currentThread().getName() + "卖出第：" + (number--) + "还剩下" + number);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
