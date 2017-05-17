package com.book.JavaHighCurrencyDesign.chapter5concurrencyPatternAndAlgorithm.ParallelSearch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by niceyuanze on 17-5-17.
 */
public class ParallelSearch {
    static int [] arr;

    static ExecutorService pool = Executors.newCachedThreadPool();
    static final int Thread_Num = 2;
    static AtomicInteger result = new AtomicInteger(-1);











    public static class SearchTask implements Callable<Integer>{
        int begin, end, searchValue;

        public SearchTask(int searchValue, int begin, int end ) {
            this.begin = begin;
            this.end = end;
            this.searchValue = searchValue;
        }

        @Override
        public Integer call() throws Exception {
            int re = search(searchValue,begin,end);
            return re;
        }
    }

    public static int search(int searchValue, int beginPos, int endPos){
        int i = 0;
        System.out.println(beginPos+"     ddd      "+endPos);
        for(i = beginPos; i < endPos;i++){
//            System.out.println("xxx"+arr[i]+"   "+searchValue);

            if(result.get() >= 0){
//                System.out.println("!!!!");
                return result.get();
            }
            if(arr[i] == searchValue){
//                System.out.println("!!!!!");
                if(!result.compareAndSet(-1,i)){
                    return result.get();
                }
                return i;
            }
        }
        return -1;

    }

    public static int pSearch(int searchValue) throws ExecutionException, InterruptedException {

        int subArrSize = arr.length / Thread_Num;
        System.out.println(subArrSize);
        System.out.println(searchValue);
        System.out.println("--------------------");

        List<Future<Integer>> re = new ArrayList<Future<Integer>>();

        for(int i = 0; i < arr.length; i += subArrSize){
            int end = i + subArrSize;
            if(end >= arr.length)
                end = arr.length;
            System.out.println(i +"   "+end);
            re.add(pool.submit(new SearchTask(searchValue,i,end)));
        }

        for(Future<Integer> fu:re){
            if(fu.get() >= 0)
                return fu.get();
        }
        return -1;

    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        arr = new int[]{1, 2, 3, 4, 5, 6, 7,8,9};
        System.out.println("result: " + pSearch(9));
        pool.shutdown();
    }

}
