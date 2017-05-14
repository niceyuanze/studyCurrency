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
    private static ReentrantLock reentrantLock = new ReentrantLock();
    private static Condition condition = reentrantLock.newCondition();

    public static class Thread1 extends Thread{
        @Override
        public void run() {
            try {
                reentrantLock.lock();
                System.out.println("This is the thread 1");
                condition.await();
                System.out.println("aaaaaaa");
            } catch (InterruptedException e) {
                System.out.println("1111111111");
                condition.signal();
                System.out.println("12312werfasdfw");
                e.printStackTrace();
            }
        }
    }

    public static class Thread2 extends Thread{
        @Override
        public void run() {
            try {
                reentrantLock.lockInterruptibly();
                System.out.println("222222222221111");
                condition.await();
                System.out.println("2222222222");
            } catch (InterruptedException e) {
                System.out.println("????????????");
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread1();
        Thread thread2 = new Thread2();
        thread1.start();
        thread1.interrupt();
        Thread.sleep(1000);
        thread2.start();
//        Thread.sleep(1000);
//        reentrantLock.lock();
//        condition.signalAll();
//        reentrantLock.unlock();
    }
}
