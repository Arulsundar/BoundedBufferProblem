package com.q;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Consumer implements Runnable {
	 MyBlockingQueue q;
	    
		public Consumer(MyBlockingQueue q) {
			this.q = q;
		}
	@Override
	public void run() {
		 while(true) {
             try {
                 System.out.println("Consuming : " + q.take()) ;
                 TimeUnit.SECONDS.sleep(5) ;
             } catch (InterruptedException | IOException e) {
                 e.printStackTrace();
                 break ;
             }
         }	
	}

}
