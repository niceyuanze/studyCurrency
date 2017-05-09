package com.book.JavaHighCurrencyDesign.chapter2Fundamental;

/**
 * Created by niceyuanze on 17-5-7.
 */
public class DaemonDemo {

    public static class DaemonT extends Thread{
        @Override
        public void run() {
            while(true){
                System.out.println("i am alive");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        Thread t = new DaemonT();
        t.setDaemon(true);
        t.start();
    }




}
