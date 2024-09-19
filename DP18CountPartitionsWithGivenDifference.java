package com.tuf;

import java.util.Arrays;

/* 
 * 	Part 2: Count Partitions with Given Difference   {6+4} - {5+2} = 3  , no. of partition = 1
	
	https://takeuforward.org/data-structure/count-partitions-with-given-difference-dp-18/
*/

public class DP18CountPartitionsWithGivenDifference {
	static int mod =(int)(Math.pow(10,9)+7);
	public static void main(String[] args) {

		int arr[] = {1, 2, 3, 1, 2};
		int d = 1;

		System.out.println("The number of subsets found are " + countPartitions(d, arr,"recursion"));
	}

	static int countPartitions(int d, int[] arr,String way) {
		int n = arr.length;
		int totSum = 0;
		
		for(int i=0; i<arr.length;i++){
	        totSum += arr[i];
	    }
		
		//Checking for edge cases  9,2,5,3,4,1 = 24-3=21/2
		if(totSum-d<0) return 0;
		if((totSum-d)%2==1) return 0;
		
		int s2 = (totSum-d)/2;
		int dp[][] = new int[n][s2 + 1];
		
		for(int row[]: dp)
		    Arrays.fill(row,-1);

		return countPartitionsUtil_Recursion(n-1, s2, arr);
		
		//return countPartitionsUtil_Memization(n-1, s2, arr,dp);
	}

	static int countPartitionsUtil_Recursion(int ind, int target, int[] arr) {

		if (target == 0) {
			return 1;
		}
		if (ind == 0) {
			return (arr[0] == target) ? 1 : 0;
		}
		
		int nontake = countPartitionsUtil_Recursion(ind-1,target,arr);
		int take = 0;
		if(arr[ind] <= target) {
			take = countPartitionsUtil_Recursion(ind-1,target-arr[ind],arr);
		}
		return (nontake+take)%mod;
	}
	
	static int countPartitionsUtil_Memization(int ind, int target, int[] arr, int[][] dp) {

		if(ind == 0){
	        if(target==0 && arr[0]==0)
	            return 2;
	        if(target==0 || target == arr[0])
	            return 1;
	        return 0;
	    }
	    
	    if(dp[ind][target]!=-1)
	        return dp[ind][target];
	        
	    int notTaken = countPartitionsUtil_Memization(ind-1,target,arr,dp);
	    
	    int taken = 0;
	    if(arr[ind]<=target)
	        taken = countPartitionsUtil_Memization(ind-1,target-arr[ind],arr,dp);
	        
	    return dp[ind][target]= (notTaken + taken)%mod;
	}
	
	
	static int countPartitionsUtil_Tabulation(int ind, int target, int[] arr) {

		int n = arr.length;

	    int dp[][] = new int[n][target+1];
	    
	    if(arr[0] == 0) 
	    	dp[0][0] = 2;  // 2 cases -pick and not pick
	    else dp[0][0] = 1;  // 1 case - not pick
	    
	    if(arr[0]!=0 && arr[0]<=target)
	    	dp[0][arr[0]] =1;
	    
	    for (int i = 1; i < n; i++) {

			for (int sum = 0; sum <= target; sum++) {
				int nottake = dp[i - 1][sum];
				int take = 0;

				if (arr[i] <= sum) {
					take = dp[i - 1][sum - arr[i]];
				}
				dp[i][sum] = (nottake + take)%mod;
			}
		}
		return dp[n - 1][target];
	}
	
	static int countPartitionsUtil_SpaceOptimization(int ind, int target, int[] arr, int[][] dp) {
		
		int n = arr.length;
		
		int prev[] = new int[target+1];
		
		if(arr[0] == 0) prev[0] =2;  // 2 cases -pick and not pick
	    else prev[0] = 1;  // 1 case - not pick
	    
	    if(arr[0]!=0 && arr[0]<=target) prev[arr[0]] = 1;  // 1 case -pick
	    
	    for (int i = 1; i < n; i++) {
	    	int cur[]=new int[target+1];
			for (int sum = 0; sum <= target; sum++) {
				int nottake = prev[sum];
				int take = 0;

				if (arr[i] <= sum) {
					take = prev[sum - arr[i]];
				}
				cur[sum] = (nottake + take)%mod;
			}
			prev = cur;
		}
		return prev[target];
	}

}
