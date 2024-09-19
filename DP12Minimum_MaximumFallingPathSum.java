package com.tuf;

import java.util.Arrays;

public class DP12Minimum_MaximumFallingPathSum {

	public static void main(String[] args) {
		int matrix[][] = { { 1, 2, 10, 4 }, { 100, 3, 2, 1 }, { 1, 1, 20, 2 }, { 1, 2, 2, 1 } };

		System.out.println("-----Memo-ization-----");
		System.out.println(getMaxPathSum(matrix));
		System.out.println("Time Complexity: O(N*N)");
		System.out.println("Space Complexity: O(N) + O(N*M)");
		
		System.out.println("\n---------tabulation------");
		System.out.println(getMaxPathSum_Tabulation(matrix));
		System.out.println("Time Complexity: O(N*M))");
		System.out.println("Space Complexity: O(N*M)");
		
		System.out.println("\n---------space optimization------");
		System.out.println(getMaxPathSum_SpaceOptimize(matrix));
		
	}

	static int getMaxPathSum(int[][] matrix) {

		int n = matrix.length;
		int m = matrix[0].length;

		int dp[][] = new int[n][m];
		for (int row[] : dp)
			Arrays.fill(row, -1);

		int maxi = Integer.MIN_VALUE;

		for (int j = 0; j < m; j++) {
			int ans = getMaxUtil(n - 1, j, m, matrix, dp);
			maxi = Math.max(maxi, ans);
		}

		return maxi;
	}
	
	
	static int getMaxUtil(int i, int j, int m, int[][] matrix,int[][] dp){
	    
	    // Base Conditions
	    if(j<0 || j>=m)
	        return (int)Math.pow(-10,9);
	   
	    if(i==0)
	        return matrix[0][j];
	    
	    if(dp[i][j]!=-1) return dp[i][j];
	    
	    int up = matrix[i][j] + getMaxUtil(i-1,j,m,matrix,dp);
	    int leftDiagonal = matrix[i][j] + getMaxUtil(i-1,j-1,m,matrix,dp);
	    int rightDiagonal = matrix[i][j] + getMaxUtil(i-1,j+1,m,matrix,dp);
	    
	    return dp[i][j]= Math.max(up,Math.max(leftDiagonal,rightDiagonal));
	    
	}
	
	
	static int getMaxPathSum_Tabulation(int[][] matrix) {

		int n = matrix.length;
		int m = matrix[0].length;

		int dp[][] = new int[n][m];

		// Initializing first row - base condition
		for (int j = 0; j < m; j++) {
			dp[0][j] = matrix[0][j];
		}

		for (int i = 1; i < n; i++) {
			for (int j = 0; j < m; j++) {

				int up = matrix[i][j] + dp[i - 1][j];

				int leftDiagonal = matrix[i][j];
				if (j - 1 >= 0)
					leftDiagonal += dp[i - 1][j - 1];
				else
					leftDiagonal += (int) Math.pow(-10, 9);

				int rightDiagonal = matrix[i][j];
				if (j + 1 < m)
					rightDiagonal += dp[i - 1][j + 1];
				else
					rightDiagonal += (int) Math.pow(-10, 9);

				dp[i][j] = Math.max(up, Math.max(leftDiagonal, rightDiagonal));

			}
		}
		int maxi = Integer.MIN_VALUE;
	    
	    for(int j=0; j<m;j++){
	        maxi = Math.max(maxi,dp[n-1][j]);
	    }
	    
	    return maxi;
	}
	
	
	
	static int getMaxPathSum_SpaceOptimize(int[][] matrix) {

		int n = matrix.length;
		int m = matrix[0].length;

		int prev[]= new int[m];
		int cur[] = new int[m];

		// Initializing first row - base condition
		for (int j = 0; j < m; j++) {
			prev[j] = matrix[0][j];
		}

		for (int i = 1; i < n; i++) {
			for (int j = 0; j < m; j++) {

				int up = matrix[i][j] + prev[j];

				int leftDiagonal = matrix[i][j];
				if (j - 1 >= 0)
					leftDiagonal += prev[j - 1];
				else
					leftDiagonal += (int) Math.pow(-10, 9);

				int rightDiagonal = matrix[i][j];
				if (j + 1 < m)
					rightDiagonal += prev[j + 1];
				else
					rightDiagonal += (int) Math.pow(-10, 9);

				cur[j] = Math.max(up, Math.max(leftDiagonal, rightDiagonal));

			}
			prev=cur;
		}
		int maxi = Integer.MIN_VALUE;
	    
	    for(int j=0; j<m;j++){
	        maxi = Math.max(maxi,prev[j]);
	    }
	    
	    return maxi;
	}
}
