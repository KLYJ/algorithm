package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_2846 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		int answer = 0;
		int step = 0;
		int before = Integer.parseInt(st.nextToken());
		for(int i=1;i<N;i++) {
			int now = Integer.parseInt(st.nextToken());
			if(now > before) {
				step += now-before;
			}
			else {
				answer = Math.max(answer, step);
				step = 0;
			}
			before = now;
		}
		
		System.out.println(Math.max(answer, step));
	}
}
