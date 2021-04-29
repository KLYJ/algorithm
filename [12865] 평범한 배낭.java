package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_12865 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int wv[][] = new int[N][2];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(in.readLine(), " ");
			wv[i][0] = Integer.parseInt(st.nextToken());
			wv[i][1] = Integer.parseInt(st.nextToken());
		}
		
		int dp[][] = new int[N+1][K+1];
		for(int i=1;i<=N;i++) {
			int w = wv[i-1][0];
			int v = wv[i-1][1];
			
			for(int j=1;j<=K;j++) {
				if(w > j) {
					dp[i][j] = dp[i-1][j];
				}
				else {
					dp[i][j] = Math.max(dp[i-1][j], v+dp[i-1][j-w]);
				}
			}
		}
		
		System.out.println(dp[N][K]);
		
		
	}
	

}
