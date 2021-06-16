package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_1946 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		int T = Integer.parseInt(in.readLine());
		
		for(int t=1;t<=T;t++) {
			int N = Integer.parseInt(in.readLine());;
			
			int num[] = new int[100001]; // 계수배열
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(in.readLine(), " ");
				num[Integer.parseInt(st.nextToken())] = Integer.parseInt(st.nextToken());
			}	
			
			int answer = N;
			int min = 100000;
			for(int i=1;i<100001;i++) {
				if(min < num[i])
					answer--;
				else
					min = num[i];
			}
			
			sb.append(answer).append("\n");
		}
		
		System.out.println(sb.toString());	
	}

}
