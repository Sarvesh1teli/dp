package com.tuf;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DP41LongestIncreasingSub {
	public static void main(String[] args) {

		//int arr[] = { 10, 9, 2, 5, 3, 7, 101, 18 };
		int arr[] = { 5,4,11,1,16,8 };
		int n = arr.length;
		longestIncreasingSubsequence(arr, n);
	}

	private static void longestIncreasingSubsequence(int[] arr, int n) {
		int dp[][] = new int[n][n + 1];

		for (int row[] : dp)
			Arrays.fill(row, -1);

		System.out.println("The length of the longest increasing subsequence is ");
		System.out.println("1.Recursion :" + recursion_getAns(arr, n, 0, -1));
		

		System.out.println("2.Memoization :" + memoization_getAns(arr, n, 0, -1, dp));
		/*
		 * Time Complexity: O(N*N) 
		 * Space Complexity: O(N*N) + O(N)
		 */

		System.out.println("3.Tabulation :" + tabulation_getAns(arr, n));
		/*
		 * Time Complexity: O(N*N)
		 * Reason: There are two nested loops.
		 * Space Complexity: O(N*N)
		 * Reason: We are using an external array of size ‘(N+1)*(N+1)’. 
		 * Stack Space is eliminated.
		*/
		
		
		System.out.println("4.Space :" + space_getAns(arr, n));
		/*
		 * Time Complexity: O(N*N)
		 * Reason: There are two nested loops.
		 * Space Complexity: O(N)
		 * Reason: We are only using two rows of size n.
		*/

		System.out.println("5.LIS_newApproach:" + LIS_newApproach(arr, n));

		System.out.println("\n6.LIS_newApproach_printh :") ;
		LIS_newApproach_print(arr, n);

		
	}

	private static int recursion_getAns(int[] arr, int n, int ind, int prev_ind) {

		if (ind == n) {
			return 0;
		}
		int notTake = 0 + recursion_getAns(arr, n, ind + 1, prev_ind);
		int take = 0;
		if (prev_ind == -1 || arr[ind] > arr[prev_ind]) {

			take = 1 + recursion_getAns(arr, n, ind + 1, ind);
		}
		return Math.max(notTake, take);
	}
	
	

	/*
	 * 
	 * ‘ind’ represents the index of the array. It can range from 0 to n-1.
	 * ‘prev_index’ also represents the index of the array. When we have not
	 * considered any element in our LIS, prev_index is -1. Therefore, prev_index
	 * can range from -1 to n-1. Now we cannot store the -1 index in our 2D array.
	 * Therefore, we would do a coordinate shift of one as follows:
	 */
	private static int memoization_getAns(int[] arr, int n, int ind, int prev_ind, int dp[][]) {

		if (ind == n) {
			return 0;
		}
		if (dp[ind][prev_ind + 1] != -1) {

			return dp[ind][prev_ind + 1];
		}
		int notTake = 0 + recursion_getAns(arr, n, ind + 1, prev_ind);
		int take = 0;
		if (prev_ind == -1 || arr[ind] > arr[prev_ind]) {

			take = 1 + recursion_getAns(arr, n, ind + 1, ind);
		}
		return dp[ind][prev_ind + 1] = Math.max(notTake, take);
	}

	private static int tabulation_getAns(int[] arr, int n) {

		int dp[][] = new int[n + 1][n + 1]; // by default it stored 0 base condition satisfied

		/*
		 * changing parameters are ind and prev_ind . 
		 * I. ind --> n-1 to 0 in opposite fashion ie recursion is starting from 0 to n-1.  
		 * II.prev_ind --> n-1 to -1 in opposite
		 * fashion ie recursion is starting from 0 to n-1 n-1 in prev_ind should be
		 * ind-1 bec prev_ind is prev of current index
		 */

		for (int ind = n - 1; ind >= 0; ind--) {
			for (int prev_index = ind - 1; prev_index >= -1; prev_index--) {

				int notTake = 0 + dp[ind + 1][prev_index + 1]; // bec it starts from -1 to n-1
				int take = 0;
				if (prev_index == -1 || arr[ind] > arr[prev_index]) {

					take = 1 + dp[ind + 1][ind + 1];
				}
				dp[ind][prev_index + 1] = Math.max(notTake, take);
			}
		}
		
		/*
		 * for (int i = 0; i < n; i++) { for (int prev_index = 0; prev_index < n;
		 * prev_index++) { System.out.print(dp[i][prev_index ]+""); }
		 * System.out.println(); }
		 */
		return dp[0][0];
	}
	
	private static int space_getAns(int[] arr, int n) {

		int curRow[] = new int[n + 1]; 
		int nextRow[] = new int[n + 1]; 

		for (int ind = n - 1; ind >= 0; ind--) {
			for (int prev_index = ind - 1; prev_index >= -1; prev_index--) {

				int notTake = 0 + nextRow[prev_index + 1]; // bec it starts from -1 to n-1
				int take = 0;
				if (prev_index == -1 || arr[ind] > arr[prev_index]) {

					take = 1 + nextRow[ind + 1];
				}
				curRow[prev_index + 1] = Math.max(notTake, take);
			}
			nextRow = curRow;
		}
		return nextRow[0];
	}
	
	
	private static int LIS_newApproach(int[] arr, int n) {

		int dp[] = new int[n]; // by default it stored 0 base condition satisfied

		for (int i = 0; i < n; i++) {
			dp[i] = 1;
		}

		int maxi = 0;
		for (int i = 0; i < n; i++) {
			for (int prev_index = 0; prev_index <= i; prev_index++) {
				
				if (arr[prev_index] < arr[i]) {
					dp[i] = Math.max(dp[i], 1 + dp[prev_index]);
				}

			}
			maxi = Math.max(maxi, dp[i]);
		}
		return maxi;
	}

	private static int LIS_newApproach_print(int[] arr, int n) {

		int dp[] = new int[n]; // by default it stored 0 base condition satisfied

		for (int i = 0; i < n; i++) {
			dp[i] = 1;
		}
		
		int[] hash=new int[n];
	    Arrays.fill(hash,1);
	    int lastIndex = 0;

		int maxi = 0;
		for (int i = 0; i < n; i++) {
			hash[i] = i;
			
			for (int prev_index = 0; prev_index <= i-1; prev_index++) {
				
				if (arr[prev_index] < arr[i] && 1 + dp[prev_index] > dp[i]) {
					dp[i] =  1 + dp[prev_index];
					hash[i] = prev_index;
				}

			}
			if(dp[i] > maxi) {
				maxi = dp[i];
				lastIndex = i;
			}
			maxi = Math.max(maxi, dp[i]);
		}
		
		System.out.println("las "+lastIndex);
		System.out.println("hash[lastIndex] "+hash[lastIndex]);

		List temp = new ArrayList<>();
		temp.add(arr[lastIndex]);
		
		
		while(hash[lastIndex] != lastIndex) {
			lastIndex = hash[lastIndex];
			temp.add(arr[lastIndex]);
			
		}
		
		System.out.println("-elements are --");
		  for (int i = temp.size()-1; i >=0; i--) { 
				  System.out.print(temp.get(i)+" "); 
			  }
			  System.out.println();
		 
	
		return maxi;
	}
}
