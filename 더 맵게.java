package Level2;

import java.util.PriorityQueue;

public class 더맵게 {

	public static void main(String[] args) {
		System.out.println(solution(new int[] {1, 2, 3, 9, 10, 12}, 1000));
	}

	public static int solution(int[] scoville, int K) {
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for(int s:scoville)
			pq.add(s);
		
		int answer = 0;
		while(true) {
			int min1 = pq.poll();
			
			if(pq.isEmpty() && min1 < K) {
				answer = -1;
				break;
			}
			if(min1 >= K)
				break;
			
			int min2 = pq.poll();
			pq.add(min1+2*min2);
			answer++;
			System.out.println(pq);
		}
		
		return answer;
	}

}
