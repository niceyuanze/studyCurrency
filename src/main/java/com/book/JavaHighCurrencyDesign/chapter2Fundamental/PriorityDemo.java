package com.book.JavaHighCurrencyDesign.chapter2Fundamental;

/**
 * Created by niceyuanze on 17-5-7.
 */
public class PriorityDemo {

    public static class HightPriority extends Thread{

        static int count = 0;

        @Override
        public void run() {
            while (true){
                synchronized (PriorityDemo.class){
                    count++;
                    if(count > 10000000){
                        System.out.println("HightPriority is complete");
                        break;
                    }
                }
            }
        }
    }

    public static class LowPriority extends Thread{
        static int count = 0;

        @Override
        public void run() {
            while (true){
                synchronized (PriorityDemo.class){
                    count++;
                    if(count > 10000000){
                        System.out.println("LowPriority is complete");
                        break;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Thread high = new HightPriority();
        LowPriority lowPriority = new LowPriority();
        high.setPriority(Thread.MAX_PRIORITY);
        high.setPriority(Thread.MIN_PRIORITY);
        lowPriority.start();
        high.start();
    }
}
