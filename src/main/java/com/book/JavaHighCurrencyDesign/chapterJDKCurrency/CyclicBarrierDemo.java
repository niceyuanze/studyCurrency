package com.book.JavaHighCurrencyDesign.chapterJDKCurrency;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by niceyuanze on 17-5-10.
 */
public class CyclicBarrierDemo {

    public static class Soldier implements Runnable{

        private String soldier;
        private final CyclicBarrier cyclicBarrier;

        public Soldier(String soldier, CyclicBarrier cyclicBarrier) {
            this.soldier = soldier;
            this.cyclicBarrier = cyclicBarrier;
        }

        public void run() {
            try {
                cyclicBarrier.await();
                doWork();
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }

        void doWork(){


//            try {
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

            System.out.println(soldier + ":任务完成");
        }
    }


    public static class BarrierRun implements Runnable{

        boolean flag;

        int N;

        public BarrierRun(boolean flag, int N) {
            this.flag = flag;
            this.N = N;
        }


        public void run() {
            if(flag ){
                System.out.println("司令:[士兵"+N+"个,任务完成");
            }else{
                System.out.println("司令:[士兵"+N+"个,集合完毕!");
                flag = true;
            }
        }
    }

    public static void main(String[] args) {
        final int N = 10;
        Thread[] allSoldier = new Thread[N];
        boolean flag = false;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(N, new BarrierRun(flag, N));
        System.out.println("集合队伍! ");

        for(int i = 0; i < N; i++){
            System.out.println("士兵 " +i+ " 报道!");
            allSoldier[i] = new Thread(new Soldier( "士兵 " + i,cyclicBarrier));
            allSoldier[i].start();
            if( i == 5){
                allSoldier[0].interrupt();
            }
        }
    }


}
