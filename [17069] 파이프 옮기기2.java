package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_17069 {

	static int N, answer = 0;
	static long map[][];
	// 방향 : 가로, 세로, 대각
	static int dx[] = { 0, 1, 1 };
	static int dy[] = { 1, 0, 1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(in.readLine());

		map = new long[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

//		dfs는 시간초과
//		dfs(0, 1, 0);
//		System.out.println(answer);

		// dp로 풀자 - 가로, 세로, 대각
		long dp[][][] = new long[N + 1][N + 1][3];
		dp[1][2][0] = 1;

		for (int i = 1; i <= N; i++) {
			for (int j = 3; j <= N; j++) {
				if ((i == 1 && j == 1) || (i == 1 && j == 2) || map[i][j] == 1)
					continue;
								
				dp[i][j][0] = dp[i][j - 1][0]+dp[i][j - 1][2];
				dp[i][j][1] = dp[i - 1][j][1]+dp[i - 1][j][2];
				// 대각에 놓을 수 있는지 확인
				if (map[i - 1][j] == 0 && map[i][j - 1] == 0 && map[i - 1][j - 1] == 0)
					dp[i][j][2] = dp[i-1][j-1][0]+dp[i-1][j-1][1]+dp[i-1][j-1][2];
			}
		}

//		for (int i = 1; i <= N; i++) {
//			for (int j = 1; j <= N; j++) {
//				if (i == 1 && j == 1)
//					continue;
//
//				for (int dir = 0; dir < 3; dir++) {
//					if(dp[i][j][dir] == 0)
//						continue;
//					
//					int check = 0; // 대각 가능 여부 확인
//					for (int d = 0; d < 3; d++) {
//						int nr = i + dx[d];
//						int nc = j + dy[d];
//
//						if (nr >= 1 && nr <= N && nc >= 1 && nc <= N && map[nr][nc] == 0) {
//							check++;
//
//							// [현 가로 다음 세로 & 현 세로 다음 가로] 무시
//							if ((dir == 0 && d == 1) || (dir == 1 && d == 0))
//								continue;
//							
//							// 다음 대각 가능 여부 확인
//							if (d == 2 && check != 3)
//								continue;
//
//							dp[nr][nc][d] += dp[i][j][dir];
//						}
//					}
//				}
//			}
//		}

		System.out.println(dp[N][N][0] + dp[N][N][1] + dp[N][N][2]);
	}

	private static void dfs(int r, int c, int dir) {
		if (r == N - 1 && c == N - 1) {
			answer++;
			return;
		}

		int check = 0; // 대각 가능 여부 확인
		for (int d = 0; d < 3; d++) {
			int nr = r + dx[d];
			int nc = c + dy[d];

			if (nr >= 0 && nr < N && nc >= 0 && nc < N && map[nr][nc] == 0) {
				check++;

				// [현 가로 다음 세로 & 현 세로 다음 가로] 무시
				if ((dir == 0 && d == 1) || (dir == 1 && d == 0))
					continue;

				// 다음 대각 가능 여부 확인
				if (d == 2 && check != 3)
					continue;

				dfs(nr, nc, d);
			}

		}

	}

}
