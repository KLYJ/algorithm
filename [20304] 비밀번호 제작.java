package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_20304_4 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		int hk_pw_cnt = Integer.parseInt(in.readLine());
		
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		
		Queue<Integer> queue = new LinkedList<Integer>(); //BFS에서 이용할 큐
		int security[] = new int[1000001]; //0:dummy
		Arrays.fill(security, -1);
		for(int i=1;i<=hk_pw_cnt;i++) {
			int hk_pw = Integer.parseInt(st.nextToken());
			security[hk_pw] = 0;
			queue.offer(hk_pw);
		}
		
		int answer = Integer.MIN_VALUE;
		while(!queue.isEmpty()) {
			int num = queue.poll();
			int cnt = security[num];
			
			for(int i=0;i<20;i++) {
				int next = num^(1<<i);
				if(next>N || security[next] != -1) continue;
				security[next] = cnt+1;
				queue.offer(next);
				answer = Math.max(answer, cnt+1);
			}
		}
		
		System.out.println(answer);
		
	}
}
