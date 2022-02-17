package com.eg;
import java.util.*;
public class EquilateralTriangle {

	public static void main(String[] args) {
      Scanner sc=new Scanner(System.in);
      int n=sc.nextInt();
      int space=n+1;
      String stars="* ";
      for(int i=1;i<=n;i++)
      {
    	  System.out.printf("%"+space+"s",stars);
    	  space++;
    	  System.out.println();
    	  stars+="* ";
      }
	}

}
