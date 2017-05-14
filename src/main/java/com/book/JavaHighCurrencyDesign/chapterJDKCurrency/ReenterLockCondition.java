package com.book.JavaHighCurrencyDesign.chapterJDKCurrency;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by niceyuanze on 17-5-9.
 */
public class ReenterLockCondition implements Runnable{

    public static ReentrantLock lock = new ReentrantLock();
    public static Condition condition = lock.newCondition();

    public void run() {
        try {
            lock.lock();
            condition.await();
            System.out.println("Thread is going on");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReenterLockCondition tl = new ReenterLockCondition();
        Thread t1 = new Thread(tl);
        t1.start();
        Thread.sleep(2000);
        lock.lock();
        condition.signal();
        Thread.sleep(2000);
        lock.unlock();
    }
}
