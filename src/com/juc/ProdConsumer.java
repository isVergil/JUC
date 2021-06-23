package com.juc;

/**
 * @ClassName ProdConsumer
 * @Description TODO
 * @Author bill
 * @Date 2021/6/12 15:14
 * @Version 1.0
 * <p>
 * 题目：现在两个线程，可以操作初始值为零的一个变量，
 * 实现一个线程对该变量加1，一个线程对该变量-1，
 * 实现交替，来10轮，变量初始值为0.
 * 1.高内聚低耦合前提下，线程操作资源类
 * 2.判断/干活/通知  (通知唤醒)
 * 3.防止虚假唤醒 (判断只能用while，不能用if)  spurious wakeup  虚假唤醒
 * 知识小总结：多线程编程套路+while判断+新版写法
 * <p>
 * Q&A
 * q1：this.wait(); 会释放监视器？？？
 **/
public class ProdConsumer {
    public static void main(String[] args) {
        AirCondition airCondition = new AirCondition();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    airCondition.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "A+").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    airCondition.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "B-").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    airCondition.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "C+").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    airCondition.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "D-").start();
    }
}

class AirCondition {
    private int number = 0;

    public synchronized void increment() throws Exception {
        //判断
        while (number != 0)
            this.wait();
        //干活
        number++;
        System.out.println(Thread.currentThread().getName() + "\t" + number);
        //通知
        this.notifyAll();
    }

    public synchronized void decrement() throws Exception {
        //判断
        while (number == 0)
            this.wait();
        //干活
        number--;
        System.out.println(Thread.currentThread().getName() + "\t" + number);
        //通知
        this.notifyAll();
    }
}
