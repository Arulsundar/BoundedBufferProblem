package com.eg;
import java.util.*;
public class MaxOccurrence {

	public static void main(String[] args) {
       Scanner sc=new Scanner(System.in);
       String s=sc.nextLine();
       int[] freq=new int[256];
       int max=0;
       for(int i=0;i<s.length();i++)
       {
    	   int ix=s.charAt(i);
    	   freq[ix]++;
    	   if(freq[ix]>max)
    		   max=freq[ix];
       }
       char c=0;
       for(int i=0;i<255;i++)
       {
    	   if(freq[i]==max)
    		   c=(char)i;
       }
       System.out.println(c+"-"+max);
	}

}
