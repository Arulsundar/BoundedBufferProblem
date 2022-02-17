package com.eg;
import java.util.*;
public class Spiral {

	public static void main(String[] args) {
      Scanner sc=new Scanner(System.in);
      int m=sc.nextInt();
      int n=sc.nextInt();
      int val=1;
      int[][] mat=new int[m][n];
      int x=sc.nextInt();
      for(int i=0;i<m;i++)
      {
    	  for(int j=0;j<n;j++)
    	  {   if(i==0 && j==0)
    		     mat[i][j]=1;
    	      else  
    	      {
    	    	 val=val+x;
    		     mat[i][j]=val;
    	      }
    	  }
      }
     
      for(int i=0;i<m;i++)
      {
    	  for(int j=0;j<n;j++)
    	  {
    		System.out.print(mat[i][j]);
    	  }
    	  System.out.println();
      }
      print(mat,m,n);
	}

	private static void print(int[][] mat,int m,int n) {
		 //upper half
		 for(int r=0;r<n;r++)
		 {
			 for(int i=0,j=r;i<m&&j>=0;i++,j--)
			 {
				 System.out.print(mat[i][j]);
			 }
		 }
		//lower half
		 for(int c=1;c<m;c++)
		 {
			 for(int i=c,j=n-1;i<m&&j>=0;i++,j--)
			 {
				 System.out.print(mat[i][j]);
			 }
		 }
	}

}
