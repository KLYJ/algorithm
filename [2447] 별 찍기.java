import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static int M = 0;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		char map[][] = new char[N][N];

		DFS(map, N, 0, 0);

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sb.append(map[i][j] == '*' ? "*" : " ");
			}
			sb.append("\n");
		}

		System.out.println(sb.toString());

	}

	private static void DFS(char map[][], int cnt, int r, int c) {
		if (cnt == 1) {
			map[r][c] = '*';
			return;
		}

		for (int j = 0; j < 3; j++) {
			for (int k = 0; k < 3; k++) {
				if (j == 1 && k == 1)
					continue;
				DFS(map, cnt / 3, r + cnt / 3 * j, c + cnt / 3 * k);
			}
		}
	}

}
