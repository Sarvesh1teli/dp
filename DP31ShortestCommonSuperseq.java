package com.tuf;

import java.util.Arrays;

public class DP31ShortestCommonSuperseq {

	public static void main(String[] args) {

		String s1 = "brute";
		String s2 = "groot";

		System.out.println("The Longest Common Supersequence is " + shortestSupersequence(s1, s2));

	}

	private static String shortestSupersequence(String str1, String str2) {
		int n = str1.length();
		int m = str2.length();

		int dp[][] = new int[n + 1][m + 1];

		for (int row[] : dp) {
			Arrays.fill(row, -1);
		}

		for (int ind2 = 0; ind2 <= m; ind2++) {
			dp[0][ind2] = 0;
		}

		for (int ind1 = 0; ind1 <= n; ind1++) {
			dp[ind1][0] = 0;
		}

		for (int ind1 = 1; ind1 <= n; ind1++) {
			for (int ind2 = 1; ind2 <= m; ind2++) {

				if (str1.charAt(ind1 - 1) == str2.charAt(ind2 - 1)) {
					dp[ind1][ind2] = 1 + dp[ind1 - 1][ind2 - 1];
				} else {

					dp[ind1][ind2] = 0 + Math.max(dp[ind1 - 1][ind2], dp[ind1][ind2 - 1]);
				}
			}
		}
		int k = dp[n][m];
		System.out.println("LCS lenghth :"+dp[n][m]);
		System.out.println("min operation:"+(n+m-k));
		//return dp[n][m]; it return lenghth

		for (int ind1 = 0; ind1 <= n; ind1++) {
			for (int ind2 = 0; ind2 <= m; ind2++) {
				System.out.print(dp[ind1][ind2] + "");
			}
			System.out.println();
		}

		System.out.println();
		int len = dp[n][m];
		System.out.println("length of matrix dp :" + len);
		
		int i = n;
		int j = m;
		String s1 = "";
		System.out.println(n);
		System.out.println(m);

		while (i > 0 && j > 0) {
			if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
				s1 = str1.charAt(i - 1) + s1;
				i--;
				j--;
			} else if (dp[i - 1][j] > dp[i][j - 1]) {
				s1 = str1.charAt(i - 1) + s1;
				//System.out.println("2--" + str1.charAt(i - 1));
				i--;
			} else {
				s1 = str2.charAt(j - 1) + s1;
				//System.out.println("1--" + str2.charAt(j - 1));
				j--;
			}

		}

		while (i > 0) {
			s1 = str1.charAt(i - 1) + s1;
			i--;
		}
		
		while (j > 0 ) {
			s1 = str2.charAt(j - 1) + s1;
			j--;
		}
		return s1;
	}

}
