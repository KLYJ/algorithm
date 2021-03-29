package Level3;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class N으로표현 {

	public static int solution(int N, int number) {
		int answer = -1;
		if (N == number)
			return 1;

		HashSet<Long> dp[] = new HashSet[9];
		for (int i = 0; i < 9; i++)
			dp[i] = new HashSet<>();
		dp[1].add((long) N);

		for (int i = 2; i < 9; i++) {
			// 이전 dp에서 현재 dp 값 누적
			for (int j = 1; j <= i / 2; j++) {
				int k = i-j;
				
				// 현재 dp 인덱스 = 이전 dp 인덱스 + 이전 dp 인덱스
				for(long n: dp[j]) {
					for(long m:dp[k]) {
						// 사칙연산
						dp[i].add(n + m);
						dp[i].add(Math.abs(n - m));
						dp[i].add(n * m);
						if(n>0 && m>0)
							dp[i].add(n>m?n / m:m/n);
					}
					
				}
				
			}

			// NNN...N 추가
			StringBuilder sb = new StringBuilder();
			sb = new StringBuilder();
			for (int j = 0; j < i; j++) {
				sb.append(N);
			}
			dp[i].add(Long.parseLong(sb.toString()));

			// dp[i]에 answer가 있는지 확인
			boolean check = false;
			for(long n:dp[i]) {
				if(n==number) {
					answer=i;
					check = true;
					break;
				}
			}
			
			if(check)
				break;
			
		}

		return answer;
	}

}
