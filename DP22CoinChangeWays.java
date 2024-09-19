package com.tuf;

public class DP22CoinChangeWays {
	public static void main(String[] args) {
		int a[]= {1,1,2};
		int t=4;
		int n = a.length;
		System.out.println(f(n-1,t,a));
	}

	private static int f(int ind, int T,int a[]) {
		if(ind == 0){
	        if(T%a[0]==0)
	        return 1;
	        else
	        return 0;
	    }
		int nonpick = f(ind-1,T,a);
		int pick = 0;
		if(T>=a[ind] ){
			pick = f(ind,T-a[ind],a);
		}
		return pick+nonpick;
		
	}
	

}
