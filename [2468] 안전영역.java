package BJ;

import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.IOException;

public class BJ_2468 {
	
	static int N, map[][], answer = 1;
	static int dx[] = {0,0,1,-1};
	static int dy[] = {1,-1,0,0};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(in.readLine());
		
		map = new int[N][N];
		int max_height = 0;
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for(int j=0;j<N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] > max_height)
					max_height = map[i][j];
			}
		}
		
		for(int i=1;i<=max_height;i++) {
			// 잠기는 영역 0으로 만들기
			setZero(i);
			
			//영역 개수 구하기
			getAreaCount();
		}
		
		System.out.println(answer);
	}

	private static void getAreaCount() {
		boolean visited[][] = new boolean[N][N];
		int ans = 0;
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(!visited[i][j] && map[i][j] != 0) {
					// BFS
					Queue<int []> queue = new LinkedList<int[]>();
					queue.add(new int[] {i, j});
					visited[i][j] = true;
					
					while(!queue.isEmpty()) {
						int r = queue.peek()[0];
						int c = queue.poll()[1];
						
						for(int d=0;d<4;d++) {
							int nr = r+dx[d];
							int nc = c+dy[d];
							
							if(nr>=0 && nr<N && nc>=0 && nc<N && !visited[nr][nc] && map[nr][nc] != 0)
							{
								visited[nr][nc] = true;
								queue.add(new int[] {nr, nc});
							}
						}
					}
					
					ans++;
				}
			}
		}
		
		answer = Math.max(answer, ans);
	}

	private static void setZero(int height) {
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(map[i][j] == height)
					map[i][j] = 0;
			}
		}
	}

}
