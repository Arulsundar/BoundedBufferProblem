package com.eg;
import java.util.*;
public class Cross {

	public static void main(String[] args) {
    Scanner sc=new Scanner(System.in);
    String s=sc.nextLine();
    int len=s.length();
    if(len%2!=0)
    {
    	for(int i=0;i<len;i++) {
    		for(int j=0;j<len;j++)
    		{
    			if(j==i || j==len-i-1)
    				System.out.print(s.charAt(j));
    			else
    				System.out.print(" ");
    		}
    		System.out.println();
    	}
    }
    else
    	System.out.println("Even length");
 }

}
