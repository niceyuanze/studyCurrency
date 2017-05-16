package com.book.JavaHighCurrencyDesign.chapter5concurrencyPatternAndAlgorithm.ProducerAndConsumerDesignPattern;

import java.text.MessageFormat;
import java.util.Random;
import java.util.concurrent.BlockingQueue;

/**
 * Created by niceyuanze on 17-5-16.
 */
public class Consumer implements Runnable{

    private BlockingQueue<PCData> queue;

    private static final int SLEEPTIME = 1000;


    public Consumer(BlockingQueue<PCData> queue) {
        this.queue = queue;
    }

    public BlockingQueue<PCData> getQueue() {
        return queue;
    }

    @Override
    public void run() {
        System.out.println("start Consumer id="
                +Thread.currentThread().getId());
        Random r = new Random();
        try{
            while(true){
                PCData pcData = queue.take();
                if(null != pcData){
                    int re = pcData.getIntData() * pcData.getIntData();
                    System.out.println(MessageFormat.format("{0}*{1}={2}",
                            pcData.getIntData(),pcData.getIntData(),re));
                    Thread.sleep(r.nextInt(SLEEPTIME));
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
}
