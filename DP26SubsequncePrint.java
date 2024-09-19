package com.tuf;

import java.util.Arrays;

public class DP26SubsequncePrint {

	public static void main(String[] args) {
		String str1 = "abcde";
		String str2 = "bdgek";

		int n = str1.length();
		int m = str2.length();

		String finalStr = "";

		System.out.println(tabulation(str1, str2));
	}

	private static String tabulation(String str1, String str2) {
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

		for (int ind1 = 0; ind1 <= n; ind1++) {
			for (int ind2 = 0; ind2 <= m; ind2++) {
				System.out.print(dp[ind1][ind2] + "");
			}
			System.out.println();
		}

		int len = dp[n][m];
		System.out.println("len:" + len);
		int index = len - 1;
		String s = "";

		for (int k = 1; k <= len; k++) {
			s += "$"; // dummy string
		}

		System.out.println("s:" + s);

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
				i--;
			} else {
				j--;
			}

		}

		return s1;
	}

}
