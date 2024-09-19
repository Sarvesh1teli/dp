package com.tuf;

import java.util.Arrays;

public class DP21SumTarget {
	public static void main(String[] args) {

		int arr[] = {1,2,3,1};
		int target=3;
		System.out.println("The number of subsets found are " + countPartitions(target, arr,"recursion"));
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

		return rec_TargetSum(n-1, s2, arr);
		
		//return countPartitionsUtil_Memization(n-1, s2, arr,dp);
	}

	private static int rec_TargetSum(int ind, int target,int arr[]) {
		
		/*if (target == 0) {
			return 1;
		}
		if (ind == 0) {
			return (arr[0] == target) ? 1 : 0;
		}
		*/
		if(ind == 0){
	        if(target==0 && arr[0]==0)
	            return 2;
	        if(target==0 || target == arr[0])
	            return 1;
	        return 0;
	    }
		
		int nontake = rec_TargetSum(ind-1,target,arr);
		int take = 0;
		if(arr[ind] <= target) {
			take = rec_TargetSum(ind-1,target-arr[ind],arr);
		}
		return (nontake+take);
		
	}
	
	


}
