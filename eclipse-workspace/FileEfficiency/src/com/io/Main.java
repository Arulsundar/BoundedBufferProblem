package com.io;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Main {

	public static void main(String[] args) {
		IOFile file=new IOFile();
		long start=System.currentTimeMillis();
		System.out.println(start);
		final Runnable producer=() -> {
		 Integer r=1;
	      int count=100;
			while(count-- >0)
			{
	    		  
	    		  try {
	    			  
//	    			  System.out.println("producing :"+r);
					  file.writeLine(r);
					  r++;
//					  TimeUnit.SECONDS.sleep(2);
				} catch (IOException e) {
					e.printStackTrace();
				}
	    	  }};
	    	 int count1=10;
	    	 while(count1-- >0)
	    		 new Thread(producer).start();
		long end=System.currentTimeMillis();
		System.out.println(end);
		System.out.println("Time Taken:"+(end-start));

	}

}
