package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_11497 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		int T = Integer.parseInt(in.readLine());
		
		for(int t=0;t<T;t++) {
			int N = Integer.parseInt(in.readLine());
			
			int arr[] = new int[N];
			st = new StringTokenizer(in.readLine(), " ");
			for(int i=0;i<N;i++)
				arr[i] = Integer.parseInt(st.nextToken());
			
			Arrays.sort(arr);
			
			int max = arr[N-1];
			int left = arr[N-2];
			int right = arr[N-3];
			int answer = Math.max(max-left, max-right);
			
			int idx = N-4;
			boolean dir = true;
			while(idx >= 0) {
				if(dir) {
					answer = Math.max(answer, left-arr[idx]);
					left = arr[idx];
				}
				else {
					answer = Math.max(answer, right-arr[idx]);
					right = arr[idx];
				}
				
				dir = !dir;
				idx--;
			}
			
			sb.append(answer).append("\n");
		}
		
		System.out.println(sb.toString());
	}

}
