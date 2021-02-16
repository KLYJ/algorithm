package com.ssafy.algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Sol_0216_BJ2839 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		
		/*
		//방법1 : DP(BFS) - 메모리 초과
		Queue<int[]> queue = new LinkedList<int[]>();
		queue.add(new int[] {3, 1});
		queue.add(new int[] {5, 1});
		
		while(!queue.isEmpty()) {
			int info[] = queue.poll();
			int kg = info[0];
			int cnt = info[1];
      
			if(kg == N) {
				System.out.println(cnt);
				break;
			}
			else if(kg > N+5) {
				System.out.println(-1);
				break;
			}
			else {
				queue.offer(new int[] {kg+3, cnt+1});
				queue.offer(new int[] {kg+5, cnt+1});
			}
  	}*/
		
		//방법2 : 그리디 - 성공
		int five = N/5;
		for(int i=five;i>=0;i--) {
			if((N-5*i)%3==0) {
				System.out.println(i+(N-5*i)/3);
				break;
			}
			if(i==0) {
				System.out.println(-1);
				break;
			}
		}
		
	}

}
