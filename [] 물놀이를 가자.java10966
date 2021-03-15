package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_10966 {
	
	static int N, M, answer = 0;
	static char map[][];
	static int dx[] = {0,0,1,-1};
	static int dy[] = {1,-1,0,0};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(in.readLine());
		
		for(int t=1;t<=T;t++) {
			sb.append("#").append(t).append(" ");
			
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

      map = new char[N][M];
			for(int i=0;i<N;i++) {
				map[i] = in.readLine().toCharArray();
			}
			
			Queue<int[]> queue = new LinkedList<int[]>();
			int visited[][] = new int[N][M];
			for(int i=0;i<N;i++) {
				Arrays.fill(visited[i], -1);
				for(int j=0;j<M;j++) {
					if(map[i][j]=='W') {
						visited[i][j] = 0;
						queue.offer(new int[] {i,j});
					}
				}
			}
			
			bfs(queue, visited);

			answer = 0;
			for(int i=0;i<N;i++) {
				for(int j=0;j<M;j++) {
					answer += visited[i][j];
				}
			}
			
			sb.append(answer).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static void bfs(Queue<int[]>queue, int[][] visited) {	
		while(!queue.isEmpty()) {
			int p[] = queue.poll();
			int r = p[0];
			int c = p[1];
			
			for(int i=0;i<4;i++) {
				int nr = r+dx[i];
				int nc = c+dy[i];
				if(nr>-1 && nr<N & nc>-1 && nc<M && visited[nr][nc] == -1) {
					visited[nr][nc] = visited[r][c]+1;
					queue.offer(new int[] {nr, nc});
				}
			}
		}
	}

}
