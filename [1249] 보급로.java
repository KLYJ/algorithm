import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

public class SWEA_1249 {

	static int N, map[][], answer, ans[][];
	static boolean visited[][];
	static int dx[] = { 0, 0, 1, -1 };
	static int dy[] = { 1, -1, 0, 0 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());
		StringBuilder sb = new StringBuilder();

		for (int t = 1; t <= T; t++) {
			sb.append("#").append(t).append(" ");

			N = Integer.parseInt(in.readLine());
			map = new int[N][N];

			for (int i = 0; i < N; i++) {
				String st = in.readLine();
				for (int j = 0; j < N; j++) {
					map[i][j] = st.charAt(j) - 48;
				}
			}

			ans = new int[N][N];
			for (int i = 0; i < N; i++) {
				Arrays.fill(ans[i], Integer.MAX_VALUE);
			}
			ans[0][0] = 0;

			visited = new boolean[N][N];
			answer = Integer.MAX_VALUE;
			dfs();

			sb.append(answer).append("\n");

		}

		System.out.println(sb.toString());
	}

	private static void dfs() {
		Stack<int[]> stack = new Stack<>();
		stack.push(new int[] { 0, 0 });
		visited[0][0] = true;

		while (!stack.isEmpty()) {
			int r = stack.peek()[0];
			int c = stack.pop()[1];

			if (r == N - 1 && c == N - 1) {
				answer = Math.min(answer, ans[r][c]);
				continue;
			}

			if (ans[r][c] >= answer) {
				continue;
			}


			for (int i = 0; i < 4; i++) {
				int nr = r + dx[i];
				int nc = c + dy[i];
				if (nr >= 0 && nr < N && nc >= 0 && nc < N
						&& (!visited[nr][nc] || (ans[nr][nc] > ans[r][c] + map[nr][nc]))) {
					visited[nr][nc] = true;
					ans[nr][nc] = ans[r][c] + map[nr][nc];
					stack.push(new int[] {nr, nc});
				}
			}
		}

	}

}
