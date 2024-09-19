package com.tuf;

import java.util.Arrays;

public class DP2CountNumOfwaysReachStarir {
	
	public static void main(String[] args) {
		System.out.println("Its similar to fibonacci series");
		
		int n = 4;
		
		solveusingSpaceOptimize(n);
		
		int countWays = staircaseRec_1(n);
		System.out.println("countWays:"+countWays);
		
		
		int dp [] = new int[n+1];
		Arrays.fill(dp, -1);
		
		
		countWays = staircaseMemoization_2(n,dp);
		System.out.println("Mem countWays:"+countWays);
		
		int dp1 [] = new int[n+1];
		Arrays.fill(dp1, -1);
		countWays = staircaseTabulation_3(n,dp1);
		System.out.println("Tabulation countWays:"+countWays);
	}

	private static void solveusingSpaceOptimize(int n) {
		
		int dp_0 = 1;
		int dp_1 = 1;
		
		for(int i=2;i<=n;i++) {
			int cur = dp_0+dp_1;
			dp_0 = dp_1;
			dp_1 = cur;
		}
		System.out.println("Number of ways:"+dp_1);
	}

	
	static int staircaseRec_1(int ind) {
		
		if(ind == 0)
			return 1;
		
		if(ind == 1)
			return 1;
		
		int l = staircaseRec_1(ind-1);
		int r = staircaseRec_1(ind-2);
		
		return l+r;

	}
	
	static int staircaseMemoization_2(int ind,int dp[]) {
		
		if(ind == 0)
			return 1;
		
		if(ind == 1)
			return 1;
		
		if(dp[ind] != -1) {
			return dp[ind];
		}
		int l = staircaseRec_1(ind-1);
		int r = staircaseRec_1(ind-2);
		return dp[ind]=l+r;

	}
	
	static int staircaseTabulation_3(int ind,int dp1[]) {
		
		dp1[0] = 1;
		dp1[1] = 1;
		for(int i = 2;i<= ind;i++) {
			dp1[i] = dp1[i-1]+dp1[i-2];
			
		}
		return dp1[ind];

	}
}
