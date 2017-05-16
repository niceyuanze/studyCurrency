package com.book.JavaHighCurrencyDesign;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

/**
 * Created by niceyuanze on 17-5-7.
 */
public class Test {
    private final static Integer x = 123;

    public static class XXX extends Thread{

        public XXX(String name) {
            super(name);
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName());
            System.out.println(x);
            synchronized (x){

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new XXX("t1");
        Thread t2 = new XXX("t2");
        t1.start();
        Thread.sleep(1000);
        t2.start();
        t1.join();
        t2.join();
    }
}
