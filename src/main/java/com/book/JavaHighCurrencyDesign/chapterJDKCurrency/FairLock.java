package com.book.JavaHighCurrencyDesign.chapterJDKCurrency;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by niceyuanze on 17-5-9.
 */
public class FairLock implements Runnable{


    //+true is the fair lock.
    public static ReentrantLock fairLock = new ReentrantLock();

    public void run() {
        while(true){
            fairLock.lock();
            System.out.println(Thread.currentThread().getName()+"获得锁");
            fairLock.unlock();
            //jhhhahhaha
        }
    }

    public static void main(String[] args) {
        FairLock r1 = new FairLock();
        Thread t1 = new Thread(r1,"Thread_t1");
        Thread t2 = new Thread(r1,"Thread_t2");
        t1.start();t2.start();

    }
}
