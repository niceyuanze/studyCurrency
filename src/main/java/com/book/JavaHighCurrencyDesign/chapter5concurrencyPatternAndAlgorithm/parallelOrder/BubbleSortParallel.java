package com.book.JavaHighCurrencyDesign.chapter5concurrencyPatternAndAlgorithm.parallelOrder;

import java.util.concurrent.CountDownLatch;

/**
 * Created by niceyuanze on 17-5-17.
 */
public class BubbleSortParallel {

    static int exchFlag = 1;

    static synchronized void setExchFlag(int v){
        exchFlag = v;
    }

    static synchronized int getExchFlag(){
        return exchFlag;
    }

    public static class OddEvenSortTask implements Runnable{

        int i;
        CountDownLatch latch;

        public OddEvenSortTask(int i, CountDownLatch latch) {
            this.i = i;
            this.latch = latch;
        }

        @Override
        public void run() {

        }
    }
}
