package com.tuf;

import java.util.Arrays;

public class DP3FrogKthJump {
	public static void main(String[] args) {
		System.out.println("Min energy/cost required to Frog Jump kth steps ");
		int a[] = { 30, 10, 60, 10, 60, 50 };
		int n = a.length;
		int k = 2;
		System.out.println("-------Using Recursion--------");
		int minCost = usingRecursion(a.length - 1, a, k);

		System.out.println("min:" + minCost);
		System.out.println("recursion Time Complexity: O(N) and Space Complexity:=O(n)+O(n)");

		System.out.println("-----------------------------------------------");
		System.out.println("\nconvert recursion -->DP ie: Using memoization");
		int dp[] = new int[n];
		Arrays.fill(dp, -1);
		minCost = usingMemoization(n - 1, a, k, dp);
		System.out.println("DP-min:" + minCost);
		System.out.println("DP-memoization Time Complexity: O(N *K) and Space Complexity:=O(N) + O(N) ≈ O(N))");
		System.out.println("-----------------------------------------------");
		System.out.println("convert DP-Tabulation ie: Using Tabulation");
		minCost = usingTabulation(n, a, k);
		System.out.println("DP-Tabulation-min:" + minCost);
		System.out.println("DP-Tabulation Time Complexity: O(N *K) and Space Complexity:=O(N)");

	}

	private static int usingRecursion(int ind, int[] a, int k) {
		if (ind == 0)
			return 0;

		int minJump = Integer.MAX_VALUE;
		for (int j = 1; j <= k; j++) {
			int jump = 0;
			if (ind - j >= 0) {
				jump = usingRecursion(ind - j, a, k) + Math.abs(a[ind] - a[ind - j]);
			}

			minJump = Math.min(minJump, jump);
		}

		return minJump;
	}

	private static int usingMemoization(int ind, int[] a, int k, int dp[]) {
		
		System.out.println(dp[ind]);
		if (ind == 0)
			return 0;

		if (dp[ind] != -1) {
			return dp[ind];
		}

		int minJump = Integer.MAX_VALUE;
		for (int j = 1; j <= k; j++) {
			int jump = 0;

			if (ind - j >= 0) {
				jump = usingMemoization(ind - j, a, k, dp) + Math.abs(a[ind] - a[ind - j]);
			}
			minJump = Math.min(minJump, jump);
			dp[ind] = minJump;
			
		}

		return dp[ind] = minJump;
	}

	private static int usingTabulation(int ind, int[] a, int k) {

		int dpTabul[] = new int[ind];
		Arrays.fill(dpTabul, -1);
		dpTabul[0] = 0;

		for (int i = 1; i < ind; i++) {
			int minStep = Integer.MAX_VALUE;
			
			/*This logic is same as like 
			 * fs = dp[i-1]+abs(a[i]-a[i-1])
			 * if(i>1){
			 * ss = dp[i-2]+abs(a[i]-a[i-2])
			 * dp[i] = Math.min(fs, ss);
			*/
			for (int j = 1; j <= k; j++) {  
				if (i - j >= 0) {
					int jump = dpTabul[i - j] + Math.abs(a[i] - a[i - j]);
					minStep = Math.min(minStep, jump);
				}
				
			}
			dpTabul[i] = minStep;
		}

		return dpTabul[ind-1];
	}

}
