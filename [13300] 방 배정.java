package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_13300 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int students[][] = new int[6][2];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int S = Integer.parseInt(st.nextToken());
			int Y = Integer.parseInt(st.nextToken());
			students[Y-1][S]++;
		}
		
		int room = 0;
		for(int i=0;i<6;i++) {
			for(int j=0;j<2;j++) {
				int k = students[i][j]/K;
				room += students[i][j]%K==0?k:k+1;
			}
		}
		
		System.out.println(room);
	}

}
