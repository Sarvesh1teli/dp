package com.tuf;

import java.util.Arrays;

//We can’t buy a stock on the very next day of selling it. This is the cooldown clause.

public class DP39BuySellCooldown {
	public static void main(String[] args) {

		int prices[] = { 4, 9, 0, 4, 10 };
		System.out.println("The maximum profit that can be generated is " + stockProfit(prices));
		/*
			Time Complexity: O(N*2) 
			Space Complexity: O(N*2) + O(N)
			Reason: We are using a recursion stack space(O(N)) and a 2D array ( O(N*2)).
		*/
		
		System.out.println("The maximum profit that can be generated is " 
		+ tabulation_getAns(prices));

		/*
		Output:
			The maximum profit that can be generated is 11
			Time Complexity: O(N*2) 
			Reason: There are two nested loops that account for O(N*2) complexity.
			Space Complexity: O(N*2)
			Reason: We are using an external array of size ‘N*2’. Stack Space is eliminated.
			*/
	}

	private static int stockProfit(int[] prices) {
		int n = prices.length;
		int dp[][] = new int[n][2];
		for (int row[] : dp)
			Arrays.fill(row, -1);

		int ans = getAns(prices, 0, 0, n, dp);
		return ans;
	}

	private static int getAns(int[] prices, int ind, int buy, int n, int[][] dp) {
		
		if(ind >= n ) {
			return 0;
		}
		/*
		int profit = 0 ;
		if(buy==0) {
			
			profit =  Math.max(-prices[ind]+
					getAns(prices,ind+1,1,n,dp),
					getAns(prices,ind+1,0,n,dp)
					);
		}else if(buy==1) {
			profit = Math.max(prices[ind]+
					getAns(prices,ind+2,0,n,dp), // cant buy immediatly
					getAns(prices,ind+1,1,n,dp)
					);
		}
		
		return profit;*/
		
		
		if (dp[ind][buy] != -1) {
			return dp[ind][buy];
		}
		if (buy == 0) {

			return dp[ind][buy] = Math.max(-prices[ind] + getAns(prices, ind + 1, 1, n, dp),
					getAns(prices, ind + 1, 0, n, dp));
		} else {
			return dp[ind][buy] = Math.max(prices[ind] + getAns(prices, ind + 2, 0, n, dp), // cant buy immediatly after
																							// selling
					getAns(prices, ind + 1, 1, n, dp));
		}
	}
	
	static int tabulation_getAns(int[] Arr) {
		int n = Arr.length;
		int dp[][] = new int[n + 2][2];

		for (int ind = n - 1; ind >= 0; ind--) {
			for (int buy = 0; buy <= 1; buy++) {
				int profit = 0;

				if (buy == 0) {// We can buy the stock
					profit = Math.max(0 + dp[ind + 1][0], -Arr[ind] + dp[ind + 1][1]);
				}

				if (buy == 1) {// We can sell the stock
					profit = Math.max(0 + dp[ind + 1][1], Arr[ind] + dp[ind + 2][0]);
				}

				dp[ind][buy] = profit;
			}
		}
		return dp[0][0];
	}
	
}
