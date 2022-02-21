package com.queue;

public class Timer extends WrapperQueue{
	final AverageComputeUtil putAvg = new AverageComputeUtil();
	final AverageComputeUtil takeAvg = new AverageComputeUtil();
	
	public Timer(int capacity)
	{
		super(capacity);
	}
	public void put(Integer e)
	{
		final long start=System.nanoTime();
		super.put(e);
		final long end=System.nanoTime();
		putAvg.add(end - start);
		System.out.println("Put timer :: "
			+ putAvg.getCount() + " puts in "
			+ new BigDecimal(putAvg.getSum()).divide(new BigDecimal(1_000_000_000)) + " seconds"
		);
	}
	public Integer take()
	{
		final long start=System.nanoTime();
		Integer val=super.take();
		final long end=System.nanoTime();
		takeAvg.add(end - start);
		System.out.println("Take timer :: "
			+ takeAvg.getCount() + " takes in "
			+ new BigDecimal(takeAvg.getSum()).divide(new BigDecimal(1_000_000_000)) + " seconds"
		);
		return val;
	}
}

class AverageComputeUtil {
	private volatile long sum;
	private volatile int count;

	public synchronized void add(long val) {
		sum += val;
		count++;
	}

	// return double or bigdecimal ? 
	// public synchronized long getAvg() {
	// 	if(count == 0)
	// 		return 0;
	// 	return sum / count;
	// }

	public synchronized int getCount() {
		return count;
	}

	public synchronized long getSum() {
		return sum;
	}
}

