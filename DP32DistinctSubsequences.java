package com.tuf;

import java.util.Arrays;

public class DP32DistinctSubsequences {
	public static void main(String[] args) {
		String s1 = "babgbag";
		String s2 = "bag";

		subsequenceCounting(s1, s2, s1.length(), s2.length());
	}

	static void subsequenceCounting(String s1, String s2, int i, int j) {
		// Write your code here.

		System.out.println(rec_countUtil(s1, s2, i - 1, j - 1));

		int dp[][] = new int[i][j];

		for (int rows[] : dp)
			Arrays.fill(rows, -1);

		System.out.println(memoization_countUtil(s1, s2, i - 1, j - 1, dp));
		System.out.println(tab_countUtil(s1, s2, i, j));

	}

	private static int memoization_countUtil(String s1, String s2, int i, int j, int[][] dp) {
		if (j < 0) {
			return 1;
		}

		if (i < 0) {
			return 0;
		}

		if (dp[i][j] != -1) {
			return dp[i][j];
		}
		if (s1.charAt(i) == s2.charAt(j)) {
			return dp[i][j] = rec_countUtil(s1, s2, i - 1, j - 1) + rec_countUtil(s1, s2, i - 1, j);
		} else {
			return dp[i][j] = rec_countUtil(s1, s2, i - 1, j);
		}
	}

	private static int rec_countUtil(String s1, String s2, int i, int j) {
		if (j < 0) {
			return 1;
		}

		if (i < 0) {
			return 0;
		}
		if (s1.charAt(i) == s2.charAt(j)) {
			return rec_countUtil(s1, s2, i - 1, j - 1) + rec_countUtil(s1, s2, i - 1, j);
		} else {
			return rec_countUtil(s1, s2, i - 1, j);
		}
	}

	private static int tab_countUtil(String s1, String s2, int n, int m) {

		int dp[][] = new int[n + 1][m + 1];


	    System.out.println("n:"+n);
	    System.out.println("m:"+m);
	    
		for (int id1 = 0; id1 <= n; id1++) {
			dp[id1][0] = 1;
		}

		for (int id2 = 1; id2 <= m; id2++) {
			dp[0][id2] = 0;
		}

		for (int i = 1; i <= n; i++) {

			for (int j = 1; j <= m; j++) {
				if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
					dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j]);
				} else {
					dp[i][j] = dp[i - 1][j];
				}
			}
		}
		
		   
	    System.out.println("---");
	    for(int i=0;i<n+1;i++){
	        for(int j=0;j<m+1;j++){
	          System.out.print(dp[i][j]+" ");
	        }
	        System.out.println();
	    }

		return dp[n][m];
	}
}
