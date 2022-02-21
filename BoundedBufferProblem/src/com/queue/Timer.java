package com.queue;

public class Timer extends WrapperQueue{
	
	public Timer(int capacity)
	{
		super(capacity);
	}
	public void put(Integer e)
	{
		long start=System.currentTimeMillis();
		super.put(e);
		long end=System.currentTimeMillis();
		System.out.println("producing time:"+(start-end));
	}
	public Integer take()
	{
		long start=System.currentTimeMillis();
		Integer val=super.take();
		long end=System.currentTimeMillis();
		System.out.println("consuming time:"+(start-end));
		return val;
	}
	

}
