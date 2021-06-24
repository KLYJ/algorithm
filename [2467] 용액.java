package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_2467 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		int num[] = new int[N];
		for(int i=0;i<N;i++)
			num[i] = Integer.parseInt(st.nextToken());
		
		// 이분 탐색
		int ans_left = 0;
		int ans_right = 0;
		int min_hap = Integer.MAX_VALUE;
		
		int left = 0;
		int right = N-1;
		while(left < right) {
			int hap = num[left]+num[right];
			if(min_hap > Math.abs(hap)) {
				ans_left = num[left];
				ans_right = num[right];
				min_hap = Math.abs(hap);
			}
			
			if(hap >= 0)
				right--;
			else
				left++;
		}
		
		System.out.println(ans_left+" "+ans_right);
		
	}

}
