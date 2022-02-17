package com.nio;



public class Main {
	public static void main(String[] args) {
		NIO file=new NIO();
		long start = System.currentTimeMillis();
		System.out.println(start);
		final Runnable producer = () -> {
			Integer r = 1;
			int count = 100;
			while (count-- > 0) {

				// System.out.println("producing :"+r);
				file.offer(r);
				r++;
				// TimeUnit.SECONDS.sleep(2);
			}
		};
		int count1 = 10;
		while (count1-- > 0)
			new Thread(producer).start();
		long end = System.currentTimeMillis();
		System.out.println(end);
		System.out.println("Time Taken:" + (end - start));

	}
}
