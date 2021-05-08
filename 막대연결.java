package com.ssafy.algo;

public class Sol_0323_막대연결 {

	public static void main(String[] args) {
		System.out.println(solution(6));
	}

	private static int solution(int n) {
		int dp[] = new int[n + 1];
		dp[0] = 0;
		dp[1] = 2;
		dp[2] = 5;

		for (int i = 3; i <= n; i++) {
			dp[i] = dp[i - 1] * 2 + dp[i - 2];
		}

		return dp[n];
	}

}
