package com.tuf;

import java.util.Arrays;

//https://takeuforward.org/data-structure/0-1-knapsack-dp-19/
//We need to find the maximum value of items that the thief can steal.
//Thief can select items valued 8 and 5 with weight 4 and 1

public class DP19Knapsack {
	public static void main(String[] args) {

		int wt[] =  { 1, 2, 4, 5 };
		int val[] = { 5, 4, 8, 6 };
		int W = 5;

		int n = wt.length;

		 knapsack(wt, val, n, W);

	}

	private static void knapsack(int[] wt, int[] val, int n, int w) {
		
		
		System.out.println("1:The Maximum value of items, thief can steal is " + recursion_knapSack(wt,val,n-1,w));
		System.out.println("recursion_knapSack Complexity: O(2^n)~= exponentional " );
		//Reason: There are N*W states therefore at max ‘N*W’ new problems will be solved.
		System.out.println("recursion_knapSack Complexity: O(N) " );
		//Reason: We are using a recursion stack space(O(N)) .
		
		System.out.println("\n");
		
		int dp[][] = new int[n][w+1];
		for(int row[]:dp) {
			Arrays.fill(row, -1);
		}
		System.out.println("2:The Maximum value of items, thief can steal is " + memoization_knapSack(wt,val,n-1,w,dp));

		System.out.println("memoization_knapSack:Time Complexity: O(N*W) " );
		//Reason: There are N*W states therefore at max ‘N*W’ new problems will be solved.
		System.out.println("memoization_knapSack:Space Complexity: O(N*W) + O(N) " );
		//Reason: We are using a recursion stack space(O(N)) and a 2D array ( O(N*W)).

		System.out.println("\n");
		
		System.out.println("3:The Maximum value of items, thief can steal is " + tabulation_knapSack(wt,val,n-1,w));
		System.out.println("tabulation_knapSack Complexity: O(N*W) " );
		//Reason: There are two nested loops

		System.out.println("tabulation_knapSack Complexity: O(N*W) " );
		//Reason: We are using an external array of size ‘N*W’. Stack Space is eliminated.

		System.out.println("\n");
		
		
		System.out.println("4:spaceOptimi_knapSack, thief can steal is " + spaceOptimi_knapSack(wt,val,n-1,w));
		System.out.println("spaceOptimi_knapSack Complexity: O(N*W) " );
		//Reason: There are two nested loops.

		System.out.println("spaceOptimi_knapSack Complexity: O(W) " );
		//Reason: We are using an external array of size ‘W+1’ to store only one row.

		
		System.out.println("4:spaceOptimi_knapSackUisng_SingleArry, thief can steal is " + spaceOptimi_knapSackUisng_SingleArry(wt,val,n-1,w));

	}

	private static int recursion_knapSack(int[] wt, int[] val, int ind, int W) {
		System.out.println("W:"+W);
		if(ind == 0) {
			if(wt[0]<=W) {
				return val[0];
			}
			return 0;
		}
		
		int take = Integer.MIN_VALUE;
		if(W >=wt[ind]) {
			take = val[ind]+recursion_knapSack(wt,val,ind-1,W-wt[ind]);
			System.out.println("take:"+take);
		}
		int nonTake = 0+recursion_knapSack(wt,val,ind-1,W);
		
		return Math.max(nonTake, take);
	}
	
	private static int memoization_knapSack(int[] wt, int[] val, int ind, int W,int dp[][]) {
		if(ind == 0) {
			if(wt[0]<=W) {
				return val[0];
			}
			return 0;
		}
		if(dp[ind][W] != -1) {
			return dp[ind][W];
		}
		int nonTake = 0+recursion_knapSack(wt,val,ind-1,W);
		int take = Integer.MIN_VALUE;
		if(W >=wt[ind]) {
			take = val[ind]+recursion_knapSack(wt,val,ind-1,W-wt[ind]);
		}
		return dp[ind][W] = Math.max(nonTake, take);
	}
	
	
	private static int tabulation_knapSack(int[] wt, int[] val, int n, int W) {
		int dp[][] = new int[n+1][W+1];
		
		for(int i=wt[0];i<=W;i++) {
			dp[0][i] = val[0];
		}
		
		for(int ind = 1;ind<=n;ind++ ) {
			for(int cap = 0;cap<=W;cap++ ) {
				int nonTake = 0+dp[ind-1][cap];
				int take = Integer.MIN_VALUE;
				if(cap >=wt[ind]) {
					take = val[ind]+dp[ind-1][cap-wt[ind]];
				}
				dp[ind][cap] = Math.max(nonTake, take);
			}
		}
		return dp[n][W];
	}
	
	private static int spaceOptimi_knapSack(int[] wt, int[] val, int n, int W) {
		int prev[] = new int[W+1];
		for(int i=wt[0];i<=W;i++) {
			prev[i] = val[0];
		}
		for(int ind = 1;ind<=n;ind++ ) {
			int cur[] = new int[W+1];
			cur[0] = val[0];
			for(int cap = 0;cap<=W;cap++ ) {
				int nonTake = 0+prev[cap];
				int take = Integer.MIN_VALUE;
				if(cap >=wt[ind]) {
					take = val[ind]+prev[cap-wt[ind]];
				}
				cur[cap] = Math.max(nonTake, take);
			}
			prev = cur;
		}
		return prev[W];
	}

	private static int spaceOptimi_knapSackUisng_SingleArry(int[] wt, int[] val, int n, int W) {
		int prev[] = new int[W+1];
		for(int i=wt[0];i<=W;i++) {
			prev[i] = val[0];
		}
		for(int ind = 1;ind<=n;ind++ ) {
			for(int cap = W;cap>=0;cap-- ) {
				int nonTake = 0+prev[cap];
				int take = Integer.MIN_VALUE;
				if(cap >=wt[ind]) {
					take = val[ind]+prev[cap-wt[ind]];
				}
				prev[cap] = Math.max(nonTake, take);
			}
		}
		return prev[W];
	}
}
