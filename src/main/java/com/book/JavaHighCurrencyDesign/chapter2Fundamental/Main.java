package com.book.JavaHighCurrencyDesign.chapter2Fundamental;

/**
 * Created by niceyuanze on 17-5-6.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        try{
            throw new NullPointerException();
        }catch (Exception e){
            System.out.println("1");
        }
        System.out.println("2");
    }
}
