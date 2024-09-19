package com.tuf;

import java.util.Arrays;

public class DP7NinjaTraining {
	public static void main(String[] args) {
		int[][] points = { { 10, 40, 70 }, { 20, 50, 80 }, { 30, 60, 90 } };

		int n = points.length;
		ninjaTraining(n, points);
	}

	private static void ninjaTraining(int n, int[][] points) {

		System.out.println(f_recursion(n - 1, 3, points));
		
		System.out.println("-----f_memoization--------");
		System.out.println("Time Complexity: O(N*4*3)");
		System.out.println("Space Complexity: O(N) + O(N*4)");
		int dp[][] = new int[n][4];
		
		for (int[] row: dp)
            Arrays.fill(row, -1);
		System.out.println(f_memoization(n - 1, 3, points,dp));


		System.out.println("-----f_Tabulation--------");
		System.out.println("Time Complexity: O(N*4*3)");
		System.out.println("Space Complexity: O(N*4)");
		System.out.println(f_Tabulation(n ,points));
		
		
		System.out.println("-----f_SpaceOptimization--------");
		System.out.println("Time Complexity: O(N*4*3)");
		System.out.println("Space Complexity: O(4)");
		System.out.println(f_SpaceOptimization(n ,points));

		
	}

	private static int f_recursion(int days, int last, int[][] points) {

		if (days == 0) {
			int maxi = 0;

			for (int i = 0; i <= 2; i++) {
				if (i != last) {
					maxi = Math.max(maxi, points[0][i]);
				}
			}
			return maxi;
		}

		int maxi = 0;
		for (int i = 0; i <= 2; i++) {
			if (i != last) {
				int activity = points[days][i] + f_recursion(days - 1, i, points);
				maxi = Math.max(activity, maxi);
			}

		}
		return maxi;
	}
	
	private static int f_memoization(int days, int last, int[][] points,int dp[][]) {
		
		if (days == 0) {
			int maxi = 0;

			for (int i = 0; i <= 2; i++) {
				if (i != last) {
					maxi = Math.max(maxi, points[0][i]);
				}
			}
			return maxi;
		}
		
		if(dp[days][last] != -1) {
			return dp[days][last];
		}
		int maxi = 0;
		
		for(int i=0;i<=2;i++) {
			
			if( i!=last) {
				int activity = points[days][i]+f_memoization(days-1,i,points,dp);
				maxi = Math.max(maxi, activity);
			}
		}
		return dp[days][last] = maxi;
	}
	private static int f_Tabulation(int n,  int[][] points) {
		
		int[][] dp = new int[n][4];
		
		dp[0][0] = Math.max(points[0][1], points[0][2]);
        dp[0][1] = Math.max(points[0][0], points[0][2]);
        dp[0][2] = Math.max(points[0][0], points[0][1]);
        dp[0][3] = Math.max(points[0][0], Math.max(points[0][1], points[0][2]));

        for (int day = 1; day < n; day++) {
        	for (int last = 0; last < 4; last++) {
        		dp[day][last] = 0;
        		 for (int task = 0; task <= 2; task++) {
        			 if (task != last) {
        				 int activity = points[day][task] + dp[day - 1][task];
                         dp[day][last] = Math.max(dp[day][last], activity);
        			 }
        		 }
        	}
        }
        return dp[n - 1][3];
        	
	}
	private static int f_SpaceOptimization(int n,  int[][] points) {
		int prev[] = new int[4];
		prev[0] = Math.max(points[0][1], points[0][2]);
        prev[1] = Math.max(points[0][0], points[0][2]);
        prev[2] = Math.max(points[0][0], points[0][1]);
        prev[3] = Math.max(points[0][0], Math.max(points[0][1], points[0][2]));

        
        for (int day = 1; day < n; day++) {
        	int temp[] = new int[4];
        	for (int last = 0; last < 4; last++) {
        		temp[last] = 0;
        		for (int task = 0; task <= 2; task++) {
        			if (task != last) {
                        temp[last] = Math.max(temp[last], points[day][task] + prev[task]);
                    }
        		}
        	}
        	prev = temp;
        }
        return prev[3];
	}
}
