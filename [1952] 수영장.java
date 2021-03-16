package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_1952 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(in.readLine());
		
		for(int t=1;t<=T;t++) {
			sb.append("#").append(t).append(" ");
			
			int fee[] = new int[4];
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			for(int i=0;i<4;i++) {
				fee[i] = Integer.parseInt(st.nextToken());
			}
			
			int months[] = new int[12];
			st = new StringTokenizer(in.readLine(), " ");
			for(int i=0;i<12;i++) {
				int days = Integer.parseInt(st.nextToken());
				//1일권 vs 1달권
				months[i] = Math.min(fee[0]*days, fee[1]);
			}				
			
			int money[] = new int[13];
			money[0] = 0;
			money[1] = months[0];
			money[2] = months[0]+months[1];
			for(int i=3;i<=12;i++) {
				money[i] = Math.min(fee[2]+money[i-3], money[i-1]+months[i-1]);
			}
			
			sb.append(Math.min(money[12], fee[3])).append("\n");
		}
		System.out.println(sb.toString());
	}
}
