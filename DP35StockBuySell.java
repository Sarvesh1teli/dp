package com.tuf;

public class DP35StockBuySell {
	public static void main(String[] args) {
		int[] arr  ={7,1,5,3,6,4};

		
		  System.out.println("The maximum profit by selling the stock is "+
		  maximumProfit(arr));
	}

	private static int maximumProfit(int[] arr) {
		
		int profit = 0;
		int min = arr[0];
		
		for(int i=1;i<arr.length;i++) {
			int curProfit = arr[i]- min;
			profit = Math.max(profit, curProfit);
			min = Math.min(min, arr[i]);
		}
		
		return profit;
	}
}
