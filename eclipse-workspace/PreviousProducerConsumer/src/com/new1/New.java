package com.new1;


import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

import com.finall.FileStore;

public class New {
	private int[] producer;
	private int[] consumer;
	private int capacity;
	private int front, rear,front1,rear1;
	private AtomicInteger size,size1;
	private ReentrantLock putLock;
	private ReentrantLock takeLock;
	private FileQueue file;

	public New(int capacity) {
		
		this.capacity = capacity;
		this.producer = new int[capacity+1/2];
		this.consumer=new int[capacity/2];
		front=front1=0;
		size = new AtomicInteger();
		size1=new AtomicInteger();
		rear =rear1 = - 1;
		putLock = new ReentrantLock(true);
		takeLock=new ReentrantLock(true);
		this.file = new FileQueue();
	}

	public void shiftFromQueueToFile() throws IOException {
//		String line;
//		while (size.get() < capacity && (line= file.readLine()) != null) {
//			 rear++;
//	            if (rear == capacity ) {
//	                rear = 0;
//	            }
//	            
//		        producer[rear]=Integer.valueOf(line);
//		        size.incrementAndGet();
//			System.out.println("Shifting from file,"+Integer.valueOf(line));
//		}
		if(front <capacity)
		{
		Integer e=producer[front];
		front++;
		size.getAndDecrement();
		file.offer(e);
//		System.out.println("shifting to file,"+e);
		}
		if(front==capacity) front=0;
		
	}
    public void shiftFromFileToQueue() throws NumberFormatException, IOException
    {   
    	String line;
    	Integer e = null;
    	
    	if(!consumerFull())
		 {  rear1++;
           if (rear1 == capacity ) {
               rear1 = 0;
           }
           if((line=file.poll())!=null)
           {
    	       e=Integer.valueOf(line);
//    	       System.out.println("writing to cons producer,"+e);
	       
           }
           if(e!= null)
        	   consumer[rear1]=e;
	        size1.incrementAndGet();
	       
		 }
		 
	       
    }
	public void put(Integer e) throws IOException, InterruptedException {
		putLock.lock();
		try {
			 
			 if(!queueFull())
			 {  rear++;
	            if (rear == capacity ) {
	                rear = 0;
	            }
	            
		        producer[rear]=e;
		        size.incrementAndGet();
		       
			 }
			 else
			 {
				 shiftFromQueueToFile();
				 
			     rear++;
	            if (rear == capacity ) {
	                rear = 0;
	            }
	            
		        producer[rear]=e;
		        size.incrementAndGet();
			 }
		       
		} finally {
			putLock.unlock();
		}
		
	}
    
	public Integer take() throws InterruptedException, IOException
	{
	      takeLock.lock();
	        try {
	        	shiftFromFileToQueue();
	            if (queueEmpty())
	               return null;
	            int item;
	            if(consumerFull())
	            {
	            	item = consumer[front1];

		            front1++;
		            if (front1 == capacity)
		                front1 = 0;

		            size1.decrementAndGet();
	            }
//	            if((line=file.poll())!=null)
//	            
//	     	       item=Integer.valueOf(line);
	            else
	            {
	            item = producer[front];

	            front++;
	            if (front == capacity)
	                front = 0;

	            size.decrementAndGet();
	            }

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
    public boolean consumerFull()
    {
    	return (size1.get()>=1 && size1.get()<=capacity);
    }
    public boolean consumerEmpty()
    {
    	return (size1.get()==0);
    }
}
/*
 * Suppose we have a queue length of 7
 * Initially,if producer adds an element 10 in the queue ,front and rear points to the same position "0" now :check if Thread safe exists
 * Suppose if the queue is full [10,20,30,40,50,60,70] and the consumer consumes 10 and 20 from the queue and sleeps for few seconds.
    But producer is ready with an element =>
    the shifting of values from queue to file [30,40,50,60,70] should happen instead of  adding an element to the file.
 * While shifting ,the producer should not wait with an element :check efficiency
 * Read and write in a file has to be done in a efficient way..
 * After shifting,the consumer has to read from the queue ?
 */


