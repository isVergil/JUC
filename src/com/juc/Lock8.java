package com.juc;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName Lock8
 * @Description TODO
 * @Author bill
 * @Date 2021/6/11 16:17
 * @Version 1.0
 * <p>
 * 八锁 lock
 * 笔记： https://blog.csdn.net/lizongxiao/article/details/106668806
 * 1、同一时间段只允许一个线程进入资源类进行访问其中的一个 synchronized 方法 （synchronized 锁的是当前对象 this 俗称对象锁）
 * 2、普通方法和同步锁无关 锁的是当前实例对象 synchronized(this){}
 * 3、静态同步锁  是全局锁 锁整个全局类   Class.forName();  Class 对象
 * 4、静态同步方法用的是同一个锁---类对象本身  跟非静态同步方法之间无关  他们两个是不一样的锁
 **/
public class Lock8 {
    public static void main(String[] args) throws Exception {
        Phone phone = new Phone();
        Phone phone1 = new Phone();
        new Thread(() -> {
            try {
                //phone.sendEmail();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "A").start();

        new Thread(() -> {
            try {
                //phone.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "B").start();

        new Thread(() -> {
            try {
                //phone.sayHello();
                Phone.sendEmail();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "C").start();

        new Thread(() -> {
            try {
                //phone1.sendSMS();
                Phone.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "D").start();

    }
}

class Phone {
    //因为synchronized的锁是this  对象锁
    public static synchronized void sendEmail() throws Exception {
        TimeUnit.SECONDS.sleep(4);
        System.out.println("*********sendEmail");
    }

    public static synchronized void sendSMS() throws Exception {
        System.out.println("*********sendSMS");
    }

    public  void sayHello() throws Exception {
        System.out.println("*********sayHello");
    }
}