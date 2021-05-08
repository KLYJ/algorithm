package com.ssafy.algo;

public class Sol_0323_아파트 {

	public static void main(String[] args) {
		System.out.println(solution(8));
	}

	private static int solution(int n) {
		int dp[] = new int[n+1];
		dp[0] = 0;
		dp[1] = 2;
		dp[2] = 3;
		
		for(int i=3;i<=n;i++) {
			dp[i] = dp[i-2]+dp[i-1];
		}
		
		return dp[n];		
	}
	
	
	
}
