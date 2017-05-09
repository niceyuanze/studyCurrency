package com.book.JavaHighCurrencyDesign.chapter2Fundamental;

/**
 * Created by niceyuanze on 17-5-7.
 */
public class AccountingVol implements Runnable{
    static AccountingVol instance = new AccountingVol();
    static volatile int i = 0;

    public static synchronized void increate(){
            i++;
    }


    public void run() {
        for(int j = 0; j < 10000000; j++){
            increate();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new AccountingVol());
        Thread t2 = new Thread(new AccountingVol());
        t1.start();t2.start();
        t1.join();t2.join();
        System.out.println(i);
    }
}
