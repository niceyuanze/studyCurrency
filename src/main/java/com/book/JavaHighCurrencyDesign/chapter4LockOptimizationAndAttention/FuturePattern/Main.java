package com.book.JavaHighCurrencyDesign.chapter4LockOptimizationAndAttention.FuturePattern;

/**
 * Created by niceyuanze on 17-5-17.
 */
public class Main {
    public static void main(String[] args) {
        Client client = new Client();
        Data data = client.request("name");
        System.out.println("请求完毕");
//        try{
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//        }
        System.out.println("数据 = " + data.getResult());
    }
}
