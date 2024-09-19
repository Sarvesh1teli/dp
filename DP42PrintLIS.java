package com.tuf;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DP42PrintLIS {
	public static void main(String[] args) {
		// int arr[] = { 10, 9, 2, 5, 3, 7, 101, 18 };
		//int arr[] = { 5, 4, 11, 1, 16, 8 };
		int arr[]  = {1,16,7,8,4};
		int n = arr.length;
		longestIncreasingSubsequence(arr, n);
	}

	private static void longestIncreasingSubsequence(int[] arr, int n) {

		System.out.println(".LIS_newApproach_printh :");
		LIS_newApproach_print(arr, n);

	}
	private static int LIS_newApproach_print(int[] arr, int n) {

		 Arrays.sort(arr);
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
				
				if (arr[i] % arr[prev_index] == 0 && 1 + dp[prev_index] > dp[i]) {
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
		System.out.println(temp);
		
		System.out.println("-elements are --");
		  for (int i = temp.size()-1; i >=0; i--) { 
				  System.out.print(temp.get(i)+" "); 
			  }
			  System.out.println();
		 
	
		return maxi;
	}
}
