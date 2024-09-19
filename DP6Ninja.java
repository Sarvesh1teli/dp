package com.tuf;

import java.util.Arrays;

public class DP6Ninja {
	public static void main(String[] args) {
		
		System.out.println("Maximum sum of non-adjacent elements (DP 5) ");
		
		int[][] points = {
						  {10,40,70},
                		  {20,50,80},
                		  {30,60,90}
                		  };

		int n = points.length-1;
		System.out.println("-------Using Recursion--------");
		int maxSum = ninjaRecursion(n,3,points);
		
		System.out.println("Maximum point of non-adjacent elements : "+maxSum);
		System.out.println("recursion Time Complexity: Exponentional n and Space Complexity:=O(n)+O(n)");

		
		
	}

	/*
	 * static int ninjaTraining(int n, int[][] points) {
	 * 
	 * int dp[][] = new int[n][4]; for (int[] row: dp) Arrays.fill(row, -1);
	 * 
	 * return f(n - 1, 3, points, dp); }
	 */
	
	private static int ninjaRecursion(int day,int last,int[][] points) {
		
		
		if (day == 0) {
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
				
				int activity = points[day][i] + ninjaRecursion(day - 1, i, points);
				
				maxi = Math.max(maxi,activity);
			}
		}
		
		return maxi;
	}

}
