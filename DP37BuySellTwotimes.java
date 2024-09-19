package com.tuf;

public class DP37BuySellTwotimes {
	public static void main(String[] args) {

		// int arr[] = { 7, 1, 5, 3, 6, 4 };

		int arr[] = { 3, 3, 5, 0, 0, 3, 1, 4 };
		int n = arr.length;
		int cap = 2; // only two transactione ie 1. Buy and sell 2. Buy and sell
		int buy = 0; // Variable buy=0 means 0 means BUY and 1 means sell

		System.out.println(
				"The maximum profit that can be generated is " + getMaximumProfit_approach_1(0, arr, n, buy, cap));

		long arr1[] = { 3, 3, 5, 0, 0, 3, 1, 4 };

		System.out.println("tabulation_getMaximumProfit The maximum profit that can be generated is "
				+ tabulation_getMaximumProfit(arr1));

		int ind = 0;
		int transNo = 0; // Buy ,sell ,buy and sell ie two transaction
		int k = 2; // Buy ,sell ,buy and sell ie two transaction

		System.out.println(
				"The maximum profit that can be generated is " + getMaximumProfit_approach_2(ind, transNo, arr, n,k));

	}

	private static int getMaximumProfit_approach_1(int ind, int[] arr, int n, int buy, int cap) {

		if (ind == n || cap == 0) {
			return 0;
		}

		int profit = 0;

		if (buy == 0) {
			profit = Math.max(-arr[ind] + getMaximumProfit_approach_1(ind + 1, arr, n, 1, cap),
					0 + getMaximumProfit_approach_1(ind + 1, arr, n, 0, cap));
		}

		if (buy == 1) {
			profit = Math.max(arr[ind] + getMaximumProfit_approach_1(ind + 1, arr, n, 0, cap - 1),
					0 + getMaximumProfit_approach_1(ind + 1, arr, n, 1, cap));
		}

		return profit;
	}

	private static long tabulation_getMaximumProfit(long[] arr) {

		int n = arr.length;
		// int buy = 2;
		// int trans = 3;

		long dp[][][] = new long[n + 1][2][3];

		// base condition ind == n

		// not required bec its zero only

		/*
		 * for (int b = 0; b <buy; b++) { for (int c = 0; c <trans; c++) { dp[n][b][c] =
		 * 0; } }
		 * 
		 * // base condition cap == 0 for (int i = 0; i <= n; i++) { for (int b = 0;b <
		 * buy; b++) { dp[n][b][0] = 0; } }
		 */

		for (int ind = n - 1; ind >= 0; ind--) {
			for (int buy = 0; buy <= 1; buy++) {
				for (int cap = 1; cap <= 2; cap++) {
					if (buy == 0) // We can buy the stock
					{

						dp[ind][buy][cap] = Math.max(-arr[ind] + dp[ind + 1][1][cap], 0 + dp[ind + 1][0][cap]);
					} else {

						dp[ind][buy][cap] = Math.max(arr[ind] + dp[ind + 1][0][cap - 1], // changed 0-->1 means sold the
								// stock
								0 + dp[ind + 1][1][cap]);
					}
				}

			}
		}
		return dp[0][0][2];
	}

	private static int getMaximumProfit_approach_2(int ind, int transNo, int[] arr, int n, int k) {

		if (ind == n || transNo == 2 * k) {
			// transNo = 2*k bec k=2 transaction means total 4 ie BUY,SELL,BUY and SELL
			return 0;
		}

		if (transNo % 2 == 0) {
			return Math.max(-arr[ind] + getMaximumProfit_approach_2(ind + 1, transNo + 1, arr, n, k),
					0 + getMaximumProfit_approach_2(ind + 1, transNo, arr, n, k));
		} else {
			return Math.max(arr[ind] + getMaximumProfit_approach_2(ind + 1, transNo + 1, arr, n, k),
					0 + getMaximumProfit_approach_2(ind + 1, transNo, arr, n, k));

		}

	}}
