package com.book.JavaHighCurrencyDesign.chapter5concurrencyPatternAndAlgorithm.parallelOrder;

/**
 * Created by niceyuanze on 17-5-17.
 */
public class parallelOrder {
    static int[] arr;
    public static void oddEvenSort(int[] arr){
        int exchFlag = 1, start = 0;
        while(exchFlag == 1 || start == 1){

            exchFlag = 0;

            for(int i = start; i < arr.length - 1; i += 2){
                if(arr[i] > arr[i+1]){
                    int temp = arr[i];
                    arr[i] = arr[i+1];
                    arr[i+1] = temp;
                    exchFlag = 1;
                }
            }
            if(start == 0)
                start = 1;
            else
                start = 0;

        }
    }

    public static void main(String[] args) {
        arr = new int[]{223, 4, 3, 356, 3456, 436, 3456, 3457, 234, 523, 45, 2345};
        oddEvenSort(arr);
        for(int x:arr){
            System.out.println(x);
        }
    }
}
