import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Sol_0218_BJ3109 {

	static int R, C, total_cnt = 0;
	static char map[][];
	static boolean visited[][];
	static int dx[] = {-1, 0, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(new File("input.txt"))));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new char[R][C];
		visited = new boolean[R][C];
		for (int i = 0; i < R; i++) {
			String info = in.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = info.charAt(j);
			}
		}

		for (int i = 0; i < R; i++) {
			total_cnt += DFS(i, 0, 0);
		}
		
		System.out.println(total_cnt);

	}

	private static int DFS(int row, int col, int cnt) {
		if (!visited[row][col]) {
			if (cnt == C-1) {
				visited[row][col] = true;
				return 1;
			}
			for(int i=0;i<3;i++) {
				int nx = row+dx[i];
				int ny = col+1;
				if(nx>=0 && nx<R && ny>=0 && ny<C) {
					if(map[nx][ny]=='.') {
						visited[row][col] = true;
						int flag = DFS(nx,ny, cnt+1);
						if(flag == 1)
							return 1;
					}
				}
			}
		}
		else {
			return 0;
		}
		return 0;
	}

}
