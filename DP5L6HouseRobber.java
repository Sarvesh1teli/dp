package com.tuf;

import java.util.*;

public class DP5L6HouseRobber {
	public static void main(String[] args) {
	
		int a[]= {2,1,4,9};
		List<Integer> temp1 = new ArrayList();
		List temp2 = new ArrayList();
		
		for(int i=0;i<a.length;i++) {
			if(i!=0) {
				temp1.add(a[i]);
			}
			
			if(i!=a.length-1) {
				temp2.add(a[i]);
			}
		}
		
		int cc = Math.max(houseRobberUsingSpaceOptimization(temp1,temp1.size()), houseRobberUsingSpaceOptimization(temp2,temp2.size()));
		System.out.println(cc);
	}
	private static int houseRobberUsingSpaceOptimization(List<Integer> arr, int n) {

		int prev1 = (int) arr.get(0);
		int prev2 = 0;
		int cur = 0;
		for(int i=1;i<n;i++) {
			int pick = arr.get(i);
			if(i>1) {
				pick += prev2;// like dp[i-2]
			}
			int  nonPick = 0+prev1; // like dp[i-1]
			
			cur = Math.max(pick, nonPick);
			prev2 = prev1;
			prev1 = cur;
		}
		return prev1;
	}
}
