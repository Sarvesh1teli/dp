 package com.tuf;

import java.util.Arrays;

/*

Count Subsets with Sum K .
ex:{ 1, 2, 2, 3 } k=3,
output: [1,2],[1,2],[3]

https://takeuforward.org/data-structure/count-subsets-with-sum-k-dp-17/

*/


public class DP17CountsSubsetswithSumK {

	public static void main(String[] args) {

		int arr[] = { 1, 2, 2, 3 };
		int n = arr.length;
		int k = 3;

		System.out.println("---recursion---");
		int count = countSubset_Recursion(arr, n - 1, k);
		System.out.println("count:" + count);

		int dp[][] = new int[n][k + 1];

		for (int row[] : dp) {
			Arrays.fill(row, -1);
		}

		System.out.println("---memoization-----");
		count = countSubset_Memoization(arr, n - 1, k, dp);
		System.out.println("count:" + count);
		
		System.out.println("Time Complexity: O(N*K)");
		System.out.println("Reason: There are N*K states therefore at max ‘N*K’ "
				+ "new problems will be solved.");
		
		System.out.println("Space Complexity: O(N*K) + O(N)");
		System.out.println("Reason: We are using a recursion stack space(O(N)) "
				+ "and a 2D array ( O(N*K)).");

		
		

		System.out.println("---Tabulation-----");
		count = countSubset_Tabulation(arr, n, k);
		System.out.println("count:" + count);
		System.out.println("Time Complexity: O(N*K)");
		System.out.println("Reason: There are two nested loops\n");
		
		System.out.println("Space Complexity: O(N*K)");
		System.out.println("Reason: We are using an external array of size ‘N*K’. "
				+ "Stack Space is eliminated.");

		
		
		
		
		System.out.println("---Space optimization-----");
		count = countSubset_Space(arr, n, k);
		System.out.println("count:" + count);
		System.out.println("Time Complexity: O(N*K)");
		System.out.println("Reason: There are two nested loops\n");
		
		System.out.println("Space Complexity: O(K)\n");
		System.out.println("Reason: We are using an external array of size ‘K+1’ "
				+ "to store only one row.");


	}

	private static int countSubset_Recursion(int[] arr, int ind, int target) {

		if (target == 0) {
			return 1;
		}

		if (ind == 0) {
			return (arr[0] == target) ? 1 : 0;
		}

		int nottake = countSubset_Recursion(arr, ind - 1, target);
		int take = 0;
		if (arr[ind] <= target) {
			take = countSubset_Recursion(arr, ind - 1, target - arr[ind]);
		}

		return nottake + take;

	}

	private static int countSubset_Memoization(int[] arr, int ind, int target, int[][] dp) {

		if (target == 0) {
			return 1;
		}
		if (ind == 0) {
			return (arr[0] == target) ? 1 : 0;
		}
		if (dp[ind][target] != -1) {
			return dp[ind][target];
		}
		int nottake = countSubset_Recursion(arr, ind - 1, target);
		int take = 0;
		if (arr[ind] <= target) {
			take = countSubset_Recursion(arr, ind - 1, target - arr[ind]);
		}
		return dp[ind][target] = nottake + take;

	}

	private static int countSubset_Tabulation(int[] arr, int n, int k) {

		int dp[][] = new int[n][k + 1];
		for (int i = 0; i < n; i++) {
			dp[i][0] = 1;
		}
		if (arr[0] <= k) {
			dp[0][arr[0]] = 1;
		}
		for (int i = 1; i < n; i++) {

			for (int target = 1; target <= k; target++) {
				int nottake = dp[i - 1][target];
				int take = 0;

				if (arr[i] <= target) {
					take = dp[i - 1][target - arr[i]];
				}
				dp[i][target] = nottake + take;
			}
		}
		return dp[n - 1][k];
	}
	
	private static int countSubset_Space(int[] arr, int n, int k) {

		int prev[] = new int[k+1];
		int cur[] = new int[k+1];
		cur[0] = 1;
		prev[0]= 1;
		if (arr[0] <= k) {
		prev[arr[0]] = 1;
		}
		
		for (int i = 1; i < n; i++) {
			for (int target = 1; target <= k; target++) {
				int nottake = prev[target];
				int take = 0;

				if (arr[i] <= target) {
					take = prev[target - arr[i]];
				}
				cur[target] = nottake + take;
			}
			prev = cur;
		}
		return prev[k];

	}

}
