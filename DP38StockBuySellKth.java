package com.tuf;

public class DP38StockBuySellKth {
	public static void main(String[] args) {
		// int arr[] = { 7, 1, 5, 3, 6, 4 };

		int arr[] = { 3, 3, 5, 0, 0, 3, 1, 4 };
		int n = arr.length;
		int cap = 2; // only two transactione ie 1. Buy and sell 2. Buy and sell
		int buy = 0; // Variable buy=0 means 0 means BUY and 1 means sell

		System.out.println(
				"The maximum profit that can be generated is " + getMaximumProfit_approach_1(0, arr, n, buy, cap));

		int ind = 0;
		int transNo = 0;
		int k = 2; // Buy ,sell ,buy and sell ie two transaction  kth transaction

		System.out.println(
				"The maximum profit that can be generated is " + getMaximumProfit_approach_2(ind, transNo, arr, n, k));

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

	}
}
