package com.book.JavaHighCurrencyDesign.chapter4LockOptimizationAndAttention.FuturePattern;

/**
 * Created by niceyuanze on 17-5-17.
 */
public class RealData {

    protected final String result;

    public RealData(String para){

        StringBuffer sb = new StringBuffer();

        for(int i = 0; i < 10; i++){
            sb.append(para);
            try {
                Thread.sleep(100);
            }catch (InterruptedException e){

            }
        }

        result = sb.toString();
    }
}
