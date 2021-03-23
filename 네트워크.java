import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int solution(int n, int[][] computers) {
        int answer = 0;
        boolean visited[] = new boolean[n];
        
        Queue<Integer> q = new LinkedList<Integer>();
        for(int i=0;i<n;i++) {
        	if(!visited[i]) {
        		answer++;
        		
        		q.offer(i);
        		while(!q.isEmpty()) {
        			int idx = q.poll();
        			visited[idx] = true;
        			
        			for(int j=0;j<n;j++) {
        				if(!visited[j] && computers[idx][j]!=0) {
        					q.offer(j);
        				}
        			}
        		}
        	}
        }
        return answer;
    }
}
