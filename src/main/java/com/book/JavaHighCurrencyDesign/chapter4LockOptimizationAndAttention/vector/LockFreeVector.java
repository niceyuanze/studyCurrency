package com.book.JavaHighCurrencyDesign.chapter4LockOptimizationAndAttention.vector;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceArray;

/**
 * Created by niceyuanze on 17-5-14.
 */
public class LockFreeVector<E> {


    private final AtomicReferenceArray<AtomicReferenceArray<E>> buckets;

    private final int N_BUCKET = 30;

    private final int FIRST_BUCKET_SIZE = 8;

    private  final AtomicReference<Descriptor<E>> descriptor;

    public LockFreeVector() {
        buckets = new AtomicReferenceArray<AtomicReferenceArray<E>>(N_BUCKET);
        buckets.set(0, new AtomicReferenceArray<E>(FIRST_BUCKET_SIZE));
        descriptor = new AtomicReference<Descriptor<E>>(new Descriptor<E>(0, null));
    }

    public void push_back(E e){
        Descriptor<E> desc;
        Descriptor<E> newd;
//        do {
//            desc = descriptor.get();
//            desc.completeWrite();
//            int pos = desc.size + FIRST_BUCKET_SIZE;
//            int zeroNumPos = Integer.numberOfLeadingZeros(pos);
////            int bucketInd = zeroNumFirst - z
//        }

    }





    static class Descriptor<E>{


        public int size;
        volatile WriteDescriptor<E> writeop;


        public Descriptor(int size, WriteDescriptor<E> writeop) {
            this.size = size;
            this.writeop = writeop;
        }


        public void completeWrite(){
            WriteDescriptor<E> tmpOp = writeop;
            if(tmpOp != null){
                tmpOp.doIt();
                writeop = null;
            }
        }


        static class WriteDescriptor<E>{

            public AtomicReferenceArray<E> addr;
            public int addr_ind;
            public E oldV;
            public E newV;

            public WriteDescriptor(AtomicReferenceArray<E> addr, int addr_ind, E oldV, E newV) {
                this.addr = addr;
                this.addr_ind = addr_ind;
                this.oldV = oldV;
                this.newV = newV;
            }

            public void doIt(){
                addr.compareAndSet(addr_ind, oldV, newV);
            }
        }




    }
}
