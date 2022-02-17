package com.eg;

import java.util.Scanner;

public class TrianglePattern {

	public static void main(String[] args) {
      Scanner sc=new Scanner(System.in);
      int n=sc.nextInt();
      int[][] mat=new int[n][n];
      int val=1;
      int left=0,top=0,right=n-1,down=n-1;
      while(left<=right && top<=down)
      {
    	  for(int i=top,j=right;i<=down && j>=0;i++,j--)
    	  {
    		  mat[i][j]=val++;
    	  }
    	  top++;
    	  left++;
    	  
      }
      for(int i=0;i<n;i++)
      {
    	  for(int j=0;j<n;j++)
    	  {
    		 if(mat[i][j]>0)
    		   System.out.print(mat[i][j]);
    		 System.out.print(" ");
    	  }
    	  System.out.println();
      }
	}

}
