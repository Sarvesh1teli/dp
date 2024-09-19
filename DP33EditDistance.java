package com.tuf;

import java.util.Arrays;

public class DP33EditDistance {
	public static void main(String[] args) {
		String s1 = "horse";
		String s2 = "ros";

		System.out.println("The minimum number of operations required is: " + editDistance(s1, s2));
	
		System.out.println("Tabulation: The minimum number of operations required is: " + editDistance_2(s1, s2));

	}

	static int editDistance(String S1, String S2) {

		int n = S1.length();
		int m = S2.length();

		int[][] dp = new int[n][m];
		for (int row[] : dp)
			Arrays.fill(row, -1);
		return editDistanceUtil(S1, S2, n - 1, m - 1, dp);

	}
	
	
	static int editDistance_2(String S1, String S2) {

		return editDistance_Tab(S1, S2);

	}


	static int editDistanceUtil(String S1, String S2, int i, int j, int[][] dp) {

		if (i < 0)
			return j + 1;
		if (j < 0)
			return i + 1;

		if (dp[i][j] != -1)
			return dp[i][j];

		if (S1.charAt(i) == S2.charAt(j))
			return dp[i][j] = 0 + editDistanceUtil(S1, S2, i - 1, j - 1, dp);

		// Minimum of three choices
		else
			return dp[i][j] = 1 + Math.min(editDistanceUtil(S1, S2, i - 1, j - 1, dp),
					Math.min(editDistanceUtil(S1, S2, i - 1, j, dp), editDistanceUtil(S1, S2, i, j - 1, dp)));

	}
	
	static int editDistance_Tab(String S1, String S2){
	    
	    int n = S1.length();
	    int m = S2.length();
	    
	    int[][] dp=new int[n+1][m+1];
	    
	    System.out.println("n:"+n);
	    System.out.println("m:"+m);
	    for(int i=0;i<=n;i++){
	        dp[i][0] = i;
	    }
	    for(int j=0;j<=m;j++){
	        dp[0][j] = j;
	    }
	    
	    for(int i=1;i<n+1;i++){
	        for(int j=1;j<m+1;j++){
	            if(S1.charAt(i-1)==S2.charAt(j-1))
	                dp[i][j] = 0+dp[i-1][j-1];
	            
	            else dp[i][j] = 1+Math.min(dp[i-1][j-1],Math.min(dp[i-1][j],dp[i][j-1]));
	        }
	    }
	   
	    System.out.println("---");
	    for(int i=0;i<n+1;i++){
	        for(int j=0;j<m+1;j++){
	          System.out.print(dp[i][j]+" ");
	        }
	        System.out.println();
	    }
	    
	    return dp[n][m];
	}
}
