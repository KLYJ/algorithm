package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class SWEA_1486 {
	
	static int N, B, answer;
	static Integer staffH[];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(in.readLine());
		
		for(int t=1;t<=T;t++) {
			sb.append("#").append(t).append(" ");
			
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			
			staffH = new Integer[N];
			st = new StringTokenizer(in.readLine(), " ");
			for(int i=0;i<N;i++)
				staffH[i] = Integer.parseInt(st.nextToken());
			
			Arrays.sort(staffH, new Comparator<Integer>() {
				public int compare(Integer o1, Integer o2) {
					return o2-o1;
				}
			});
			
			answer = Integer.MAX_VALUE;
			subset(0, new boolean[N], 0);
			
			sb.append(answer).append("\n");
		}
		System.out.println(sb.toString());
	}		

	private static void subset(int selectTo, boolean[] selected, int height) {
		if(height>=B) {
			answer = Math.min(height-B, answer);
			return;
		}
		
		if(selectTo==N) {
			return;
		}
		
		
		selected[selectTo] = true;
		subset(selectTo+1, selected, height+staffH[selectTo]);
		
		selected[selectTo] = false;
		subset(selectTo+1, selected, height);
	}

}
