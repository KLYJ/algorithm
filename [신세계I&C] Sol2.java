package shinsagae;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Sol2 {
	
	public static void main(String[] args) {
		System.out.println(solution(2, new int[][] {{10,3,2},{15,2,5}}));
		System.out.println(solution(3, new int[][] {{6,5,1},{11,3,2}, {7,10,3}}));
	}
	
	public static int solution(int n, int products[][]) {
		int answer = 0;
		
		Queue<Integer> list[] = new LinkedList[products.length];
		for(int i=0;i<products.length;i++) {
			list[i] = new LinkedList<>();
			
			int stock = products[i][0];
			int price = products[i][1];
			int oneday = products[i][2];
			
			int toN = 0;
			while(stock >= oneday) {
				if(toN < n)
					answer += price*oneday;
				else
					list[i].offer(price*oneday);
				stock -= oneday;
				toN++;
			}
			
			if(stock > 0) {
				if(toN < n)
					answer += price*stock;
				else
					list[i].offer(price*stock);
			}
		}
		
		PriorityQueue<Integer> spare = new PriorityQueue<>(); 
		for(int i=0;i<list.length;i++) {
			while(!list[i].isEmpty()) {
				spare.add(-list[i].poll());
			}
		}
		
		for(int i=0;i<n;i++)
			answer += -spare.poll();
		
		return answer;
	}

}
