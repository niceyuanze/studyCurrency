package com.book.JavaHighCurrencyDesign.chapter5concurrencyPatternAndAlgorithm.parallelPipeline;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by niceyuanze on 17-5-17.
 */
public class Plus implements Runnable{

    public static BlockingQueue<Msg> bq = new LinkedBlockingDeque<>();


    @Override
    public void run() {
        while(true){
            try{
                Msg msg = bq.take();
                msg.j = msg.j + msg.j;
                Multiply.bq.add(msg);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
