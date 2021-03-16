package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1219 {
	
	static int answer = 0;
	static int road[][];
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		for(int t=1;t<=10;t++) {
			sb.append("#").append(t).append(" ");
			
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			st.nextToken();
			int n = Integer.parseInt(st.nextToken());
			
			road = new int[100][2];
			st = new StringTokenizer(in.readLine(), " ");
			for(int i=0;i<n;i++) {
				int r = Integer.parseInt(st.nextToken());
				if(road[r][0]==0)
					road[r][0] = Integer.parseInt(st.nextToken());
				else
					road[r][1] = Integer.parseInt(st.nextToken());
			}
			
			answer = 0;
			dfs(0, new boolean[100]);
			sb.append(answer).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static void dfs(int r, boolean[] visited) {
		for(int i=0;i<2;i++) {
			if(road[r][i] != 0 && !visited[r]) {
				if(road[r][i]==99) {
					answer = 1;
					return;
				}
				dfs(road[r][i], visited);
			}
		}
		visited[r] = true;
	}

}
