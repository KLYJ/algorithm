import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class JO_1681 {

	static int adMatrix[][];
	static int N, answer = Integer.MAX_VALUE;
	static boolean visited[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());

		adMatrix = new int[N][N];
		visited = new boolean[N];

		StringTokenizer st = null;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < N; j++) {
				adMatrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		visited[0] = true;
		dfs(0, 1, 0);

		System.out.println(answer);
	}

	private static void dfs(int idx, int cnt, int sum) {
		// 기저조건
		if (cnt == N) {
			// 회사로 바로 돌아가는 길이 없다면 리턴
			if (adMatrix[idx][0] == 0) {
				return;
			}
			answer = Math.min(answer, sum + adMatrix[idx][0]);
			return;
		}

		// 가지치기
		if (sum > answer)

		{
			return;
		}

		for (int i = 0; i < N; i++) {
			if (!visited[i] && adMatrix[idx][i] > 0) {
				visited[i] = true;
				dfs(i, cnt + 1, sum + adMatrix[idx][i]);
				visited[i] = false;
			}
		}
	}

}
