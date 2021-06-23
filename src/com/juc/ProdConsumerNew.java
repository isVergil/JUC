package com.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName ProdConsumerNew
 * @Description TODO
 * @Author bill
 * @Date 2021/6/12 23:07
 * @Version 1.0
 * **********************
 * 可重入递归非公平锁
 * 公平：先来后到
 * 非公平：可以加塞
 **/
public class ProdConsumerNew {
}


class AirConditionNew {
    private int number = 0;
    private Lock lock = new ReentrantLock();

    public void increment() throws Exception {
        lock.lock();
        try {
            //判断
            while (number != 0)
                this.wait();
            //干活
            number++;
            System.out.println(Thread.currentThread().getName() + "\t" + number);
            //通知
            this.notifyAll();
        } finally {
            lock.unlock();
        }


    }

    public void decrement() throws Exception {
        //判断
        if (number == 0)
            this.wait();
        //干活
        number--;
        System.out.println(Thread.currentThread().getName() + "\t" + number);
        //通知
        this.notifyAll();
    }
}