package com.book.JavaHighCurrencyDesign.chapter5concurrencyPatternAndAlgorithm.ProducerAndConsumerDesignPattern;

/**
 * Created by niceyuanze on 17-5-16.
 */
public class FalseSharing implements Runnable{

    public final static int NUM_THREADS = 4;
    public final static long ITERATIONS = 500L * 1000L * 1000L;
    private final int arrayIndex;
    private static VolatileLong[] longs = new VolatileLong[NUM_THREADS];


    static {
        for(int i = 0; i < longs.length; i++){
            longs[i] = new VolatileLong();
        }
    }

    public final static class VolatileLong {
        public volatile long value = 0L;
//        public long p1, p2, p3, p4, p5, p6, p7;
    }

    public FalseSharing(int arrayIndex) {
        this.arrayIndex = arrayIndex;
    }
//duration = 12300
    public static void main(String[] args) throws InterruptedException {
        final long start = System.currentTimeMillis();
        runTest();
        System.out.println("duration = " +
                (System.currentTimeMillis() - start));
    }



    private static void runTest() throws InterruptedException {
        Thread[] threads = new Thread[NUM_THREADS];

        for(int i = 0; i < threads.length;i++){
            threads[i] = new Thread(new FalseSharing(i));
        }
        for(Thread t : threads){
            t.start();
        }
        for(Thread t : threads){
            t.join();
        }
    }





    @Override
    public void run() {
        long i = ITERATIONS + 1;
        while( 0 != --i){
            longs[arrayIndex].value = i;
        }
    }


}
