package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SWEA_1227 {
	
	static int answer = 0;
	static char map[][];
	static int dx[] = {0,0,1,-1};
	static int dy[] = {1,-1,0,0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		for(int t=1;t<=10;t++) {
			sb.append("#").append(t).append(" ");
			in.readLine();
			
			map = new char[100][100];
			int start[] = new int[2];
			for(int i=0;i<100;i++) {
				String st = in.readLine();
				for(int j=0;j<100;j++) {
					map[i][j] = st.charAt(j);
					if(map[i][j] == '2') {
						start[0] = i;
						start[1] = j;
					}
				}
			}
			
			answer = 0;
			dfs(start[0], start[1], new boolean[100][100]);
			
			sb.append(answer).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static void dfs(int r, int c, boolean[][] visited) {
		if(map[r][c]=='3') {
			answer = 1;
			return;
		}
		
		boolean check = false;		
		for(int i=0;i<4;i++) {
			int nr = r+dx[i];
			int nc = c+dy[i];
			if(map[nr][nc]!='1' && !visited[nr][nc]) {
				visited[r][c] = true;
				dfs(nr, nc, visited);
				check = true;
			}
		}
		
		if(!check)
			return;
	}

}
