package com.eg;
import java.util.*;
public class RaceCar {

	public static void main(String[] args) {
       Scanner sc=new Scanner(System.in);
       String s=sc.nextLine();
       if(s.length()%2!=0)
    	   print(s);
       else
        System.out.println("Even length");
	}

	private static void print(String s) {
		int mid=s.length()/2;
		int len=s.length();
		for(int i=0;i<len;i++)
		{
			for(int j=0;j<len;j++)
			{
				if(i<=mid && j==mid+i || j==mid-i)
					System.out.print(s.charAt(j));
				else if(i>mid && j==mid+len-i-1 || j==mid-(len-i-1))
					System.out.print(s.charAt(j));
				else
					System.out.print(" ");
			}
			System.out.println();
		}
	}

}
