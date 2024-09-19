package com.tuf;

//Partition A Set Into Two Subsets With Minimum Absolute Sum Difference
//https://takeuforward.org/data-structure/partition-set-into-2-subsets-with-min-absolute-sum-diff-dp-16/


public class DP16MinSubsetSumDifference {
	
	public static void main(String[] args) {
		
		//int arr[] = { 2, 3, 3, 3, 4, 5 };   //{2,3,5}, {3,3,4} sum = 10, 10-10 =0 ;
		
		//int arr[] = { 8, 6, 5 };  //3
		
		int arr[] = { 3, 1, 5,2,8 };  //1
		//int arr[] = { 1, 2, 3,4 };  //0
		int n = arr.length;

		int totalSum = 0;
		
		for(int i=0;i<n;i++) {
			totalSum += arr[i];
		}
		
		System.out.println(subsetSumUtil_Tabulation(n-1,totalSum,arr));
		
	}
	
	
	static int subsetSumUtil_Tabulation(int ind,int k,int a[]) {
		
		boolean dp[][] = new boolean[ind+1][k+1];
		
		for(int i=0;i<=ind;i++) {
			dp[i][0] = true;
			System.out.print(dp[i][0]+" ");		
		}
		
		System.out.println();
		if(a[ind]<=k) {
			dp[0][a[0]] = true;
		}
		
		for(int i =1;i<=ind;i++) {
			for(int target = 1;target<=k;target++) {
				
				boolean nonTaken = dp[i-1][target];
				boolean taken = false;
				
				if(a[i]<=target) {
					taken = dp[i-1][target-a[i]];
				}
				
				dp[i][target]= taken || nonTaken;
				System.out.print(dp[ind][target] +" ");

			}
			System.out.println();
		}
		//return dp[ind][k];
		
		int mini = (int) 1e9;
		
		for(int i=0;i<=k/2;i++) {   
			if(dp[ind][i] == true) {
				int s1 = i;
				int s2 = k-s1;
				int diff = Math.abs(s1-s2);  //k-s1 means s2 ie s2-s1.  k->totalsum
				mini = Math.min(mini, diff);
			}
		}
		return mini;
	}
}
