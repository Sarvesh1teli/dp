package com.tuf;

import java.util.Arrays;

public class DP14SubsetSumEqualsTarget {

	public static void main(String[] args) {

		int arr[] = { 1, 2, 3, 4 };
		int k = 6;
		int n = arr.length;

		System.out.println("-----Recursion------");
		if (subsetSumToK(n, k, arr, "rec"))
			System.out.println("Subset with given target found");
		else
			System.out.println("Subset with given target not found");

		System.out.println("-----Memoization------"); // Time Complexity: O(N*K) Space Complexity: O(N*K) + O(N)
		if (subsetSumToK(n, k, arr, "mem"))
			System.out.println("Subset with given target found");
		else
			System.out.println("Subset with given target not found");

		System.out.println("-----Tabulation------");// Time Complexity: O(N*K) Space Complexity: O(N*K)

		int arr1[] = { 1, 2, 3, 4 };
		int k1 = 4;
		int n1 = arr.length;

		if (subsetSumUtil_Tabulation(n1, k1, arr1))
			System.out.println("Subset with given target found");
		else
			System.out.println("Subset with given target not found");

		System.out.println("-----Space------");// Time Complexity: O(N*K) Space Complexity: O(K)

		if (subsetSumUtil_Space(n1, k1, arr1))
			System.out.println("Subset with given target found");
		else
			System.out.println("Subset with given target not found");
	}

	static boolean subsetSumToK(int n, int k, int[] arr, String way) {

		boolean dp[][] = new boolean[n][k + 1];
		
		if (way.equals("rec")) {
			return subsetSumUtil_Recursion(n - 1, k, arr);
		}

		return subsetSumUtil_Memoization(n - 1, k, arr, dp);

	}

	static boolean subsetSumUtil_Recursion(int ind, int target, int[] arr) {

		if (target == 0) {
			return true;
		}
		if (ind == 0) {
			return (target == arr[0]);
		}
		boolean notPick = subsetSumUtil_Recursion(ind - 1, target, arr);
		boolean pick = false;
		if (target >= arr[ind]) {

			pick = subsetSumUtil_Recursion(ind - 1, target - arr[ind], arr);

		}
		return notPick || pick;
	}
	
	
	static boolean subsetSumUtil_Memoization(int ind, int target, int[] arr, boolean[][] dp) {
		if (target == 0) {
			return true;
		}
		if (ind == 0) {
			return (target == arr[0]);
		}

		if (dp[ind][target] != false) {
			
			
			return dp[ind][target] ;
			
			//return dp[ind][target] == 1 ? true : false;
		}
		boolean notPick = subsetSumUtil_Recursion(ind - 1, target, arr);
		boolean pick = false;
		if (target >= arr[ind]) {

			pick = subsetSumUtil_Recursion(ind - 1, target - arr[ind], arr);
			
		}
		return dp[ind][target]=notPick || pick;
	}

	static boolean subsetSumUtil_Tabulation(int n, int k, int[] arr) {
		boolean dp[][] = new boolean[n][k + 1];

		for (int i = 0; i < n; i++) {
			dp[i][0] = true; // if target==0 return true; here target is fix ie 0 and i is 0,1,2....n-1 so
			System.out.print(dp[i][0]+" ");				// loop
		}
		
		System.out.println();

		if (arr[0] <= k)
			dp[0][arr[0]] = true;

		for (int ind = 1; ind < n; ind++) {

			for (int target = 1; target <= k; target++) {

				boolean notTaken = dp[ind - 1][target];
				boolean taken = false;

				if (arr[ind] <= target) {
					taken = dp[ind - 1][target - arr[ind]];
				}

				dp[ind][target] = notTaken || taken;
				//System.out.println("["+ind+"]"+"["+target+"]"+dp[ind][target]);
				System.out.print(dp[ind][target] +" ");
			}
			System.out.println();
		}
		return dp[n - 1][k];

	}

	static boolean subsetSumUtil_Space(int n, int k, int[] arr) {
		boolean prev[] = new boolean[k + 1];
		prev[0] = true;

		boolean cur[] = new boolean[k + 1];
		cur[0] = true;

		
		if (arr[0] <= k)
			prev[arr[0]] = true;

		for (int ind = 1; ind < n; ind++) {
			
			for (int target = 1; target <= k; target++) {
				boolean notTaken = prev[target];
				boolean taken = false;

				if (arr[ind] <= target)
					taken = prev[target - arr[ind]];

				cur[target] = notTaken || taken;

			}
			prev = cur;
		}
		return prev[k];
	}
}
