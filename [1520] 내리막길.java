package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_1520_2 {

	static int R, C, map[][], dp[][];
	static int dx[] = { 0, 0, 1, -1 }; // 행 방향 전환
	static int dy[] = { 1, -1, 0, 0 }; // 열 방향 전환

	public static void main(String[] args) throws NumberFormatException, IOException {
		// 입력
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		// 가로, 세로 입력 받기
		st = new StringTokenizer(in.readLine(), " ");
		R = Integer.parseInt(st.nextToken()); // 가로
		C = Integer.parseInt(st.nextToken()); // 세로

		// 지형 입력 받기
		map = new int[R][C]; // 지형의 높이 저장
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dp = new int[R][C];
		for(int i=0;i<R;i++)
			Arrays.fill(dp[i], -1);
		
		System.out.println(dfs(0, 0));
	}

	// dfs + dp
	private static int dfs(int r, int c) {
		if(r == R-1 && c == C-1)
			return 1;
		if(dp[r][c] != -1)
			return dp[r][c];
		
		dp[r][c] = 0;
		
		for(int d=0;d<4;d++) {
			int nr = r+dx[d];
			int nc = c+dy[d];
			
			if(nr>=0 && nr<R && nc>=0 && nc<C && map[r][c] > map[nr][nc])
				dp[r][c] += dfs(nr, nc);
		}
		
		return dp[r][c];
	}

}
