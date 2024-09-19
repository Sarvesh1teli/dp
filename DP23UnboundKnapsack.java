package com.tuf;

import java.util.Arrays;

public class DP23UnboundKnapsack {
	public static void main(String[] args) {

		int wt[] = { 2, 4, 6 };
		int val[] = { 5, 11, 13 };
		int W = 10;
		int n = wt.length - 1;

		int dp[][] = new int[n + 1][W + 1];

		for (int row[] : dp) {
			Arrays.fill(row, -1);
		}

		System.out.println("recursion : " + recursion(n, W, wt, val));
		
		System.out.println("Memoization : " + memoization(n, W, wt, val,dp));
		System.out.println("Tabulation : " + tabulation(n, W, wt, val));
		
		System.out.println("spaceOptimization : " + spaceOptimization(n, W, wt, val));
	}

	private static int recursion(int ind, int W, int[] wt, int[] val) {

		/*if (ind == 0) {
			if(W>=wt[0]) {
				return ((int) (W / wt[0])) * val[0];
			}else {
				return 0;
			}
			
		}*/
		
		
		if (ind == 0) {
				return ((int) (W / wt[0])) * val[0];
		}

		int notPick = 0 + recursion(ind - 1, W, wt, val);
		int taken = Integer.MIN_VALUE;
		if (W >= wt[ind]) {
			taken = val[ind] + recursion(ind, W - wt[ind], wt, val);
		}
		return Math.max(notPick, taken);
	}
	
	private static int memoization(int ind, int W, int[] wt, int[] val,int dp[][]) {
		
		if (ind == 0) {
				return ((int) (W / wt[0])) * val[0];
		}
		if(dp[ind][W] != -1) {
			return dp[ind][W];
		}

		int notPick = 0 + recursion(ind - 1, W, wt, val);
		int taken = Integer.MIN_VALUE;
		if (W >= wt[ind]) {
			taken = val[ind] + recursion(ind, W - wt[ind], wt, val);
		}
		return dp[ind][W] = Math.max(notPick, taken);
	}
	
	private static int tabulation(int ind, int W, int[] wt, int[] val) {
		
		int dp[][] = new int[ind+1][W+1];
		
		for(int w=wt[0];w<=W;w++) {
			dp[0][w] = ((int) (w / wt[0])) * val[0];
		}
	
		for(int i=1;i<=ind;i++) {
			for(int cap=0;cap<=W;cap++) {
				
				int notPick = 0 + dp[i - 1][cap];
				int taken = Integer.MIN_VALUE;
				if (cap >= wt[i]) {
					taken = val[i] + dp[i][ cap - wt[i]];
				}
				dp[i][cap] = Math.max(notPick, taken);
			}
		}

		
		return dp[ind][W];
	}
	
private static int spaceOptimization(int ind, int W, int[] wt, int[] val) {
		
		int prev[] = new int[W+1];
		int cur[] = new int[W+1];
		
		for(int w=wt[0];w<=W;w++) {
			prev[w] = ((int) (w / wt[0])) * val[0];
		}
	
		for(int i=1;i<=ind;i++) {
			for(int cap=0;cap<=W;cap++) {
				
				int notPick = 0 + prev[cap];
				int taken = Integer.MIN_VALUE;
				if (cap >= wt[i]) {
					taken = val[i] + cur[ cap - wt[i]];
				}
				cur[cap] = Math.max(notPick, taken);
			}
			prev = cur;
		}

		
		return prev[W];
	}

}
