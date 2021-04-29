package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_16234 {

	static int N, L, R, map[][], visited[][], answer = 0;;
	static int dx[] = { 0, 0, -1, 1 };
	static int dy[] = { -1, 1, 0, 0 };
	static HashMap<Integer, Integer> hm;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		visited = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < N; j++) {
//				System.out.print(map[i][j] + "\t");
//			}
//			System.out.println();
//		}
//		System.out.println("--------");

		while (true) {
			// 초기화
			for (int i = 0; i < N; i++)
				Arrays.fill(visited[i], 0);
			hm = new HashMap<>();

			// 국경선 open할 나라 찾기
			if (!find())
				break;

			// 인구 재분배
			move();

//			for (int i = 0; i < N; i++) {
//				for (int j = 0; j < N; j++) {
//					System.out.print(map[i][j] + "\t");
//				}
//				System.out.println();
//			}
//			System.out.println("--------");

			answer++;
		}

		System.out.println(answer);

	}

	private static void move() {
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(visited[i][j] != 0) {
					map[i][j] = hm.get(visited[i][j]);
				}
			}
		}
			
	}

	private static boolean find() {
		int num = 1;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (visited[i][j] != 0)
					continue;

				if (bfs(i, j, num))
					num++;
			}
		}

		if (num == 1)
			return false;
		else
			return true;
	}

	private static boolean bfs(int i, int j, int num) {
		Queue<int[]> queue = new LinkedList<int[]>();
		queue.add(new int[] { i, j });
		int cnt = 0;
		int sum = 0;

		while (!queue.isEmpty()) {
			int r = queue.peek()[0];
			int c = queue.poll()[1];
			int m1 = map[r][c];

			for (int d = 0; d < 4; d++) {
				int nr = r + dx[d];
				int nc = c + dy[d];

				if (nr >= 0 && nr < N && nc >= 0 && nc < N && visited[nr][nc] == 0) {
					int m2 = map[nr][nc];
					int cha = Math.abs(m1 - m2);

					if (L <= cha && cha <= R) {
						visited[r][c] = num;
						visited[nr][nc] = num;
						queue.add(new int[] { nr, nc });
						cnt++;
						sum += map[nr][nc];
					}
				}
			}
		}

		if (visited[i][j] != 0) {
			sum += map[i][j];
			cnt++;
			hm.put(num, sum / cnt);
			return true;
		} else
			return false;

	}

}
