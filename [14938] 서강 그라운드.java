package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_14938 {
	
	static int n, m, r, items[], matrix[][], answer = 0;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(in.readLine()," ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(in.readLine(), " ");
		items = new int[n+1];
		for(int i=0;i<n;i++) {
			items[i+1] = Integer.parseInt(st.nextToken());
		}
		
		matrix = new int[n+1][n+1];
		for(int i=0;i<r;i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int l = Integer.parseInt(st.nextToken());
			
			matrix[a][b] = l;
			matrix[b][a] = l;
		}
		
		for(int i=1;i<=n;i++) {
			dijkstra(i);
		}
		
		System.out.println(answer);
	}

	private static void dijkstra(int start) {
		int minDis[] = new int[n+1];
		boolean visited[] = new boolean[n+1];
		
		Arrays.fill(minDis, Integer.MAX_VALUE);
		minDis[start] = 0;
		
		int getItem = 0;
		
		for(int i=1;i<=n;i++) {
			int min = Integer.MAX_VALUE;
			int cur = 0;
			
			for(int j=1;j<=n;j++) {
				if(!visited[j] && min > minDis[j]) {
					min = minDis[j];
					cur = j;
				}
			}
			
			visited[cur] = true;
			if(minDis[cur]<=m)
				getItem += items[cur];
			
			for(int j=1;j<=n;j++) {
				int dis = min+matrix[cur][j];
				if(!visited[j] && matrix[cur][j] != 0 && minDis[j] > dis) {
					minDis[j] = dis;
				}
			}
		}
		
//		System.out.println(start+"에서 수색 시작하면");
//		System.out.println(getItem);
		
		answer = Math.max(answer, getItem);
		
	}

}
