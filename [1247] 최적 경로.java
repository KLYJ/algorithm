import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Sol_0218_SWEA1247 {

	static int N, answer;
	static int customer[][];
	static int company[];
	static int home[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());
		StringBuilder sb = new StringBuilder();

		for (int t = 1; t <= T; t++) {
			sb.append("#").append(t).append(" ");
			N = Integer.parseInt(in.readLine());

			company = new int[2];
			home = new int[2];
			customer = new int[N][2];
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			company[0] = Integer.parseInt(st.nextToken());
			company[1] = Integer.parseInt(st.nextToken());
			home[0] = Integer.parseInt(st.nextToken());
			home[1] = Integer.parseInt(st.nextToken());
			for (int i = 0; i < N; i++) {
				customer[i][0] = Integer.parseInt(st.nextToken());
				customer[i][1] = Integer.parseInt(st.nextToken());
			}

			answer = Integer.MAX_VALUE;
			dfs(0, -1, 0, 0);

			sb.append(answer).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static void dfs(int selectTo, int before_idx, int flag, int dis) {
		if (selectTo == N) {
			// 마지막 고객 - 집 거리 누적
			dis += Math.abs(customer[before_idx][0] - home[0]) + Math.abs(customer[before_idx][1] - home[1]);
			answer = Math.min(dis, answer);
			return;
		}
		for (int i = 0; i < N; i++) {
			if ((flag & 1 << i) == 0) {
				if (before_idx == -1) {
					// 처음 고객 - 회사 거리 누적
					dis += Math.abs(company[0] - customer[i][0]) + Math.abs(company[1] - customer[i][1]);
				} else {
					// 첫 고객 아니면 앞 고객과의 거리 누적
					dis += Math.abs(customer[before_idx][0] - customer[i][0])
							+ Math.abs(customer[before_idx][1] - customer[i][1]);
				}
				// 현재 누적 거리가 answer보다 작다면 DFS 수행
				if (dis < answer) {
					dfs(selectTo + 1, i, flag | 1 << i, dis);
					// dis DFS 수행 전으로 돌려주기
					if (before_idx == -1) {
						dis -= Math.abs(company[0] - customer[i][0]) + Math.abs(company[1] - customer[i][1]);
					} else {
						dis -= Math.abs(customer[before_idx][0] - customer[i][0])
								+ Math.abs(customer[before_idx][1] - customer[i][1]);
					}
				}
				// 중간에 거리가 answer(현재 최단거리)보다 커지면 DFS 중지
				else {
					break;
				}
			}
		}
	}

}
