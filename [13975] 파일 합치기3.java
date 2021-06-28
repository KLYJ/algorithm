package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_13975 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		int TC = Integer.parseInt(in.readLine());
		
		for(int t=1;t<=TC;t++) {
			int N = Integer.parseInt(in.readLine());
			
			st = new StringTokenizer(in.readLine(), " ");
			PriorityQueue<Long> pq = new PriorityQueue<>();
			for(int i=0;i<N;i++) {
				pq.add(Long.parseLong(st.nextToken()));
			}
			
			long answer = 0;

			while(pq.size() > 1) {
				long hap = pq.poll()+pq.poll();
				answer += hap;
				pq.add(hap);
			}
			pq.poll();
			
			sb.append(answer).append("\n");
		}
		
		System.out.println(sb.toString());
	}
}
