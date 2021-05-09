package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BJ_14696 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int R = Integer.parseInt(in.readLine());
		StringBuilder sb = new StringBuilder();
		
		for(int i=0;i<R;i++) {
			//A
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			int a_len = Integer.parseInt(st.nextToken());
			Integer a[] = new Integer[a_len];
			for(int j=0;j<a_len;j++)
				a[j] = Integer.parseInt(st.nextToken());
			Arrays.sort(a, (o1, o2)->(o2-o1));
			//B
			st = new StringTokenizer(in.readLine(), " ");
			int b_len = Integer.parseInt(st.nextToken());
			Integer b[] = new Integer[b_len];
			for(int j=0;j<b_len;j++)
				b[j] = Integer.parseInt(st.nextToken());
			Arrays.sort(b, (o1, o2)->(o2-o1));
			
			//비교
			int len = Math.max(a_len, b_len);
			char win = '0';
			for(int j=0;j<len;j++) {
				if(j==a_len) {
					win = 'B';
					break;
				}
				if(j==b_len) {
					win = 'A';
					break;
				}
				if(a[j]>b[j]) {
					win = 'A';
					break;
				} else if(a[j]<b[j]) {
					win = 'B';
					break;
				}
			}
			sb.append(win=='0'?'D':win).append("\n");
			
		}
		System.out.println(sb.toString());
	}

}
