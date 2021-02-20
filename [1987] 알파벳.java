import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BJ_1987 {

	static int R, C;
	static char map[][];
	static int answer = 0;
	static int dx[] = { -1, 0, 1, 0 };
	static int dy[] = { 0, 1, 0, -1 };
	static int visited[][];

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new char[R][C];
		for (int i = 0; i < R; i++) {
			map[i] = in.readLine().toCharArray();
		}
//		int alpha[] = new int[26];
//		alpha[map[0][0]-'A'] = 1;
//		int alpha = 1<<(map[0][0]-'A');
//		DFS2(alpha, 0, 0, 1);
		visited = new int[R][C];
		DFS3(1<<(map[0][0]-'A'), 0, 0, 1);
		System.out.println(answer);
	}
	
	//방법3 : 경로 같은 경우 가지치기 (132ms) (단, visted가 static이어야 함ㅔ)
	private static void DFS3(int alpha, int r, int c, int cnt) {
		if(visited[r][c] == alpha)
			return;
		
		visited[r][c] = alpha;
		for (int i = 0; i < 4; i++) {
			int nr = r + dx[i];
			int nc = c + dy[i];
			if (nr >= 0 && nr < R && nc >= 0 && nc < C) {
				if((alpha & 1<<(map[nr][nc]-'A')) != 0) {
					answer = Math.max(answer, cnt);
					continue;
				}
				DFS3(alpha | 1<<(map[nr][nc]-'A'), nr, nc, cnt+1);
			}
		}
	}

	//방법2 : alpha 계수 배열을 flag로 사용 (776ms)
	private static void DFS2(int alpha, int r, int c, int cnt) {
		for (int i = 0; i < 4; i++) {
			int nr = r + dx[i];
			int nc = c + dy[i];
			if (nr >= 0 && nr < R && nc >= 0 && nc < C) {
				if((alpha & 1<<(map[nr][nc]-'A')) != 0) {
					answer = Math.max(answer, cnt);
					continue;
				}
				DFS2(alpha | 1<<(map[nr][nc]-'A'), nr, nc, cnt+1);
			}
		}
	}

	//방법1 : alpha 계수 배열 (1348ms)
	private static void DFS1(int alpha[], int r, int c, int cnt) {		
		for (int i = 0; i < 4; i++) {
			int nr = r + dx[i];
			int nc = c + dy[i];
			if (nr >= 0 && nr < R && nc >= 0 && nc < C) {
				if(alpha[map[nr][nc]-'A']==1) {
					answer = Math.max(answer, cnt);
					continue;
				}
				alpha[map[nr][nc]-'A']=1;
				DFS1(alpha, nr, nc, cnt+1);
				alpha[map[nr][nc]-'A']=0;
			}
		}
	}

}
