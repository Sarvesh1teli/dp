package com.tuf;

import java.util.Arrays;

public class DP1Fibonacci {
	public static void main(String[] args) {
		int n = 5;
		System.out.println("-------Using Recursion--------");
		int value = fibRec(n);
		System.out.println("recursion value:"+value);
		
		System.out.println("\n-------DP using Memoization--------");
		int dp[] = new int[n+1];
		Arrays.fill(dp, -1);
		int mem = fibMemoization(n,dp);
		System.out.println("DP memoization value:"+mem);
		System.out.println("DP memoization Time Complexity: O(N) and Space Complexity:=O(n)+O(n)");
		
		System.out.println("\n-------DP using Tabulation--------");
		int dp1[] = new int[n+1];
		Arrays.fill(dp1, -1);
		fibonaccUsingTabulation(n,dp1);
		System.out.println("DP Tabulation Time Complexity: O(N) and Space Complexity:=O(n)");
		
		System.out.println("\n-------DP using Space optimization--------");
		fibonaccUsingSpaceOptimization(n);
		System.out.println("DP Space optimization Time Complexity: O(N) and Space Complexity: O(1)");
	}

	private static int fibRec(int n) {
		if(n <= 1) {
			return n;
		}
		return fibRec(n-1)+fibRec(n-2);
	}

	private static int fibMemoization(int n,int dp[]) {
		System.out.println("dp[ " + n +" ]" + dp[n]);
		
		if(n <= 1) {
			return n;
		}
		
		if(dp[n] !=-1) {
			return dp[n];
		}
		
		return dp[n] = fibMemoization(n-1,dp)+fibMemoization(n-2,dp);
	}
	
	
	private static void fibonaccUsingTabulation(int n,int dp[]) {
		 dp[0]=0;
		 dp[1]=1;
		 for(int i=2;i<=n;i++) {
			 dp[i] = dp[i-1]+dp[i-2];
		 }
		 
		 System.out.println(dp[n]);
	}
	
	private static void fibonaccUsingSpaceOptimization(int n) {
		 
		 int prev_1 = 1;
		 int prev_2 = 0;
		 
		 for(int i=2;i<=n;i++) {
			 int cur_i = prev_1+prev_2;
			 prev_2 = prev_1;
			 prev_1 = cur_i;
		 }
		 
		 System.out.println(prev_1);
	}
}
