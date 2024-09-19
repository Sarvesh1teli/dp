package com.tuf;

import java.util.Arrays;

public class DP20MinCoin {
	public static void main(String[] args) {
		int arr[] ={1,2,3};
		int T=7;
		                                 
	System.out.println("The minimum number of coins required to form the target sum is ");
	
		minimumElements(arr,T);
	}

	private static void minimumElements(int[] arr, int t) {
		
		System.out.println("Recursion Time Complexity: O(2^n) " );

		System.out.println(rec_MinCoinChange(arr.length-1,t,arr));
		
		System.out.println("\n");
		System.out.println("memoization:Time Complexity: O(N*T) " );
		//Reason: There are N*T states therefore at max ‘N*T’ new problems will be solved.
		System.out.println("memoization:Space Complexity:O(N*T) + O(N)" );
		//Reason: We are using a recursion stack space(O(N)) and a 2D array ( O(N*T)).

		System.out.println("\n");
		System.out.println("3:Tabulation:"+tab_MinCoinChange(arr.length-1,t,arr));
		System.out.println("tab_MinCoinChange:Time Complexity: O(N*T)) " );
		//Reason: There are two nested loops
		System.out.println("tab_MinCoinChange:Space Complexity: O(N*T)" );
		//Reason: We are using an external array of size ‘N*T’. Stack Space is eliminated.

		
		
		System.out.println("\n");
		System.out.println("4:SpaceOptimisation :"+spaceOptimisation_MinCoinChange(arr.length-1,t,arr));
		System.out.println("SpaceOptimisation Time Complexity: O(N*T)" );
		//Reason: There are two nested loops.

		System.out.println("spaceOptimisation_MinCoinChange Space Complexity: O(T) " );
		//Reason: We are using two external arrays of size ‘T+1’.


		
		System.out.println("MAX coinf extra ");
		System.out.println(rec_MaxCoinChange(arr.length-1,t,arr));
		
		System.out.println("No fo ways");

		System.out.println(rec_NoofWaysCoinChange(arr.length-1,4,arr));
	}
	
	

	static int rec_MinCoinChange(int ind, int target,int a[]){
		if(ind==0) {
			if(target%a[0] == 0) {
				return target/a[0] ;
			}
			else return (int)Math.pow(10,9);
		}
		int take = (int)1e9;
		if(target>=a[ind]) {
			take = 1+rec_MinCoinChange(ind,target-a[ind],a);
		}
		int nonTaken = 0+rec_MinCoinChange(ind-1,target,a);
		
		return Math.min(nonTaken, take);
		
	}

	static int tab_MinCoinChange(int ind, int target,int a[]){
		
		int dp[][] = new int[ind+1][target+1];
		
		for(int T=0;T<=target;T++) {
			if(T%a[0] == 0) {
				dp[0][T] = T/a[0] ;
			}else {
				dp[0][T] = (int)Math.pow(10,9);
			}
		}
		for(int i=1;i<=ind;i++) {
			for(int T=0;T<=target;T++) {
				int nonTaken = 0+dp[i-1][T];
				int take = (int)1e9;
				if(T>=a[i]) {
					take = 1+dp[i][T-a[i]];
				}
				dp[i][T] = Math.min(nonTaken, take);
			}
		}
		
		return dp[ind][target];
		
	}
	
	static int spaceOptimisation_MinCoinChange(int ind, int target,int a[]){
		
		int prev[] = new int [target+1];
		//cur[0] = i/a[0] ;
		
		for(int i=0;i<=target;i++) {
			if(i%a[0] == 0) {
				prev[i] = i/a[0] ;
			}else {
				prev[i] = (int)Math.pow(10,9);
			}
		}
		for(int i=1;i<=ind;i++) {
			int cur[] = new int [target+1];
			for(int T=0;T<=target;T++) {
				int nonTaken = 0+prev[T];
				int take = (int)1e9;
				if(T>=a[i]) {
					take = 1+cur[T-a[i]];
				}
				cur[T] = Math.min(nonTaken, take);
			}
			prev = cur;
		}
		
		return prev[target];
		
	}
	
	
	static int rec_MaxCoinChange(int ind, int target,int a[]){
		if(ind==0) {
			if(target%a[0] == 0) {
				return target/a[0] ;
			}
			else return (int)Math.pow(-10,9);
		}
		int take = (int) Math.pow(-10, 9);
		if(target>=a[ind]) {
			take = 1+rec_MaxCoinChange(ind,target-a[ind],a);
		}
		int nonTaken = 0+rec_MaxCoinChange(ind-1,target,a);
		
		return Math.max(nonTaken, take);
		
	}
	
	
	
	static int rec_NoofWaysCoinChange(int ind, int target,int a[]){
		if(ind==0) {
			if(target%a[0] == 0) {
				return 1 ;
			}
			else return 0;
		}
		
		int nonTaken = rec_NoofWaysCoinChange(ind-1,target,a);

		int take = 0;
		if(target>=a[ind]) {
			take = rec_NoofWaysCoinChange(ind,target-a[ind],a);
		}
		
		return nonTaken+take;
		
	}
	
	static long countWaysToMakeChangeUtil(int[] arr, int ind, int T, long[][] dp) {
        // Base case: If the current index is 0
        if (ind == 0) {
            // If T is divisible by the first element of the array, return 1, else return 0
            if (T % arr[0] == 0)
                return 1;
            else
                return 0;
        }

        // If the result for this subproblem has already been calculated, return it
        if (dp[ind][T] != -1)
            return dp[ind][T];

        // Calculate the number of ways without taking the current element
        long notTaken = countWaysToMakeChangeUtil(arr, ind - 1, T, dp);

        // Initialize the number of ways taking the current element as 0
        long taken = 0;

        // If the current element is less than or equal to T, calculate 'taken'
        if (arr[ind] <= T)
            taken = countWaysToMakeChangeUtil(arr, ind, T - arr[ind], dp);

        // Store the result in the dp array and return it
        return dp[ind][T] = notTaken + taken;
    }

    // Function to count the ways to make change
    static long countWaysToMakeChange(int[] arr, int n, int T) {
        // Create a 2D array to store results of subproblems
        long dp[][] = new long[n][T + 1];

        // Initialize the dp array with -1 to indicate that subproblems are not solved yet
        for (long row[] : dp)
            Arrays.fill(row, -1);

        // Call the countWaysToMakeChangeUtil function to calculate the number of ways
        return countWaysToMakeChangeUtil(arr, n - 1, T, dp);
    }

}
