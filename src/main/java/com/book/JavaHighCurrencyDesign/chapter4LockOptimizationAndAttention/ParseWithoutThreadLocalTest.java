package com.book.JavaHighCurrencyDesign.chapter4LockOptimizationAndAttention;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by niceyuanze on 17-5-12.
 */
public class ParseWithoutThreadLocalTest {
    private static final SimpleDateFormat sdf =
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static class ParserDate implements Runnable{

        int i = 0;

        public ParserDate(int i){
            this.i = i;
        }
        @Override
        public void run() {
            try {
                Date t = sdf.parse("2015-03-29 19:29:"+i%60);
                System.out.println(i+":"+t);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws ParseException {
//        sdf.parse("2015-03-29 19:29:"+"12");
        ExecutorService es = Executors.newFixedThreadPool(10);
        for(int i = 0; i < 1000;i++){
            es.execute(new ParserDate(i));
        }
    }
}
