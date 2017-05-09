package com.book.JavaHighCurrencyDesign.chapterJDKCurrency;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by niceyuanze on 17-5-9.
 */
public class TimeLock implements Runnable{
    public static ReentrantLock lock = new ReentrantLock();


    public void run() {
        try {
            if(lock.tryLock(5, TimeUnit.SECONDS)){
                Thread.sleep(6000);
                System.out.println(Thread.currentThread().getName());
            }else{
                System.out.println(Thread.currentThread().getName()+"  "+"get lock failed");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            if(lock.isHeldByCurrentThread()){
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        TimeLock tl = new TimeLock();
        Thread t1 = new Thread(tl,"ttt1");

        Thread t2 = new Thread(tl,"ttt2");
        t1.start();
        t2.start();
    }
}
