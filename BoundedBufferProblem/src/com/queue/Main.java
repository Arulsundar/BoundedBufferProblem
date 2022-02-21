package com.queue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		Timer q = new Timer(5);
		AtomicInteger num=new AtomicInteger();
        ExecutorService pool = Executors.newFixedThreadPool(10);
		final Runnable producer = () -> {
			while (true) {

				try {
//					int r = (int) (Math.random() * 10);
					int r=num.getAndIncrement();
					System.out.println("producing :" + r);
					q.put(r);
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
//	      new Thread(producer).start();
		final Runnable consumer = () -> {
			while (true) {

				try {
					Integer val = q.take();
					System.out.println("consuming :" + val);
					TimeUnit.SECONDS.sleep(2);

				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
//	      new Thread(consumer).start();
//		while (true) {
//			new Thread(producer).start();
//			Thread.sleep(1000);
//			new Thread(consumer).start();
//			Thread.sleep(1000);
//		}
		while(true)
		{
			pool.execute(producer);
			Thread.sleep(1000);
			pool.execute(consumer);
			Thread.sleep(1000);
		}
	}
}
