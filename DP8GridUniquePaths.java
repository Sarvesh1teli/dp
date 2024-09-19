package com.tuf;

import java.util.Arrays;

public class DP8GridUniquePaths {
	public static void main(String[] args) {

		int m = 3;
		int n = 3;

		System.out.println(countWays(m, n));
	}

	private static int countWays(int m, int n) {
		int dp[][] = new int[m][n];
		for (int[] row : dp)
			Arrays.fill(row, -1);

		// return countWaysUtil_Recursion(m-1,n-1);

		// return countWaysUtil_Memoization(m-1,n-1,dp);

		return countWaysUtil_Tabulation(m, n, dp);
	}

	static int countWaysUtil_Recursion(int i, int j) {

		if (i == 0 && j == 0) {
			return 1;
		}
		if (i < 0 || j < 0) {
			return 0;
		}
		int up = countWaysUtil_Recursion(i - 1, j);
		int left = countWaysUtil_Recursion(i, j - 1);
		return up + left;

	}

	static int countWaysUtil_Memoization(int i, int j, int[][] dp) {
		if (i == 0 && j == 0) {
			return 1;
		}
		if (i < 0 || j < 0) {
			return 0;
		}
		if (dp[i][j] != -1)
			return dp[i][j];

		int up = countWaysUtil_Memoization(i - 1, j, dp);
		int left = countWaysUtil_Memoization(i, j - 1, dp);
		return dp[i][j] = up + left;

	}

	static int countWaysUtil_Tabulation(int m, int n, int[][] dp) {

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (i == 0 && j == 0) {
					dp[i][j] = 1;
				} else {

					int up = 0, left = 0;
					if (i > 0) {
						up = dp[i - 1][j];
					}
					if (j > 0) {
						left = dp[i][j - 1];
					}
					dp[i][j] = up + left;
				}
			}
		}

		return dp[m - 1][n - 1];

	}
	
	static int countWaysUtil_SpaceOptimization(int m, int n) {
		
		int prev[] = new int[n];
		
		for (int i = 0; i < m; i++) {
			int cur[] = new int[m];
			for (int j = 0; j < n; j++) {
				if (i == 0 && j == 0) {
					cur[j] = 1;
				} else {

					int up = 0, left = 0;
					if (i > 0) {
						up = prev[j];
					}
					if (j > 0) {
						left = cur[j - 1];
					}
					cur[j] = up + left;
				}
			}
			prev= cur;
		}

		return prev[n-1];
	}
}
