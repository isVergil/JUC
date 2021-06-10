package com.juc;

/**
 * @ClassName LambdaExpress
 * @Description 拷贝小括号  写死右箭头  落地大括号
 * FunctionalInterface 注解
 * @Author bill
 * @Date 2021/6/10 13:18
 * @Version 1.0
 **/
public class LambdaExpress {
    public static void main(String[] args) {
        Foo foo = (int x, int y) -> {
            System.out.println(x + y);
            return x + y;
        };
        foo.say(3, 5);
        //接口中的 default 默认方法
        foo.mul(4, 5);
        foo.add(17, 18);
        //接口中的 static 静态方法
        Foo.div1(2, 1);
        Foo.div2(1, 2);
    }

}

@FunctionalInterface
interface Foo {
    //void say();
    int say(int x, int y);

    //default可以定义多个
    default int mul(int x, int y) {
        System.out.println(x * y);
        return x * y;
    }

    //default可以定义多个
    default int add(int x, int y) {
        System.out.println(x + y);
        return x + y;
    }

    //static可以定义多个
    static int div1(int x, int y) {
        System.out.println(x / y);
        return x / y;
    }

    //static可以定义多个
    static int div2(int y, int x) {
        System.out.println(x / y);
        return x / y;
    }

}
