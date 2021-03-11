package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_1949 {

	static int N, answer;
	static int map[][];

	//방향 : 우 하 좌 상
	static int dx[] = { 0, 1, 0, -1 };
	static int dy[] = { 1, 0, -1, 0 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(in.readLine());

		for (int t = 1; t <= T; t++) {
			sb.append("#").append(t).append(" ");

			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());

			map = new int[N][N];
			int max = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(in.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					max = Math.max(max, map[i][j]);
				}
			}

			ArrayList<int[]> bong = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == max) {
						bong.add(new int[] { i, j });
					}
				}
			}
			
			answer = 0;
			for (int b[] : bong) {
				boolean visited[][] = new boolean[N][N];
				dfs(b[0], b[1], 1, max, K, visited);
			}
			sb.append(answer).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static void dfs(int r, int c, int cnt, int height, int k, boolean visited[][]) {
		boolean check = false; // 4방 중에 갈 수 있나?

		// 4방 탐색
		for (int i = 0; i < 4; i++) {
			int nr = r + dx[i];
			int nc = c + dy[i];
			if (nr > -1 && nr < N && nc > -1 && nc < N && !visited[nr][nc]) {
				if(height > map[nr][nc]) {
					check = true;
					visited[r][c] = true;
					dfs(nr, nc, cnt+1, map[nr][nc], k, visited);
				}
				else {
					if(k!=-1 && map[nr][nc]-height < k) { // 깎고 가보자
						check = true;
						visited[r][c] = true;
						dfs(nr, nc, cnt+1, height-1, -1, visited);
					}
				}
			}
		}
		
		visited[r][c] = false;

		if (!check) { // 갈 수 없다!
			answer = Math.max(cnt, answer);
			return;
		}
	}
}
