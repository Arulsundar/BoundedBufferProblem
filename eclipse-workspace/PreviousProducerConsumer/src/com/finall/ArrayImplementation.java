package com.finall;

import java.io.IOException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ArrayImplementation {
	private int[] queue;
	private int capacity;
	private int front, rear, size;
	private ReentrantLock lock;
	private Condition notEmpty;
	private FileStore file;

	public ArrayImplementation(int capacity) {
		
		this.capacity = capacity;
		this.queue = new int[this.capacity];
		front =size = 0;
		rear = - 1;
		lock = new ReentrantLock(true);
		this.notEmpty = lock.newCondition();
		this.file = new FileStore();
	}

	public void shiftFromFileToQueue() throws IOException {
		String line;
		while (size < capacity && (line= file.readLine()) != null) {
			 rear++;
	            if (rear == capacity ) {
	                rear = 0;
	            }
	            
		        queue[rear]=Integer.valueOf(line);
		        size++;
			System.out.println("Shifting from file,"+Integer.valueOf(line));
		}
	}

	public void put(Integer e) throws IOException, InterruptedException {
		lock.lock();
		try {
			shiftFromFileToQueue();
			 
			 if(!queueFull())
			 {  rear++;
	            if (rear == capacity ) {
	                rear = 0;
	            }
	            
		        queue[rear]=e;
		        size++;
		        notEmpty.signalAll();
			 }
			 else
				 file.writeLine(e);
		       
		} finally {
			lock.unlock();
		}
	}
    
	public Integer take() throws InterruptedException, IOException
	{
	      lock.lock();
	        try {
	            while (queueEmpty())
	                notEmpty.await();

	            int item = queue[front];

	            front++;
	            if (front == capacity)
	                front = 0;

	            size--;

	            shiftFromFileToQueue();

	            return item;

	        } finally {
	            lock.unlock();
	        }
	}
    public boolean queueFull()
    {
    	return (size==capacity);
    }
    public boolean queueEmpty()
    {
    	return (size==0);
    }
}
