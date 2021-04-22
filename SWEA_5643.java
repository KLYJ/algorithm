package com.ssafy.algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_5643 {

	static int N, M, adj[][];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		int TC = Integer.parseInt(in.readLine());

		for (int t = 1; t <= TC; t++) {
			sb.append("#").append(t).append(" ");

			N = Integer.parseInt(in.readLine());
			M = Integer.parseInt(in.readLine());
			adj = new int[N + 1][N + 1];

			for (int i = 0; i <= N; i++)
				adj[i][0] = -1;

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(in.readLine(), " ");
				int r = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				adj[r][c] = 1;
			}

			int answer = 0;
			for (int k = 1; k <= N; k++) {
				if (adj[k][0] == -1)
					dfs(k);
			}

			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					adj[0][j] += adj[i][j];
				}
			}

			for (int k = 1; k <= N; k++) {
				if (adj[k][0] + adj[0][k] == N - 1)
					answer++;
			}

			sb.append(answer).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static void dfs(int cur) {
		for (int i = 1; i <= N; i++) {
			if (adj[cur][i] == 1) {
				if (adj[i][0] == -1)
					dfs(i);

				if (adj[i][0] > 0) {
					for (int j = 1; j <= N; j++) {
						if (adj[i][j] == 1)
							adj[cur][j] = 1;
					}
				}
			}
		}

		int cnt = 0;
		for (int i = 1; i <= N; i++)
			cnt += adj[cur][i];

		adj[cur][0] = cnt;
	}

}
