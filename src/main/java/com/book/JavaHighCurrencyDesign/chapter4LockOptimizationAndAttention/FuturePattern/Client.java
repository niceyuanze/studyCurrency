package com.book.JavaHighCurrencyDesign.chapter4LockOptimizationAndAttention.FuturePattern;

/**
 * Created by niceyuanze on 17-5-17.
 */
public class Client {

    public Data request(final String queryStr){

        final FutureData future = new FutureData();

        new Thread(){
            @Override
            public void run() {
                RealData realData = new RealData(queryStr);
                future.setRealData(realData);
            }
        }.start();

        return future;
    }

}
