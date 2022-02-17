package com.new2;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MyArrayBlockingQueue {
    private final Integer[] queue ;
    private final int capacity ;
    //left and right never points to the same position ever.
    private int left = -1 ;
    private int right = 0 ;
    private final AtomicInteger size = new AtomicInteger() ;

    private final ReentrantLock putLock = new ReentrantLock() ;
    private final Condition notFull = putLock.newCondition() ;

    private final ReentrantLock takeLock = new ReentrantLock() ;
    private final Condition notEmpty = takeLock.newCondition() ;

    public MyArrayBlockingQueue(int capacity) {
        this.capacity = capacity ;
        this.queue = new Integer[capacity] ;
    }

    private void addToLast(Integer e) {
        queue[right] = e;
        right = (right + 1) % capacity;
        size.incrementAndGet();
    }

    private Integer removeFromFront() {
        left = (left + 1) % capacity ;
        Integer polled = queue[left] ;
        size.decrementAndGet() ;
        return polled ;
    }

    private void signalNotEmpty() {
        takeLock.lock();
        try {
            notEmpty.signal();
        } finally {
            takeLock.unlock();
        }
    }

    private void signalNotFull() {
        putLock.lock() ;
        try {
            notFull.signal();
        } finally {
            putLock.unlock();
        }
    }

    public void put(Integer e) {
        putLock.lock() ;
        try {
            while (size.get() == capacity) {
                notFull.await() ;
            }
            addToLast(e) ;
            new Thread(this::signalNotEmpty).start();
        } catch (Exception ex) {
            throw new RuntimeException(ex) ;
        } finally {
            putLock.unlock() ;
        }
    }

    public Integer take() {
        takeLock.lock() ;
        try {
            while(size.get() == 0) {
                notEmpty.await();
            }
            Integer polled = removeFromFront() ;
            new Thread(this::signalNotFull).start() ;
            return polled ;
        } catch (Exception ex) {
            throw new RuntimeException(ex) ;
        } finally {
            takeLock.unlock();	
        }
    }
}
