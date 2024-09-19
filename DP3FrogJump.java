package com.tuf;

import java.util.Arrays;

public class DP3FrogJump {
	public static void main(String[] args) {
		int a[] = { 30, 10, 60, 10, 60, 50 };
		System.out.println("-------Using Recursion--------");
		int minEnery = usingRecursion(a.length - 1, a);
		int n = a.length - 1;
		System.out.println("min:" + minEnery);

		System.out.println("\n-------DP using Memoization--------");
		int dp[] = new int[n + 1];
		Arrays.fill(dp, -1);
		minEnery = usingRecursionMemorization(n, a, dp);
		System.out.println("DP memoization value:" + minEnery);

		System.out.println("\n-------DP using Tabulation--------");
		usingRecursionTabulation();
		
		System.out.println("\n-------DP using SpaceOptimization--------");
		usingSpaceOptimization();
	}

	private static int usingRecursion(int n, int[] a) {
		int rt = Integer.MAX_VALUE;
		if (n == 0)
			return 0;

		int left = usingRecursion(n - 1, a) + Math.abs(a[n] - a[n - 1]);
		if (n > 1)
			rt = usingRecursion(n - 2, a) + Math.abs(a[n] - a[n - 2]);

		return Math.min(rt, left);
	}

	private static int usingRecursionMemorization(int n, int[] a, int[] dp) {

		int rt = Integer.MAX_VALUE;

		if (n == 0)
			return 0;
		if (dp[n] != -1) {
			return dp[n];
		}

		int left = usingRecursionMemorization(n - 1, a, dp) + Math.abs(a[n] - a[n - 1]);
		if (n > 1)
			rt = usingRecursionMemorization(n - 2, a, dp) + Math.abs(a[n] - a[n - 2]);

		return dp[n] = Math.min(rt, left);
	}

	private static void usingRecursionTabulation() {

		int height[] = { 30, 10, 60, 10, 60, 50 };
		
		int n = height.length;
		System.out.println("n "+n);
		int dp[] = new int[n];
		Arrays.fill(dp, -1);
		dp[0] = 0;

		for (int i = 1; i < n; i++) {
			int fs = dp[i - 1] + Math.abs(height[i] - height[i - 1]);
			int ss = Integer.MAX_VALUE;

			if (i > 1) {
				ss = dp[i - 2] + Math.abs(height[i] - height[i - 2]);
			}

			dp[i] = Math.min(fs, ss);
		}
		System.out.println(dp[n - 1]);
	}

	private static void usingSpaceOptimization() {

		int height[] = { 30, 10, 60, 10, 60, 50 };
		int n = height.length;
		int prev  = 0;
		int prev2 = 0;

		for (int i = 1; i < n; i++) {
			int ss = Integer.MAX_VALUE;
			int fs = prev + Math.abs(height[i] - height[i - 1]);
			if (i > 1)
				ss = prev2 + Math.abs(height[i] - height[i - 2]);

			int cur_i = Math.min(fs, ss);
			prev2 = prev;
			prev = cur_i;
		}

		System.out.println(prev);
	}

}
