package com.tuf;

import java.util.Arrays;

public class DP9UniquePaths2 {

	public static void main(String[] args) {

		int[][] maze = { { 0, 0, 0 }, { 0, -1, 0 }, { 0, 0, 0 } };

		int n = maze.length;
		int m = maze[0].length;

		System.out.println(n+" "+m);
		System.out.println(mazeObstacles(n, m, maze, "rec"));

		System.out.println(mazeObstacles(n, m, maze, "mem"));

		System.out.println(mazeObstacles(n, m, maze, "tab"));

		System.out.println(mazeObstacles(n, m, maze, "space"));
	}

	private static int mazeObstacles(int m, int n, int[][] maze, String way) {

		int dp[][] = new int[m][n];

		for (int[] row : dp)
			Arrays.fill(row, -1);

		if (way.equals("rec")) {
			return mazeObstaclesUtil_Recursion(m - 1, n - 1, maze);
		} else if (way.equals("mem")) {
			return mazeObstaclesUtil_memoization(m - 1, n - 1, maze, dp);
		} else if (way.equals("tab")) {
			return mazeObstaclesUtil_Tabulation(m, n, maze, dp);
		} else if (way.equals("space")) {
			return mazeObstaclesUtil_SpaceOptimization(m, n, maze);
		}
		return -1;
	}

	private static int mazeObstaclesUtil_Recursion(int i, int j, int[][] maze) {

		if (i > 0 && j > 0 && maze[i][j] == -1)
			return 0;

		if (i == 0 && j == 0)
			return 1;

		if (i < 0 || j < 0)
			return 0;

		int up = mazeObstaclesUtil_Recursion(i - 1, j, maze);
		int left = mazeObstaclesUtil_Recursion(i, j - 1, maze);
		return up + left;
	}

	private static int mazeObstaclesUtil_memoization(int i, int j, int[][] maze, int dp[][]) {

		if (i > 0 && j > 0 && maze[i][j] == -1)
			return 0;

		if (i == 0 && j == 0)
			return 1;

		if (i < 0 || j < 0)
			return 0;

		if (dp[i][j] != -1)
			return dp[i][j];

		int up = mazeObstaclesUtil_Recursion(i - 1, j, maze);
		int left = mazeObstaclesUtil_Recursion(i, j - 1, maze);
		return dp[i][j] = up + left;
	}

	private static int mazeObstaclesUtil_Tabulation(int m, int n, int[][] maze, int dp[][]) {

		for (int i = 0; i < m; i++) {

			for (int j = 0; j < n; j++) {

				if (i == 0 && j == 0) {
					dp[0][0] = 1;
				} else if (i > 0 && j > 0 && maze[i][j] == -1) {
					dp[i][j] = 0;
				} else {
					int up = 0;
					int left = 0;
					if (i > 0) {
						up = dp[i - 1][j];
					}
					if (j > 0) {
						left = dp[i][j - 1];
					}

					dp[i][j] = up + left;
				}
			}
		}
		return dp[m - 1][n - 1];
	}

	private static int mazeObstaclesUtil_SpaceOptimization(int m, int n, int[][] maze) {

		int prev[] = new int[n];

		for (int i = 0; i < m; i++) {

			int cur[] = new int[m];

			for (int j = 0; j < n; j++) {

				if (i == 0 && j == 0) {
					cur[j] = 1;
				
				} else if (i > 0 && j > 0 && maze[i][j] == -1) {
					cur[j] = 0;
				
				} else {
					int up = 0;
					int left = 0;
					if (i > 0) {
						up = prev[j];
					}
					if (j > 0) {
						left = cur[j - 1];
					}

					cur[j] = up + left;
				}
			}
			prev = cur;
		}
		return prev[n - 1];
	}
}
