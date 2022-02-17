package com.eg;
import java.util.*;
public class DiamondNumber {

	public static void main(String[] args) {
       Scanner sc=new Scanner(System.in);
       int n=sc.nextInt();
       int val=1;
       int[][] mat=new int[n][n];
       int left=0,top=0,right=n-1,down=n-1;
       while(left<right && top<down)
       {
    	   for(int i=top,j=left;i<=down;i++)
    	   {
    		   mat[i][j++]=val++;
    	   }
    	   left++;
    	   down--;
    	   for(int i=down,j=right;i>=top;i--)
    	   {
    		   mat[i][j]=val++;
    	   }
    	   right--;
    	   down--;
    	   for(int i=right,j=top;i>=left;i--)
    	   {
    		   mat[j][i]=val++;
    	   }
    	   top++;
    	   left++;
       }
       for(int i=0;i<n;i++)
       {
    	   for(int j=0;j<n;j++)
    	   {
    		   if(mat[i][j]>0)
    			   System.out.printf("%2s ",mat[i][j]);
    		   else
    			   System.out.print(" ");
    	   }
    	   System.out.println();
       }
	}

}
