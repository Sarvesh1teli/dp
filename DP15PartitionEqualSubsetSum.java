package com.tuf;

import java.util.Arrays;

//https://takeuforward.org/data-structure/partition-equal-subset-sum-dp-15/


//We need to find if we can partition the array into two subsets such that the sum 
//of elements of each subset is equal to the other.



public class DP15PartitionEqualSubsetSum {

	public static void main(String[] args) {

		int arr[] = { 2, 3, 3, 3, 4, 5 };
		int n = arr.length;

		int totalSum = 0;
		
		for(int i=0;i<n;i++) {
			totalSum += arr[i];
		}
		
		int k = totalSum/2;;
		System.out.println(totalSum);
		
		if(totalSum%2==1) {
			System.out.println("The Array cannot be partitioned into two equal subsets");
		}else {
			boolean b = recEqualSumPartition(n-1,k,arr);
			if(b) {
				 System.out.println("The Array can be partitioned into two equal subsets");
			}
		}
		
		int dp[][] = new int[n][k+1];
		
		for(int row[]:dp) {
			Arrays.fill(row, -1);
		}
		
		System.out.println("\nMemorization Approach");
		boolean mem = memoizationEqualSumPartition(n-1,k,arr,dp);
		
		if(mem) {
			 System.out.println("The Array can be partitioned into two equal subsets");
		}else {
			System.out.println("The Array cannot be partitioned into two equal subsets");
		}
		
		System.out.println("Time Complexity: O(N*K) + O(N)");
		System.out.println("Reason:There are (N*K) states therefore at max ‘N*K’ "
				+ "new problems will be solved and we are running a for loop for "
				+ "‘N’ times to calculate the total sum");

		System.out.println("\nSpace Complexity: O(N*K) + O(N)");
		System.out.println("Reason: We are using a recursion stack space(O(N)) and a 2D array ( O(N*K)).)");

		System.out.println("\n Tabulation Approach");
		
		boolean tab = TabulationEqualSumPartition(n-1,k,arr);
		if(tab) {
			 System.out.println("The Array can be partitioned into two equal subsets");
		}else {
			System.out.println("The Array cannot be partitioned into two equal subsets");
		}
		System.out.println("Time Complexity: O(N*K) + O(N)");
		System.out.println("Reason: There are two nested loops that account for O(N*K) and at"
				+ " starting we are running a for loop to calculate totSum.");
		
		System.out.println("\nSpace Complexity: O(N*K)");
		System.out.println("Reason: We are using an external array of size ‘N*K’. Stack Space is eliminated.");

	}

	private static boolean recEqualSumPartition(int ind, int target,int a[]) {
		
		if(target == 0) {
			return true;
		}
		if(ind == 0) {
			return a[0]==target;
		}
		
		boolean nonTaken = recEqualSumPartition(ind-1,target,a);
		boolean taken = false;
		if(a[ind]<=target) {
			taken = recEqualSumPartition(ind-1,target-a[ind],a);
		}
		return nonTaken|| taken;
	}

	
	private static boolean memoizationEqualSumPartition(int ind, int target,int a[],int dp[][]) {
		
		if(target == 0) {
			return true;
		}
		if(ind == 0) {
			return a[0]==target;
		}
		
		if(dp[ind][target] != -1) {
			return dp[ind][target] == 0?false:true;
		}
		
		boolean nonTaken = recEqualSumPartition(ind-1,target,a);
		boolean taken = false;
		if(a[ind]<=target) {
			taken = recEqualSumPartition(ind-1,target-a[ind],a);
		}
		dp[ind][target] = nonTaken|| taken ?1:0;
		return nonTaken|| taken;
	}
	
private static boolean TabulationEqualSumPartition(int n, int k,int a[]) {
		
	boolean dp[][] = new boolean[n][k+1];
		for(int i=0;i<n;i++) {
			dp[i][0] = true;
		}
		if(k >= a[n]) {
			dp[0][a[n]] = true;
		}
		for(int i=1;i<n;i++) {
			for(int target=1;target<=k;target++) {
				boolean nonTaken = dp[n-1][target];
				boolean taken = false;
				if(a[n]<=target) {
					taken = dp[n-1][target-a[n]];
				}
				dp[i][target] = nonTaken|| taken;
			}
		}
		return dp[n-1][k];
	}
}
