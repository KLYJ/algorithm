import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Sol_0324_BJ2636 {

	static int h, w;
	static int map[][];
	static boolean visited[][];
	static Queue<int[]> queue;
	static int dx[] = { 1, 0 , -1, 0};
	static int dy[] = { 0, 1 , 0, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		h = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());

		map = new int[h][w];
		for (int i = 0; i < h; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < w; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int time = 0;
		int pieces = 0;
		visited = new boolean[h][w];
		queue = new LinkedList<>();
		while (!isEmpty()) {
			//초기화
			queue.clear();
			for(int i=0;i<h;i++)
				Arrays.fill(visited[i], false);
			
			//바깥 공기 탐색 + 바깥 접촉 치즈 queue에 넣기
			checkOutside(0, 0);
			
			//바깥 접촉 치즈 0으로 바꾸기
			pieces = queue.size();
			for(int idx[]:queue) {
				map[idx[0]][idx[1]] = 0;
			}
			
			//시간 1초 흐름
			time++;
		}
		
		System.out.println(time);
		System.out.println(pieces);
	}

	private static void checkOutside(int r, int c) {
		visited[r][c] = true;

		for (int i = 0; i < 4; i++) {
			int nr = r + dx[i];
			int nc = c + dy[i];
			if (nr >-1 && nc >-1 && nr < h && nc < w && !visited[nr][nc]) {
				if (map[nr][nc] == 0) {
					checkOutside(nr, nc);
				} else {
					visited[nr][nc] = true;
					queue.offer(new int[] { nr, nc });
				}
			}
		}
	}

	private static boolean isEmpty() {
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				if (map[i][j] != 0) {
					return false;
				}
			}
		}
		return true;
	}

}
