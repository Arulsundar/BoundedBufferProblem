package com.new2;

import java.util.concurrent.TimeUnit;

public class Main {


    public static void main(String[] args) throws InterruptedException {
        WrapperQueue q = new WrapperQueue(5) ;

        final Runnable producer=() ->
	      {  
	    	  
	    	  while(true)
	    	  {
	    		  
	    		  try {
	    			  int r=(int) (Math.random()*10);
	    			  System.out.println("producing :"+r);
					  q.put(r);
//					  r++;
					  TimeUnit.SECONDS.sleep(2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
	    	  }
	      };
	      new Thread(producer).start();
	      final Runnable consumer=() ->
	      { 
	    	  while(true)
	    	  {
	    		  
	    		  try {
	    			  Integer val=q.take();
	    			  if(val == null)
	    			    continue;
	    			  else
	    				  System.out.println("consuming :"+ val);				  
	    			  TimeUnit.SECONDS.sleep(2);
					  
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
	    	  }
	      };
	      new Thread(consumer).start();
	      while(true)
	      {
	    	   new Thread(producer).start();
	    	   Thread.sleep(1000);
	           new Thread(consumer).start();
	           Thread.sleep(1000);
	      }
		
	

    }
}
