package com.book.JavaHighCurrencyDesign.chapter2Fundamental;

/**
 * Created by niceyuanze on 17-5-6.
 */
public class InterruptThreadSleep {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(){
            @Override
            public void run() {

                while(true){
                    if(Thread.currentThread().isInterrupted()){
                        System.out.println("zzzz");
                        System.out.println("Interruted");
                        break;
                    }
                    try {
                        Thread.sleep(2000);
                    }catch (InterruptedException e){
                        System.out.println("Interruted When Sleep");
                        System.out.println("xxxx");
//                    Thread.currentThread().interrupt();
                    }
                    Thread.yield();
                }


            }
        };

        t1.start();
        Thread.sleep(2000);
        t1.interrupt();
    }
}
