package com.ssafy.ct;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_5604 {

	private static long[] cnt;
	private static long position;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		int T = Integer.parseInt(in.readLine());

		for (int t = 1; t <= T; t++) {
			sb.append("#").append(t).append(" ");

			st = new StringTokenizer(in.readLine(), " ");
			long a = Long.parseLong(st.nextToken());
			long b = Long.parseLong(st.nextToken());
			cnt = new long[10];
			position = 1;
			
			if(a == 0)
				a = 1;

			while (a <= b) {
				while (a % 10 != 0 && a <= b) {
					cal(a);
					a++;
				}

				if (a > b)
					break;

				while (b % 10 != 9 && a <= b) {
					cal(b);
					b--;
				}

				a /= 10;
				b /= 10;
				
				for (int i = 0; i < 10; i++)
					cnt[i] += (b - a + 1) * position;

				position *= 10;
			}

			long answer = 0;
			for (int i = 1; i < 10; i++)
				answer += cnt[i]*i;

			sb.append(answer).append("\n");
		}
		
		System.out.println(sb.toString());
	}

	public static void cal(long num) {
		while (num > 0) {
			cnt[(int) (num % 10)] += position;
			num /= 10;
		}
	}
}
