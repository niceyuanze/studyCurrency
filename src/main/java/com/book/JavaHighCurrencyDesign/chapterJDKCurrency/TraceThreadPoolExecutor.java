package com.book.JavaHighCurrencyDesign.chapterJDKCurrency;

import java.util.concurrent.*;

/**
 * Created by niceyuanze on 17-5-11.
 */
public class TraceThreadPoolExecutor extends ThreadPoolExecutor{

    public static void main(String[] args) {
        ThreadPoolExecutor pools =
                new TraceThreadPoolExecutor(0, Integer.MAX_VALUE,
                        0L,TimeUnit.SECONDS,
                        new SynchronousQueue<Runnable>());
//        ThreadPoolExecutor pools = new ThreadPoolExecutor(0, Integer.MAX_VALUE,
//                0l,TimeUnit.SECONDS,
//                new SynchronousQueue<Runnable>());
        for(int i = 0; i < 5; i++){
            pools.execute(new DivTask(100, i));
        }
    }
/*
Exception in thread "pool-1-thread-1" java.lang.ArithmeticException: / by zero
	at com.book.JavaHighCurrencyDesign.chapterJDKCurrency.TraceThreadPoolExecutor$DivTask.run(TraceThreadPoolExecutor.java:36)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1142)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:617)
	at java.lang.Thread.run(Thread.java:745)

 */

    public static class DivTask implements Runnable{

        int a, b;

        public DivTask(int a, int b){
            this.a = a;
            this.b = b;
        }

        @Override
        public void run() {

            double re = a / b;
            System.out.println(re);

        }
    }


    public TraceThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    @Override
    public void execute(Runnable task) {
        super.execute( wrap(task, clientTrace(),Thread.currentThread().getName()));
    }

    @Override
    public Future<?> submit(Runnable task) {
        return super.submit( wrap(task, clientTrace(), Thread.currentThread().getName()));
    }

    private Exception clientTrace(){
        return new Exception("Client stack trace");
    }



    private Runnable wrap(final Runnable task, final Exception clientStack,
                          String clientThreadName){
        return () ->{
            try{
                task.run();

            }catch (Exception e){
                clientStack.printStackTrace();
                throw e;
            }
        };
    }
}
