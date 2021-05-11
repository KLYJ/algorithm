package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_1916 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		int M = Integer.parseInt(in.readLine());

		StringTokenizer st = null;
		
		int matrix[][] = new int[N+1][N+1];
		for(int i=1;i<N+1;i++)
			Arrays.fill(matrix[i], -1);
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int fee = Integer.parseInt(st.nextToken());
			
			if(matrix[from][to] == -1)
				matrix[from][to] = fee;
			else
				matrix[from][to] = Math.min(matrix[from][to], fee);
		}
		
		st = new StringTokenizer(in.readLine(), " ");
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		int minFee[] = new int[N+1];
		boolean visited[] = new boolean[N+1];
		
		Arrays.fill(minFee, Integer.MAX_VALUE);
		minFee[start] = 0;
		
		for(int i=1;i<N+1;i++) {
			int min = Integer.MAX_VALUE;
			int cur = 0;
			
			for(int j=1;j<N+1;j++) {
				if(!visited[j] && min > minFee[j]) {
					min = minFee[j];
					cur = j;
				}
			}
			
			visited[cur] = true;
			if(cur==end)
				break;
			
			for(int j=1;j<N+1;j++) {
				if(!visited[j] && matrix[cur][j] != -1 && minFee[j] > min+matrix[cur][j])
					minFee[j] = min+matrix[cur][j];
			}
			
		}
		
		System.out.println(minFee[end]);
	}

}
