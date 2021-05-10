package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_1182 {
	
	static int N, S, arr[], answer = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(in.readLine(), " ");
		arr = new int[N];
		for(int i=0;i<N;i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		subset(0, new boolean[N]);
		
		System.out.println(answer);
	}

	private static void subset(int selectTo, boolean[] selected) {
		if(selectTo == N) {
			int sum = 0;
			int cnt = 0;
			for(int i=0;i<N;i++) {
				if(selected[i])
					sum += arr[i];
				else
					cnt++;
			}
			
			if(sum == S && cnt != N)
				answer++;
			
			return;
		}
		
		selected[selectTo] = true;
		subset(selectTo+1, selected);
		
		selected[selectTo] = false;
		subset(selectTo+1, selected);
	}

}
