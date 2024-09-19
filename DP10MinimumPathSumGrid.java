package com.tuf;

import java.util.Arrays;

public class DP10MinimumPathSumGrid {
	public static void main(String[] args) {
		
		int matrix[][] = {{5,9,6},{11,5,2}};
        
		 int n = matrix.length;
		 int m = matrix[0].length;
		 minSumPath(n,m,matrix);
	}

	private static void minSumPath(int n, int m, int[][] matrix) {
		
		int dp[][] = new int[n][m];
		
		for (int[] row : dp) {
			Arrays.fill(row, -1);
		}
		
		System.out.println(minSumPathUtil(n-1,m-1,matrix));
		System.out.println(minSumPathUtil_memoization(n-1,m-1,matrix,dp));
		System.out.println(minSumPathUtil_Tabulation(n-1, m-1, matrix));
		System.out.println(minSumPathUtil_SpaceOptimization(m, n, matrix));
		
		
		  
	}

	private static int minSumPathUtil_SpaceOptimization(int n, int m, int[][] matrix) {
		int prev[] = new int[n];

		for (int i = 0; i < n; i++) {

			int cur[] = new int[m];

			for (int j = 0; j < m; j++) {

				if (i == 0 && j == 0) {
					cur[j] = matrix[i][j];
				
				}  else {
					int up =matrix[i][j];
					int left = matrix[i][j];
					
					if(i>0) up += prev[j];
	                else up += (int)Math.pow(10,9);
					
					
					if(j>0) left+=cur[j-1];
	                else left += (int)Math.pow(10,9);

					cur[j] = up + left;
				}
			}
			prev = cur;
		}
		return prev[m - 1];
	}

	private static int minSumPathUtil_Tabulation(int m, int n, int[][] matrix) {
		
		int dp[][] = new int[n][m];
		
		 for(int i=0; i<n ; i++){
		        for(int j=0; j<m; j++){
		            if(i==0 && j==0) dp[i][j] = matrix[i][j];
		            else{
		                
		                int up = matrix[i][j];
		                if(i>0) up += dp[i-1][j];
		                else up += (int)Math.pow(10,9);
		                
		                int left = matrix[i][j];
		                if(j>0) left+=dp[i][j-1];
		                else left += (int)Math.pow(10,9);
		                
		                dp[i][j] = Math.min(up,left);
		            }
		        }
		    }
		    
		    return dp[n-1][m-1];
	}

	private static int minSumPathUtil(int i, int j, int[][] matrix) {
		
		if(i==0 && j==0) {
			return matrix[0][0];
		}
		if (i < 0 || j < 0) {
			return (int)Math.pow(10,9);
		}
		
		int up = matrix[i][j]   + minSumPathUtil(i-1,j,matrix);
		int left = matrix[i][j] + minSumPathUtil(i,j-1,matrix);
		
		return Math.min(up, left);
	}
	
private static int minSumPathUtil_memoization(int i, int j, int[][] matrix, int[][] dp) {

		if(i==0 && j==0) {
			return matrix[0][0];
		}
		if (i < 0 || j < 0) {
			return (int)Math.pow(10,9);
		}
		
		if(dp[i][j] != -1) {
			return dp[i][j];
		}
		int up = matrix[i][j]   + minSumPathUtil_memoization(i-1,j,matrix,dp);
		int left = matrix[i][j] + minSumPathUtil_memoization(i,j-1,matrix,dp);
		
		return dp[i][j]= Math.min(up, left);
	}

}
