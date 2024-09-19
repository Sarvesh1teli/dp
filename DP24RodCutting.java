package com.tuf;

public class DP24RodCutting {
	public static void main(String[] args) {
		
		int price[] = {2,5,7,8,10};
		int rodlength = price.length;
		int n = price.length-1;

		System.out.println(recursion_RodCutting(n,rodlength,price));
	}

	private static int recursion_RodCutting(int ind, int rodlength, int[] price) {
		
		if(ind==0) {
			return (rodlength*price[0]);  
			//at index 0, means we can cut 5m rod rod into 1m*5 ie rodlenghth/1*price[0] = (5/1) *2=10
		}
		
		int nonpick = 0+recursion_RodCutting(ind-1,rodlength,price);
		int pick = Integer.MIN_VALUE;
		int pieceOfRodlenth = ind+1 ;   //[0,1,2,3,4] so ind+1
		if(rodlength>=pieceOfRodlenth) {
			pick = price[ind]+recursion_RodCutting(ind,rodlength-pieceOfRodlenth,price);
		}
		
		return Math.max(pick, nonpick);
	}
}
