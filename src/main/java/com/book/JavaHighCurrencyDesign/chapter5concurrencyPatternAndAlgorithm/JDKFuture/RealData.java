package com.book.JavaHighCurrencyDesign.chapter5concurrencyPatternAndAlgorithm.JDKFuture;

import com.book.JavaHighCurrencyDesign.chapterJDKCurrency.ReenterLock;

import java.util.concurrent.*;

/**
 * Created by niceyuanze on 17-5-17.
 */
public class RealData implements Callable<String>{
    private String para;

    public RealData(String para) {
        this.para = para;
    }

    @Override
    public String call() throws Exception {
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < 10; i++){
            sb.append(para);
            try{
                Thread.sleep(100);
            }catch (InterruptedException e){
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> future = new FutureTask<String>(new RealData("a"));
        ExecutorService executor = Executors.newFixedThreadPool(1);
        executor.submit(future);

        System.out.println("执行完毕");
        try{
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }
        System.out.println("数据 = "+future.get());
    }
}
