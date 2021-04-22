import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class SWEA_1868 {

	static int N, map[][];
	static boolean visited[][];
	static int dx[] = { 0, 0, 1, 1, 1, -1, -1, -1 };
	static int dy[] = { 1, -1, 0, 1, -1, 0, 1, -1 };
	static Queue<int[]> queue;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(in.readLine());

		for (int t = 1; t <= T; t++) {
			sb.append("#").append(t).append(" ");

			N = Integer.parseInt(in.readLine());
			map = new int[N][N];
			visited = new boolean[N][N];
			queue = new LinkedList<int[]>();

			for (int i = 0; i < N; i++) {
				String str = in.readLine();
				for (int j = 0; j < N; j++) {
					if (str.charAt(j) == '*') {
						map[i][j] = 9;
						visited[i][j] = true;
					}
				}
			}

			int answer = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!visited[i][j]) { // 지뢰가 아니라면
						// 8방에 지뢰 몇 개 있는지 저장
						int cnt = 0;
						for (int d = 0; d < 8; d++) {
							int nr = i + dx[d];
							int nc = j + dy[d];
							if (nr >= 0 && nr < N && nc >= 0 && nc < N && visited[nr][nc])
								cnt++;
						}
						map[i][j] = cnt;
					}
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == 0 && !visited[i][j]) {
						queue.add(new int[] { i, j });
						bfs();
						answer++;
					}
				}
			}
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(!visited[i][j]) {
						answer++;
					}
				}
			}

			sb.append(answer).append("\n");
		}

		System.out.println(sb.toString());
	}

	private static void bfs() {
		while (!queue.isEmpty()) {
			int r = queue.peek()[0];
			int c = queue.poll()[1];
			visited[r][c] = true;

			for (int d = 0; d < 8; d++) {
				int nr = r + dx[d];
				int nc = c + dy[d];
				if (nr >= 0 && nr < N && nc >= 0 && nc < N && !visited[nr][nc]) {
					visited[nr][nc] = true;

					if (map[nr][nc] == 0)
						queue.add(new int[] { nr, nc });
				}
			}
		}
	}

}
