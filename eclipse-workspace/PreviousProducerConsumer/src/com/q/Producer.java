package com.q;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Producer implements Runnable {
	MyBlockingQueue q;

	public Producer(MyBlockingQueue q) {
		this.q = q;
	}

	@Override
	public void run() {
		int r = 1;
		while (true) {
			try {
				System.out.println("Producing : " + r);
				q.put(r);
				r++;
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException | IOException e) {
				e.printStackTrace();
				break;
			}
		}
	}

}
