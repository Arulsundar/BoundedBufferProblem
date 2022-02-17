package com.eg;

import java.util.Scanner;

public class DescendingString {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();
		int[] freq = new int[256];
		for (int i = 0; i < s.length(); i++) {
			int ix = s.charAt(i);
			freq[ix]++;
		}
		String res ="";
		for(int i=255;i>=0;i--)
		{
			int cur=freq[i];
			while(cur>0)
			{
				res+=(char) i;
				cur--;
			}
			
		}
		System.out.println(res);
	}

}
