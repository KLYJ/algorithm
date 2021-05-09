package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ_17204 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		
		int next[] = new int[N];
		for(int i=0;i<N;i++)
			next[i] = Integer.parseInt(in.readLine());
		
		ArrayList<Integer> history = new ArrayList<>();
		int answer = 0;
		int next_idx = 0;
		while(true) {
			answer++;
			next_idx = next[next_idx];
			
			if(next_idx == B)
				break;
			
			if(history.contains(next_idx)) {
				answer = -1;
				break;
			}
			
			history.add(next_idx);
		}
		
		System.out.println(answer);
	}
	

}
