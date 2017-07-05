package com.book.JavaHighCurrencyDesign.chapter5concurrencyPatternAndAlgorithm.parallelOrder;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by niceyuanze on 17-5-17.
 */
public class BubbleSortParallel {

    static ExecutorService pool = Executors.newCachedThreadPool();
    static int[] arr;
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
            if(arr[i] > arr[i+1]){
                int temp = arr[i];
                arr[i] = arr[i+1];
                arr[i+1] = temp;
                setExchFlag(1);
            }
            latch.countDown();
        }
    }

    public static void pOddEvenSort(int[] arr) throws InterruptedException {
        int start = 0;
        while(getExchFlag() == 1 || start == 1){

            setExchFlag(0);
            CountDownLatch latch = new
                    CountDownLatch(arr.length / 2 - (arr.length%2==0?start:0));
            for(int i = start; i < arr.length - 1; i += 2){
                System.out.println(i);
                pool.submit(new OddEvenSortTask(i,latch));
            }
            latch.await();
            if(start == 0)
                start = 1;
            else
                start = 0;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        arr = new int[]{223, 4, 3, 356, 3456, 436, 3456, 3457, 234, 523};
        pOddEvenSort(arr);
        for(int x: arr){
            System.out.println(x);
        }
        pool.shutdown();
    }










}
