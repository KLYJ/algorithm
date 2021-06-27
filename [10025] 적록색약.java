package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BJ_10026 {

	static int dx[] = { 0, 0, 1, -1 };
	static int dy[] = { 1, -1, 0, 0 };
	static int N, no, yes;
	static char map[][];
	static boolean visited[][];

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());

		map = new char[N][N];
		for (int i = 0; i < N; i++) {
			String st = in.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = st.charAt(j);
			}
		}

		no = 0; // 색약 X
		yes = 0; // 색약 O

		visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (visited[i][j])
					continue;
				
				yes++; no++;
				bfs(i, j);
			}
		}

		System.out.println(no + " " + yes);

	}

	private static void bfs(int i, int j) {
		if(visited[i][j]) {
			no--;
			return;
		}
		
		Queue<int[]> queue = new LinkedList<int[]>();
		Queue<int[]> queue2 = new LinkedList<int[]>();
		queue.add(new int[] { i, j });
		visited[i][j] = true;

		while (!queue.isEmpty()) {
			int r = queue.peek()[0];
			int c = queue.poll()[1];

			for (int d = 0; d < 4; d++) {
				int nr = r + dx[d];
				int nc = c + dy[d];

				if (nr >= 0 && nr < N && nc >= 0 && nc < N && !visited[nr][nc]) {
					if(map[nr][nc] == map[i][j]) {
						visited[nr][nc] = true;
						queue.add(new int[] { nr, nc });
					}
					else if(Math.abs(map[nr][nc]-map[i][j])=='R'-'G') {
						queue2.add(new int[] {nr, nc});
					}	
				}
			}

		}
		
		while(!queue2.isEmpty()) {
			no++;
			bfs(queue2.peek()[0], queue2.poll()[1]);
		}

	}

}
