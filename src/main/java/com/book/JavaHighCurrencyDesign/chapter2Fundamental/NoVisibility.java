package com.book.JavaHighCurrencyDesign.chapter2Fundamental;


/**
 * Created by niceyuanze on 17-5-6.
 */
public class NoVisibility {

    private static  boolean ready = false;

    private static int number;

    private static class ReaderThread extends Thread{

        public void run(){
            while(!ready);
            System.out.println("123123123");
            System.out.println(number);
        }
    }

    public static void main(String[] args) throws InterruptedException {

        new ReaderThread().start();
        Thread.sleep(1000);
        number = 42;
        ready = true;
        Thread.sleep(10000);
    }
}
