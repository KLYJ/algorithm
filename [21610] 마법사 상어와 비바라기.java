import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static int N, M, map[][], move[][];
	static List<int[]> cloud = new ArrayList<int[]>();
	static boolean isCloud[][];
	static int dx[] = { 0, 0, -1, -1, -1, 0, 1, 1, 1 };
	static int dy[] = { 0, -1, -1, 0, 1, 1, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		move = new int[M][2];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine(), " ");

			move[i][0] = Integer.parseInt(st.nextToken());
			move[i][1] = Integer.parseInt(st.nextToken());
		}

		// 초기 구름 설정
		cloud.add(new int[] { N - 2, 0 });
		cloud.add(new int[] { N - 2, 1 });
		cloud.add(new int[] { N - 1, 0 });
		cloud.add(new int[] { N - 1, 1 });

		// 구름이 있는 곳인지 체크
		isCloud = new boolean[N][N];

		for (int i = 0; i < M; i++) {
			// 1. 초기화
			for (int j = 0; j < N; j++)
				Arrays.fill(isCloud[j], false);

			// 2. 구름 이동
			moveCloud(move[i][0], move[i][1]);
			
			// 3. 이동한 칸 4방 확인
			calcMap();

			// 4. 구름이었던 칸 visited[][] = true
			for (int[] c : cloud)
				isCloud[c[0]][c[1]] = true;

			// 5. 구름 생성
			makeCloud();
		}
		
		int answer = 0;
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++)
				answer += map[i][j];
		}
		System.out.println(answer);
	}

	private static void calcMap() {
		for (int[] cl : cloud) {
			int r = cl[0];
			int c = cl[1];
			
			// 4방 대각선 확인 후 ++
			for (int d = 2; d <= 8; d += 2) {
				int nr = r + dx[d];
				int nc = c + dy[d];

				if (nr >= 0 && nr < N && nc >= 0 && nc < N && map[nr][nc] > 0)
					map[r][c]++;
			}
		}
	}

	private static void makeCloud() {
		// 기존 구름 리셋
		cloud.clear();
		
		// 구름 찾아서 물의 양 -2
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(map[i][j] >= 2 && !isCloud[i][j]) {
					map[i][j] -= 2;
					cloud.add(new int[] {i, j});
				}
			}
		}
	}

	private static void moveCloud(int d, int s) {
		for (int[] c : cloud) {
			c[0] = (c[0] + dx[d] * s) % N;
			c[1] = (c[1] + dy[d] * s) % N;

			// 음수 처리
			while (c[0] < 0)
				c[0] += N;

			while (c[1] < 0)
				c[1] += N;

			// 이동한 구름 칸++
			map[c[0]][c[1]]++;
		}
	}

	private static void printMap() {
		for (int i = 0; i < N; i++) {
			System.out.println(Arrays.toString(map[i]));
		}
	}

}
