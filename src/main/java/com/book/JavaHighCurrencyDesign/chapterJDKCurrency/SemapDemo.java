package com.book.JavaHighCurrencyDesign.chapterJDKCurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by niceyuanze on 17-5-9.
 */
public class SemapDemo implements Runnable{

    final Semaphore semp = new Semaphore(5);


    public void run() {
        try {
            semp.acquire();
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getId()+":done");
            semp.release();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        ExecutorService exec = Executors.newFixedThreadPool(20);
        final SemapDemo demo = new SemapDemo();
        for(int i = 0; i < 20; i++){
            exec.submit(demo);
        }
    }
}
