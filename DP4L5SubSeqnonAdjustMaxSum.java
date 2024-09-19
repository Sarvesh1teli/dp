package com.tuf;

import java.util.Arrays;

public class DP4L5SubSeqnonAdjustMaxSum {
	public static void main(String[] args) {
		
		
		System.out.println("Maximum sum of non-adjacent elements (DP 5) ");
		int arr[]= {1,2,3,1,3,5,8,1,9}; //{1,2,4};//{2,1,4,9};
		int n = arr.length;
		System.out.println("-------Using Recursion--------");
		int maxSum = subSeqUsingRecursion(arr,n-1);
		System.out.println("Maximum sum of non-adjacent elements : "+maxSum);
		System.out.println("recursion Time Complexity: Exponentional n(2^n) and Space Complexity:=O(n)+O(n)");

		System.out.println("-------Using Recursion2--------");
		 maxSum = subSeqUsingRecursion2(arr,0);
		System.out.println("Maximum sum of non-adjacent elements : "+maxSum);
		System.out.println("recursion Time Complexity: Exponentional n and Space Complexity:=O(n)+O(n)");

		
		
		System.out.println("-----------------------------------------------");
		System.out.println("Convert recursion -->DP ie: Using memoization");
		int dp[] = new int[n];
		Arrays.fill(dp, -1);
		maxSum = subSeqUsingMemoization(arr,n-1,dp);
		System.out.println("Maximum sum of non-adjacent elements : "+maxSum);
		System.out.println("DP-memoization Time Complexity: O(N) and Space Complexity:=O(N) + O(N) ≈ O(N))");
		System.out.println("-----------------------------------------------");
	
		System.out.println("******* Using DP-Tabulation ******* ");
		maxSum = subSeqUsingTabulation(arr,n);
		System.out.println("Maximum sum of non-adjacent elements : "+maxSum);
		System.out.println("DP-Tabulation Time Complexity: O(N) and Space Complexity:=O(N) ");

		System.out.println("-----------------------------------------------");
		System.out.println("\n-------DP using SpaceOptimization--------");
		maxSum = subSeqUsingUsingSpaceOptimization(arr,n);
		System.out.println("Maximum sum of non-adjacent elements : "+maxSum);
		System.out.println("DP-Tabulation Time Complexity: O(N) and Space Complexity:=O(1) ");

	}

	private static int subSeqUsingUsingSpaceOptimization(int[] arr, int n) {

		int prev1 = arr[0];
		int prev2 = 0;
		int cur = 0;
		for(int i=1;i<n;i++) {
			int pick = arr[i];
			if(i>1) {
				pick += prev2;// like dp[i-2]
			}
			int  nonPick = 0+prev1; // like dp[i-1]
			
			cur = Math.max(pick, nonPick);
			prev2 = prev1;
			prev1 = cur;
		}
		return prev1;
	}

	private static int subSeqUsingTabulation(int[] arr, int n) {
		int[] dp = new int[n];
		
		Arrays.fill(dp, -1);
		dp[0] = arr[0];
		
		for(int i = 1;i<n;i++) {
			
			int pick = arr[i] ;
			if(i>1) {
				pick  += dp[i-2];
			}
			
			int nonPick = 0+dp[i-1];
			dp[i] = Math.max(pick, nonPick);
		}
		return dp[n-1];
	}

	private static int subSeqUsingMemoization(int[] arr, int n, int[] dp) {
		
		if(n<0) {
			return 0;
		}
		
		if(n==0) {
			return arr[0];
		}
		if(dp[n] != -1) {
			return dp[n];
		}
		
		int pick = arr[n] + subSeqUsingRecursion(arr,n-2);
		int nonPick = 0 + subSeqUsingRecursion(arr,n-1);
		return dp[n] = Math.max(pick, nonPick);
	}

	private static int subSeqUsingRecursion(int[] arr, int ind) {
		if(ind<0) {
			return 0;
		}
		
		if(ind==0) {
			return arr[0];
		}
		
		int pick = arr[ind] + subSeqUsingRecursion(arr,ind-2);
		int nonPick = 0 + subSeqUsingRecursion(arr,ind-1);
		return Math.max(pick, nonPick);
	}
	
	private static int subSeqUsingRecursion2(int[] arr, int ind) {
		if(ind >=arr.length) {
			return 0;
		}
		
		if(ind==arr.length-1) {
			return arr[arr.length-1];
		}
		
		int pick = arr[ind] + subSeqUsingRecursion2(arr,ind+2);
		int nonPick = 0 + subSeqUsingRecursion2(arr,ind+1);
		return Math.max(pick, nonPick);
	}
}
