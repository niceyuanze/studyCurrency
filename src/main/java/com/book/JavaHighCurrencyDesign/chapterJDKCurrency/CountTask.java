package com.book.JavaHighCurrencyDesign.chapterJDKCurrency;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * Created by niceyuanze on 17-5-11.
 */
public class CountTask extends RecursiveTask<Long>{

    private static final int THRESHOLD = 10;

    private long  start;

    private long end;

    public CountTask(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public Long compute(){

        long sum = 0;
        boolean canCompute = (end - start + 1) < THRESHOLD;
        if(canCompute){
            System.out.println("start:" + start + "   end:"+end);
            for(long i = start; i <= end; i++){
                sum+=i;
            }

        }else{
            long step = (end - start) / 10;
            ArrayList<CountTask> subTasks = new ArrayList<CountTask>();
            long pos = start;
            for(int i = 0; i < 10; i++){
                long lastOne = pos + step;
                System.out.println("    pos:"+pos+"     lastOne:"+lastOne );
                if(lastOne > end) lastOne = end;
                CountTask subTask = new CountTask(pos, lastOne);
                pos += step + 1;
                subTasks.add(subTask);
                subTask.fork();
            }
            for(CountTask t: subTasks){
                sum+=t.join();
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        CountTask task = new CountTask(11, 51L);
        ForkJoinTask<Long> result = forkJoinPool.submit(task);
        try {
            long res = result.get();
            System.out.println("sum="+res);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }


}
