package com.book.JavaHighCurrencyDesign.chapterJDKCurrency;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by niceyuanze on 17-5-11.
 */
public class RejectThreadPoolDemo {

    public static AtomicInteger atomicInteger = new AtomicInteger();

    public static class MyTask implements Runnable{
        @Override
        public void run() {
            System.out.println(System.currentTimeMillis()
                    + ":Thread ID:"
                    + Thread.currentThread().getId() + "     "+atomicInteger.addAndGet(1));
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        MyTask task = new MyTask();

//        ExecutorService es = new ThreadPoolExecutor( 5,
//                5,
//                0L,
//                TimeUnit.MILLISECONDS,
//                new LinkedBlockingDeque<Runnable>(10),
//                Executors.defaultThreadFactory(),
//                (Runnable runnable, ThreadPoolExecutor threadPoolExecutor) ->{
////                    threadPoolExecutor.submit(runnable);
//                    System.out.println(runnable.toString()+" is discard");
//        });
//        for(int i = 0; i < Integer.MAX_VALUE;i++){
//            es.submit(task);
//            Thread.sleep(10);
//        }



        ExecutorService es = new ThreadPoolExecutor( 5,
                5,
                0L,
                TimeUnit.MILLISECONDS,
                new SynchronousQueue<Runnable>(),
                (Runnable r) -> {
                    Thread t = new Thread(r);
                    t.setDaemon(true);
                    System.out.println("create " + t);
                    return t;
                },
                (Runnable runnable, ThreadPoolExecutor threadPoolExecutor) ->{
                    threadPoolExecutor.submit(runnable);
                    System.out.println(runnable.toString()+" is discard");
        });

    }


}
