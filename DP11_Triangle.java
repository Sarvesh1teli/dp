package com.tuf;

import java.util.Arrays;

//Minimum path sum in Triangular Grid (DP 11)

public class DP11_Triangle {
	public static void main(String[] args) {

		int triangle[][] = { 
					{ 1 },
					{ 2, 3 }, 
					{ 9, 6, 7 },
					{ 8, 9, 6, 10 } 
					};

		int n = triangle.length;

		System.out.println(n);
		System.out.println(minimumPathSum(triangle, n,"rec"));
		
		
		System.out.println(minimumPathSum(triangle, n,"mem"));
		System.out.println("Time Complexity: O(N*N)");
		System.out.println("Space Complexity: O(N) + O(N*N)");
	}

	private static int minimumPathSum(int[][] triangle, int n,String way) {
		
		int dp[][]=new int[n][n];
	    for(int row[]: dp)
	    {
	    	 Arrays.fill(row,-1);
	    }
	   
	    if (way.equals("rec")) {
	    	 return minimumPathSumUtil_Recursion(0,0,triangle,n);
		} else if (way.equals("mem")) {
			 System.out.println("------MEMO-IZATION---------");
			return minimumPathSumUtil_memoization(0,0,triangle,n,dp);
		} else if (way.equals("tab")) {
			return minimumPathSumUtil_Tabulation(triangle,n);
		} else if (way.equals("space")) {
			return minimumPathSumUtil_Recursion(0,0,triangle,n);
		}
		return -1;
	    
	   
	}

	private static int minimumPathSumUtil_Tabulation( int[][] triangle, int n) {
		
		int[][]dp = new int[n][n];
		
		for(int j=0;j<n;j++) {
			dp[n-1][j] = triangle[n-1][j];
		}
		
		for(int i=n-2; i>=0; i--){
	        for(int j=i; j>=0; j--){
	        	
	        	int down = triangle[i][j]+dp[i+1][j];
	            int diagonal = triangle[i][j]+dp[i+1][j+1];
	            
	            dp[i][j] = Math.min(down, diagonal);
	        }
	        
		}
		return dp[0][0];
	}

	private static int minimumPathSumUtil_memoization(int i, int j, int[][] triangle, int n,int[][]dp) {
		
		if(i==n-1) {
			return triangle[i][j];
		}
		if(dp[i][j] != -1) {
			return dp[i][j];
		}
		int down = triangle[i][j]+minimumPathSumUtil_Recursion(i+1,j,triangle,n);
		int daignol = triangle[i][j]+minimumPathSumUtil_Recursion(i+1,j+1,triangle,n);
		
		return dp[i][j] =  Math.min(down, daignol);
	}

	private static int minimumPathSumUtil_Recursion(int i, int j, int[][] triangle, int n) {
		
		if(i==n-1) {
			return triangle[i][j];
		}
		
		int down = triangle[i][j]+minimumPathSumUtil_Recursion(i+1,j,triangle,n);
		int daignol = triangle[i][j]+minimumPathSumUtil_Recursion(i+1,j+1,triangle,n);
		
		return Math.min(down, daignol);
	}

}
