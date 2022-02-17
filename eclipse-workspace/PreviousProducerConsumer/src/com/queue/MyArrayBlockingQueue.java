package com.queue;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MyArrayBlockingQueue {
    private final Integer[] queue ;
    private final int capacity ;
    //left and right never points to the same position ever.
    private final AtomicInteger left = new AtomicInteger(-1) ;
    private final AtomicInteger right = new AtomicInteger(0) ;
    private final AtomicInteger size = new AtomicInteger() ;
    private FileStore file;

//    private final ReentrantLock putLock = new ReentrantLock() ;
//    private final Condition notFull = putLock.newCondition() ;
//
//    private final ReentrantLock takeLock = new ReentrantLock() ;
//    private final Condition notEmpty = takeLock.newCondition() ;

    public MyArrayBlockingQueue(int capacity) {
        this.capacity = capacity ;
        this.queue = new Integer[capacity] ;
        this.file=new FileStore();
    }

    private void addToLast(Integer e) {
        queue[right.get()] = e;
        right.set((right.get() + 1) % capacity);
        size.incrementAndGet();
    }

    private Integer removeFromFront() {
        left.set((left.get() + 1) % capacity );
        Integer polled = queue[left.get()] ;
        size.decrementAndGet() ;
        return polled ;
    }

   
    public void put(Integer e) {
        try {
            if(!queueFull())
              addToLast(e) ;
            
           
        } catch (Exception ex) {
            throw new RuntimeException(ex) ;
        } 
    }

    public Integer take() {
        try {
//            if(queueEmpty())
//              return null;
//        	int x=1;
//        	while(x==1)
//        		;
            Integer polled = removeFromFront() ;
            return polled ;
        } catch (Exception ex) {
            throw new RuntimeException(ex) ;
        } 
    }
    public boolean queueFull()
    {
    	return (size.get()==capacity);
    }
    public boolean queueEmpty()
    {
    	return (size.get()==0);
    }
}
