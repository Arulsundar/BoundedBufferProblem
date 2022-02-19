package com.queue;

import java.io.Serializable;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.CompletableFuture;

public class MyArrayBlockingQueue implements Serializable {
	private static final long serialVersionUID = 1L;
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
            CompletableFuture.runAsync(this::signalNotEmpty);
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
			CompletableFuture.runAsync(this::signalNotFull);
            return polled ;
        } catch (Exception ex) {
            throw new RuntimeException(ex) ;
        } finally {
            takeLock.unlock();	
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
    public int size()
    {
    	return size.get();
    }

	@Override
	public String toString() {
		return "MyArrayBlockingQueue{" +
				"queue=" + Arrays.toString(queue) +
				'}';
	}
}