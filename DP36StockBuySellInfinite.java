package com.tuf;

import java.util.Arrays;

public class DP36StockBuySellInfinite {

	public static void main(String[] args) {
		int n = 6;
		long Arr[] = { 7, 1, 5, 3, 6, 4 };

		System.out.println("The maximum profit that can be generated is " + getMaximumProfit(0, Arr, n, 0));

		int buy = 2;
		long dp[][] = new long[n][buy];

		for (long row[] : dp) {
			Arrays.fill(row, -1);
		}
		System.out.println(
				"The maximum profit that can be generated is " + memoization_getMaximumProfit(0, Arr, n, 0, dp));

		System.out.println(
				"The maximum profit that can be generated is " + tabulation_getMaximumProfit(Arr));

		int arr[] = { 7, 1, 5, 3, 6, 4 };

		System.out.println(
				"The maximum profit that can be generated is " + space_getMaximumProfit(arr));

	}

	private static long getMaximumProfit(int ind, long[] arr, int n, int buy) {

		if (ind == n) {
			return 0;
		}

		long profit = 0;

		if (buy == 0) { // We can buy the stock

			// 1.buy the stock means investee the money so -arr[ind] 2.not bought
			profit = Math.max(-arr[ind] + getMaximumProfit(ind + 1, arr, n, 1), // changed 1-->0 means bought the stock
					0 + getMaximumProfit(ind + 1, arr, n, 0));
		}

		if (buy == 1) {// We can sell the stock

			// 1.sold means got the money 2.not sold
			profit = Math.max(arr[ind] + getMaximumProfit(ind + 1, arr, n, 0), // changed 0-->1 means sold the stock
					0 + getMaximumProfit(ind + 1, arr, n, 1));
		}
		return profit;
	}

	private static long memoization_getMaximumProfit(int ind, long[] arr, int n, int buy, long dp[][]) {

		if (ind == n) {
			return 0;
		}
		if (dp[ind][buy] != -1) {
			return dp[ind][buy];
		}

		long profit = 0;

		if (buy == 0) { // We can buy the stock

			// 1.buy the stock means investee the money so -arr[ind] 2.not bought
			profit = Math.max(-arr[ind] + getMaximumProfit(ind + 1, arr, n, 1), // changed 1-->0 means bought the stock
					0 + getMaximumProfit(ind + 1, arr, n, 0));
		}

		if (buy == 1) {// We can sell the stock

			// 1.sold means got the money 2.not sold
			profit = Math.max(arr[ind] + getMaximumProfit(ind + 1, arr, n, 0), // changed 0-->1 means sold the stock
					0 + getMaximumProfit(ind + 1, arr, n, 1));
		}
		return dp[ind][buy] = profit;
	}

	private static long tabulation_getMaximumProfit(long[] arr) {

		int n = arr.length;
		long dp[][] = new long[n+1][2];

		// base condition
		dp[n][0] = dp[n][1] = 0;

		long profit = 0;

		for (int ind = n - 1; ind >= 0; ind--) {
			for (int buy = 0; buy <= 1; buy++) {
				
				if (buy == 0) { // We can buy the stock

					profit = Math.max(-arr[ind] + dp[ind + 1][ 1], 0 + dp[ind + 1][0]);
				}

				if (buy == 1) {

					profit = Math.max(arr[ind] + dp[ind + 1][0], // changed 0-->1 means sold the
																						// stock
							0 + dp[ind + 1][1]);
				}
				 dp[ind][buy] = profit;
			}
		}
		return dp[0][0];
	}
	
	
	private static int space_getMaximumProfit(int[] arr) {

		int n = arr.length;
		int  ahead[] = new int [2];
		int  cur[] = new int [2];
		// base condition
		ahead[0] = ahead[1] = 0;

		int profit = 0;

		for (int ind = n - 1; ind >= 0; ind--) {
			for (int buy = 0; buy <= 1; buy++) {
				
				if (buy == 0) { // We can buy the stock

					profit = Math.max(-arr[ind] + ahead[ 1], 0 + ahead[0]);
				}

				if (buy == 1) {

					profit = Math.max(arr[ind] + ahead[0], // changed 0-->1 means sold the
																						// stock
							0 + ahead[1]);
				}
				 cur[buy] = profit;
			}
			ahead = cur.clone();
		}
		return cur[0];
	}
}
