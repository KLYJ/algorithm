package ect;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 슈퍼마리오 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		int arr[] = new int[N+2];
		int score[] = new int[N+2];
		
		for(int i=1;i<=N;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			
			if(i>=2)
				score[i] = Math.max(score[i], score[i-2]+arr[i]);
			if(i>=7)
				score[i] = Math.max(score[i], score[i-7]+arr[i]);
		}
		
//		System.out.println(Arrays.toString(score));
		
		for(int i=N-6;i<=N;i++) {
			score[N+1] = Math.max(score[N+1], score[i]);
		}
		
		System.out.println(score[N+1]);
		
	}

}
