import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Sol_0326_BJ14502 {

	static int sero, garo, map[][], copyMap[][], answer = 0;
	static ArrayList<int[]> virus = new ArrayList<>();
	static ArrayList<int[]> empty = new ArrayList<>();
	static int dx[] = { 0, 0, 1, -1 };
	static int dy[] = { 1, -1, 0, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		sero = Integer.parseInt(st.nextToken());
		garo = Integer.parseInt(st.nextToken());
		map = new int[sero][garo];
		for (int i = 0; i < sero; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < garo; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2)
					virus.add(new int[] { i, j });
				else if (map[i][j] == 0)
					empty.add(new int[] { i, j });
			}
		}

		int selected[] = new int[3];
		copyMap = new int[sero][garo];
		dfs(0, selected, 0);
		System.out.println(answer);
	}

	// 벽 조합
	private static void dfs(int selectTo, int selected[], int start) {
		if (selectTo == 3) {
			// 선택한 곳에 벽 세우기
			for (int i = 0; i < 3; i++) {
				int r = empty.get(selected[i])[0];
				int c = empty.get(selected[i])[1];
				map[r][c] = 1;
			}

			// 바이러스 퍼뜨리기
			for (int i = 0; i < sero; i++)
				System.arraycopy(map[i], 0, copyMap[i], 0, garo);
			for (int i = 0; i < virus.size(); i++) {
				spreadVirus(virus.get(i)[0], virus.get(i)[1]);
			}

			// 안전 영역 구하기
			checkSafeArea();

			// 선택한 곳에 벽 지우기
			for (int i = 0; i < 3; i++) {
				int r = empty.get(selected[i])[0];
				int c = empty.get(selected[i])[1];
				map[r][c] = 0;
			}
			return;
		}

		// 0 중에서 3개 선택하기
		for (int i = start; i < empty.size(); i++) {
			selected[selectTo] = i;
			dfs(selectTo + 1, selected, i + 1);
		}

	}

	// 바이러스 퍼뜨리기
	private static void spreadVirus(int r, int c) {
		for (int d = 0; d < 4; d++) {
			int nr = r + dx[d];
			int nc = c + dy[d];
			if (nr >= 0 && nc >= 0 && nr < sero && nc < garo && copyMap[nr][nc] == 0) {
				copyMap[nr][nc] = 2;
				spreadVirus(nr, nc);
			}
		}
	}

	// 안전 영역 확인
	private static void checkSafeArea() {
		int cnt = 0;
		for (int i = 0; i < sero; i++) {
			for (int j = 0; j < garo; j++) {
				if (copyMap[i][j] == 0)
					cnt++;
			}
		}
		answer = Math.max(answer, cnt);
	}

}
