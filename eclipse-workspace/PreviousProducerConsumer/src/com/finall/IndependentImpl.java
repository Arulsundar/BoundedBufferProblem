package com.finall;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class IndependentImpl {
	private int[] queue;
	private int capacity;
	private int front, rear;
	private AtomicInteger size;
	private ReentrantLock putLock;
	private ReentrantLock takeLock;
	private FileStore file;

	public IndependentImpl(int capacity) {
		
		this.capacity = capacity;
		this.queue = new int[this.capacity];
		front =0;
		size = new AtomicInteger();
		rear = - 1;
		putLock = new ReentrantLock(true);
		takeLock=new ReentrantLock(true);
		this.file = new FileStore();
	}

	public void shiftFromFileToQueue() throws IOException {
		String line;
		while (size.get() < capacity && (line= file.readLine()) != null) {
			 rear++;
	            if (rear == capacity ) {
	                rear = 0;
	            }
	            
		        queue[rear]=Integer.valueOf(line);
		        size.incrementAndGet();
//			System.out.println("Shifting from file,"+Integer.valueOf(line));
		}
	}

	public void put(Integer e) throws IOException, InterruptedException {
		putLock.lock();
		try {
			shiftFromFileToQueue();
			 
			 if(!queueFull())
			 {  rear++;
	            if (rear == capacity ) {
	                rear = 0;
	            }
	            
		        queue[rear]=e;
		        size.incrementAndGet();
		       
			 }
			 else
				 file.writeLine(e);
		       
		} finally {
			putLock.unlock();
		}
		
	}
    
	public Integer take() throws InterruptedException, IOException
	{
	      takeLock.lock();
	        try {
	            if (queueEmpty())
	               return null;
	            int item = queue[front];

	            front++;
	            if (front == capacity)
	                front = 0;

	            size.decrementAndGet();

	            shiftFromFileToQueue();

	            return item;

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

}
/*
 * Suppose we have a queue length of 7
 * Initially,if producer adds an element 10 in the queue ,front and rear points to the same position "0" now :check if Thread safe exists
 * Suppose if the queue is full [10,20,30,40,50,60,70] and the consumer consumes 10 and 20 from the queue and sleeps for few seconds.
    But producer is ready with an element =>
    the shifting of values from queue to file [30,40,50,60,70] should happen instead of  adding an element to the file.
 * While shifting ,the producer should not wait with an element :check efficiency
 * Read and write in a file has to done in a efficient way..
 * After shifting,the consumer has to read from the queue ?
 */

