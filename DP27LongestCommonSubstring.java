package com.tuf;

import java.util.Arrays;

public class DP27LongestCommonSubstring {
	public static void main(String[] args) {

		 String s1 = "abcjklp";
		 String s2 = "acjkp";

		//String s1 = "aa";
		//String s2 = "bc";
		System.out.println("The Length of Longest Common Substring is " + lcsTabulationLengh(s1, s2));
		int n = s1.length();
		int m = s2.length();
		System.out.println("Recursion :The  Longest Common Substring is : " + lcsString(s1, s2));

	}

	static int lcsTabulationLengh(String s1, String s2) {
		int n = s1.length();
		int m = s2.length();

		int index1 = 0, index2 = 0;

		int dp[][] = new int[n + 1][m + 1];
		
		
		int ans = 0;
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
					int val = 1 + dp[i - 1][j - 1];
					dp[i][j] = val;
					 ans = Math.max(ans, val);
					
				} else {
					dp[i][j] = 0;
				}
			}
		}
		return ans;
	}

	static String lcsString(String s1, String s2) {
		int n = s1.length();
		int m = s2.length();

		int index1 = 0, index2 = 0;

		int dp[][] = new int[n + 1][m + 1];
		int ans = 0;
		
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
					int val = 1 + dp[i - 1][j - 1];
					dp[i][j] = val;
					// ans = Math.max(ans, val);
					
					if (ans <= val) {
						ans = val;
						index1 = i;
						index2 = j;
					}
				} else {
					dp[i][j] = 0;
				}
			}
		}

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				System.out.print(dp[i][j] + "");
			}
			System.out.println();
		}

		System.out.println("Max lenghth:" + ans + " index1:" + index1 + " index2:" + index2);

		int i = index1-1;
		int j = index2-1;
		String s = "";

		while (i >= 0 && j >= 0) {
			if (s1.charAt(i) == s2.charAt(j)) {
				s = s1.charAt(i) + s;
				i--;
				j--;
			} else {
				break;
			}
		}
		//System.out.println(s);
		return s;
	}

}
