package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class PuyoPuyo {

	static char map[][];
	static int dx[] = { 0, 0, 1, -1 };
	static int dy[] = { -1, 1, 0, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		map = new char[12][6];
		for (int i = 0; i < 12; i++) {
			String str = in.readLine();
			for (int j = 0; j < 6; j++) {
				map[i][j] = str.charAt(j);
			}
		}

		int answer = 0;
		while (isPossible()) {
			down();
			answer++;
		}

		System.out.println(answer);
	}

	private static void down() {
		char resultMap[][] = new char[12][6];
		for(int i=0;i<12;i++)
			Arrays.fill(resultMap[i], '.');

		for (int j = 0; j < 6; j++) {
			int idx = 11;
			for (int i = 11; i >= 0; i--) {
				if(map[i][j] == '.')
					continue;
				else
					resultMap[idx--][j] = map[i][j];
			}
		}
		
		map = resultMap;
	}

	private static void bomb(ArrayList<int[]> bombs) {
		for (int[] bomb : bombs) {
			map[bomb[0]][bomb[1]] = '.';
		}
	}

	private static boolean isPossible() {
		boolean result = false;
		boolean visited[][] = new boolean[12][6];
		ArrayList<int[]> bombs = new ArrayList<>();
		Queue<int[]> queue = new LinkedList<int[]>();

		for (int i = 11; i >= 0; i--) {
			for (int j = 0; j < 6; j++) {
				if (visited[i][j] || map[i][j] == '.')
					continue;

				bombs.clear();

				// BFS
				queue.add(new int[] { i, j });
				bombs.add(new int[] { i, j });
				visited[i][j] = true;
				char color = map[i][j];

				while (!queue.isEmpty()) {
					int r = queue.peek()[0];
					int c = queue.poll()[1];

					for (int d = 0; d < 4; d++) {
						int nr = r + dx[d];
						int nc = c + dy[d];

						if (nr >= 0 && nr < 12 && nc >= 0 && nc < 6 && !visited[nr][nc] && map[nr][nc] == color) {
							visited[nr][nc] = true;
							queue.add(new int[] { nr, nc });
							bombs.add(new int[] { nr, nc });
						}
					}
				}

				if (bombs.size() >= 4) {
					bomb(bombs);
					result = true;
				}
			}
		}

		return result;
	}
}
