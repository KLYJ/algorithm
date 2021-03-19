package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//dp 방식
public class BJ_1697_2 {
   
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int dp[] = new int[2*K+1];
		Arrays.fill(dp, Integer.MAX_VALUE);
		
		for(int i=0;i<2*K+1;i++) {
			if(i<=N) {
				dp[i] = N-i;
				continue;
			}
			
			if(i%2==0) 
				dp[i] = Math.min(dp[i], Math.min(dp[i-1]+1, dp[i/2]+1));
			else
				dp[i] = Math.min(dp[i], dp[i-1]+1);
			
			for(int j=1;j<=i;j++) {
				if(dp[i]+j < dp[i-j]) {
					dp[i-j] = dp[i]+j;
				}
				else {
					break;
				}
			}
		}
		
		System.out.println(dp[K]);
		
	}

}

//BFS 
public class BJ_1697_3 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		if(N >= K) {
			System.out.println(N-K);
			return;
		}
		
		Queue<Integer> queue = new LinkedList<>();
		HashMap<Integer, Integer> map = new HashMap<>();
		map.put(N, 0);
		queue.offer(N);
		
		while(!queue.isEmpty()) {
			int idx = queue.poll();
			
			if(idx==K) {
				System.out.println(map.get(idx));
				break;
			}
			
			if(!map.containsKey(2*idx) && 2*idx < 2*K) {
				map.put(2*idx, map.get(idx)+1);
				queue.offer(2*idx);
			}
			if(!map.containsKey(idx+1) && idx+1< 2*K) {
				map.put(idx+1, map.get(idx)+1);
				queue.offer(idx+1);
			}
			if(!map.containsKey(idx-1) && idx-1 > 0) {
				map.put(idx-1, map.get(idx)+1);
				queue.offer(idx-1);
			}
		}

	}
}
