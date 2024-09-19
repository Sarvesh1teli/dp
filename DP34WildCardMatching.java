package com.tuf;

import java.util.Arrays;

public class DP34WildCardMatching {
	public static void main(String[] args) {

		String S1 = "ab*cd";
		String S2 = "abdefcd";
		int n = S1.length();
		int m = S2.length();

		boolean b = recursion_wildcardMatching(S1, S2, n - 1, m - 1);
		if (b) {
			System.out.println("String S1 and S2 do match");
		} else {
			System.out.println("String S1 and S2 do not match");
		}

		int dp[][] = new int[n][m];
		for (int row[] : dp) {
			Arrays.fill(row, -1);
		}
		int status = memoization_wildcardMatching(S1, S2, n - 1, m - 1, dp);

		if (status == 1) {
			System.out.println("String S1 and S2 do match");
		} else {
			System.out.println("String S1 and S2 do not match");
		}
		// Time Complexity: O(N*M) Space Complexity: O(N*M) + O(N+M)

		boolean tab_b = tabulation_wildcardMatching(S1, S2);
		if (tab_b) {
			System.out.println("String S1 and S2 do match");
		} else {
			System.out.println("String S1 and S2 do not match");
		}
		// Time Complexity: O(N*M) Space Complexity: O(N*M)

		boolean space_b = space_wildcardMatching(S1, S2);
		if (space_b) {
			System.out.println("String S1 and S2 do match");
		} else {
			System.out.println("String S1 and S2 do not match");
		}
	}

	static boolean recursion_wildcardMatching(String s1, String s2, int i, int j) {

		if (i < 0 && j < 0) { // s1 and s2 exhausted
			return true;
		}

		if (i < 0 && j >= 0) { // s1 exhausted and s2 still have an character
			return false;
		}

		if (i >= 0 && j < 0) { // s2 exhausted and s1 still have an character like s1=***abcd s2=abdefcd

			for (int ii = 0; ii <= i; ii++) {

				if (s1.charAt(ii) != '*') {
					return false;
				}
			}
			return true;
		}
		if (s1.charAt(i) == s2.charAt(j) || s1.charAt(i) == '?') {

			return recursion_wildcardMatching(s1, s2, i - 1, j - 1);
		} else if (s1.charAt(i) == '*') {
			return recursion_wildcardMatching(s1, s2, i - 1, j) || recursion_wildcardMatching(s1, s2, i, j - 1);
		} else {
			return false;
		}
	}

	static int memoization_wildcardMatching(String s1, String s2, int i, int j, int dp[][]) {

		if (i < 0 && j < 0) { // s1 and s2 exhausted
			return 1;
		}

		if (i < 0 && j >= 0) { // s1 exhausted and s2 still have an character
			return 0;
		}

		if (i >= 0 && j < 0) { // s2 exhausted and s1 still have an character like s1=***abcd s2=abdefcd

			for (int ii = 0; ii <= i; ii++) {

				if (s1.charAt(ii) != '*') {
					return 0;
				}
			}
			return 1;
		}
		if (dp[i][j] != -1) { // s1 exhausted and s2 still have an character
			return dp[i][j];
		}

		if (s1.charAt(i) == s2.charAt(j) || s1.charAt(i) == '?') {

			dp[i][j] = memoization_wildcardMatching(s1, s2, i - 1, j - 1, dp);
		} else if (s1.charAt(i) == '*') {
			dp[i][j] = (memoization_wildcardMatching(s1, s2, i - 1, j, dp) == 1
					|| memoization_wildcardMatching(s1, s2, i, j - 1, dp) == 1) ? 1 : 0;
		} else {
			dp[i][j] = 0;
		}

		return dp[i][j];
	}

	static boolean isAllStars(String S1, int i) {

		// S1 is taken in 1-based indexing
		for (int j = 1; j <= i; j++) {
			if (S1.charAt(j - 1) != '*')
				return false;
		}
		return true;
	}

	static boolean tabulation_wildcardMatching(String s1, String s2) {

		int n = s1.length();
		int m = s2.length();

		boolean dp[][] = new boolean[n + 1][m + 1];

		dp[0][0] = true; // s1 and s2 exhausted

		for (int j = 0; j <= m; j++) { // s1 exhausted and s2 still have an character
			dp[0][j] = false;
		}

		for (int i = 0; i <= n; i++) { // s2 exhausted and s1 still have an character like s1=***abcd s2=abdefcd
			dp[i][0] = isAllStars(s1, i);
		}

		for (int i = 1; i <= n; i++) {

			for (int j = 1; j <= m; j++) {

				if (s1.charAt(i - 1) == s2.charAt(j - 1) || s1.charAt(i - 1) == '?') {

					dp[i][j] = dp[i - 1][j - 1];

				} else if (s1.charAt(i - 1) == '*') {

					dp[i][j] = dp[i - 1][j] || dp[i][j - 1];

				} else {
					dp[i][j] = false;
				}

			}
		}
		return dp[n][m];
	}

	static boolean space_wildcardMatching(String s1, String s2) {

		int n = s1.length();
		int m = s2.length();

		boolean prev[] = new boolean[m + 1];
		boolean cur[] = new boolean[m + 1];

		prev[0] = true; // s1 and s2 exhausted

		for (int i = 1; i <= n; i++) {
			cur[0] = isAllStars(s1, i);
			for (int j = 1; j <= m; j++) {

				if (s1.charAt(i - 1) == s2.charAt(j - 1) || s1.charAt(i - 1) == '?') {

					cur[j] = prev[j - 1];

				} else {
					if (s1.charAt(i - 1) == '*') {
						cur[j] = prev[j] || cur[j - 1];
					} else {
						cur[j] = false;
					}
				}
			}
			prev = (boolean[]) cur.clone();
		}
		return prev[m];
	}
}
