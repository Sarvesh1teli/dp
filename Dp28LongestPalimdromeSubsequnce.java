package com.tuf;

import java.util.Arrays;

//Longest Palindromic Subsequence | 
public class Dp28LongestPalimdromeSubsequnce {

	public static void main(String[] args) {

		String s1 = "bbabcbcab";
		String s2 = new StringBuilder(s1).reverse().toString();

		System.out.println("S1 :" + s1);
		System.out.println("S2 :" + s2);

		System.out.println("tabulationLCS:" + tabulationLCS(s1, s2));
		System.out.print("The Length of Longest Palindromic Subsequence is ");
		System.out.println(longestPalindromeSubsequence(s1));
	}

	static int longestPalindromeSubsequence(String s) {
		String t = s;
		String ss = new StringBuilder(s).reverse().toString();
		return lcsSpaceOptimisation(ss, t);
	}

	private static int tabulationLCS(String str1, String str2) {

		int n = str1.length();
		int m = str2.length();

		int dp[][] = new int[n + 1][m + 1];
		for (int rows[] : dp)
			Arrays.fill(rows, -1);

		// In the recursive logic, we set the base case to if(ind1<0 || ind2<0) but we
		// can’t set the dp array’s index to -1.
		// Therefore a hack for this issue is to shift every index by 1 towards the
		// right.

		// recursive -1,0,1,----n
		// shifted 0,1,.......n+1

		for (int i2 = 0; i2 <= m; i2++) { // ind1<0 means -1<0 ,here -1 means 0
			dp[0][i2] = 0;
		}
		for (int i1 = 0; i1 <= n; i1++) {
			dp[i1][0] = 0;
		}

		for (int id1 = 1; id1 <= n; id1++) {
			for (int id2 = 1; id2 <= m; id2++) {
				if (str1.charAt(id1 - 1) == str2.charAt(id2 - 1)) { // Shifting of indexes
					dp[id1][id2] = 1 + dp[id1 - 1][id2 - 1];
				} else {

					dp[id1][id2] = Math.max(dp[id1 - 1][id2], dp[id1][id2 - 1]);
				}
			}
		}
		return dp[n][m];

	}

	static int lcsSpaceOptimisation(String s1, String s2) {

		int n = s1.length();
		int m = s2.length();

		int[] prev = new int[m + 1];
		int[] cur = new int[m + 1];

		// Base Case is covered as we have initialized the prev and cur to 0.

		for (int ind1 = 1; ind1 <= n; ind1++) {
			for (int ind2 = 1; ind2 <= m; ind2++) {
				if (s1.charAt(ind1 - 1) == s2.charAt(ind2 - 1))
					cur[ind2] = 1 + prev[ind2 - 1];
				else
					cur[ind2] = 0 + Math.max(prev[ind2], cur[ind2 - 1]);
			}

			prev = (int[]) (cur.clone());

		}

		return prev[m];
	}
}
