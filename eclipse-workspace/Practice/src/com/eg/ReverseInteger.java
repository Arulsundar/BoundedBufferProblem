package com.eg;
import java.util.*;
public class ReverseInteger {

	public static void main(String[] args) {
      Scanner sc=new Scanner(System.in);
      int num=sc.nextInt();
      int n=num;
      for(int i=0;i<9;i++)
      {  
    	  while(n!=0)
    	  {
    		  int cur=n%10;
    		  
    		  if(cur==i)
    			  System.out.print(i);
    		 n=n/10;
    	  }
    	  n=num;
      }
	}

}
