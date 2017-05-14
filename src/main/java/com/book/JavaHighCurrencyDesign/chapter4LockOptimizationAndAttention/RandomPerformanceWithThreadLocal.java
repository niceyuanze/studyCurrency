package com.book.JavaHighCurrencyDesign.chapter4LockOptimizationAndAttention;

import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by niceyuanze on 17-5-12.
 */
public class RandomPerformanceWithThreadLocal {

    public static final int GEN_COUNT = 100000000;

    public static final int THREAD_COUNT = 4;

    static ExecutorService exe = Executors.newFixedThreadPool(THREAD_COUNT);

    public static Random rnd  = new Random(123);

    public static ThreadLocal<Random> tRnd = new ThreadLocal<Random>(){
        @Override
        protected Random initialValue() {
            return new Random(123);
        }
    };

    public static class RndTask implements Callable<Long>{

        private int mode = 0;

        public RndTask(int mode){
            this.mode = mode;
        }

        public Random getRandom(){
            if (mode == 0){
                return  rnd;
            } else if (mode == 1){
                return tRnd.get();
            } else {
                return null;
            }
        }


        @Override
        public Long call() throws Exception {
            long b = System.currentTimeMillis();
            for(long i = 0; i < GEN_COUNT; i++){
                getRandom().nextInt();
            }
            long e = System.currentTimeMillis();
            System.out.println(Thread.currentThread().getName()+
                    " spend "+(e - b) + "ms");
            return e - b;
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Future<Long>[] futs = new Future[THREAD_COUNT];
        for(int i = 0; i < THREAD_COUNT;i++){
            futs[i] = exe.submit(new RndTask(0));
        }
        long totaltime = 0;
        for(int i = 0; i < THREAD_COUNT; i++){
            totaltime += futs[i].get();
        }

        System.out.println("多线程访问同一个Random实例:"+totaltime+"ms");


        //ThreadLocal的情况
        for(int i = 0; i < THREAD_COUNT; i++){
            futs[i] = exe.submit(new RndTask(1));
        }
        totaltime = 0;
        for(int i = 0; i < THREAD_COUNT; i++){
            totaltime += futs[i].get();
        }
        System.out.println("使用ThreadLocal 包装实例:" + totaltime + "ms");
        exe.shutdown();
    }
}
