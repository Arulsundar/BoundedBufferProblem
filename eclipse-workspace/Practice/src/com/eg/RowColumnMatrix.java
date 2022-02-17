package com.eg;
import java.util.*;
public class RowColumnMatrix {

	public static void main(String[] args) {
    Scanner sc=new Scanner(System.in);
    int n=sc.nextInt();
    int c=sc.nextInt();
    for(int i=0;i<n;i++)
    {
    	int begin=i*n+1;
    	int end=begin+n-1;
    	int first=(n-c+1)+begin;
    	for(int j=first;j<=end;j++)
    		System.out.print(j);
    	for(int k=begin;k<first;k++)
    		System.out.print(k);
    	System.out.println();
    }
	} 

}
