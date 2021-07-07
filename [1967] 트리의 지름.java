package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_1967 {
	
	static class Node{
		int parent, weight, max, hap;
		
		public Node(int p, int w, int m, int h) {
			this.parent = p;
			this.weight = w;
			this.max = m;
			this.hap = h;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int N = Integer.parseInt(in.readLine());

		Node nodes[] = new Node[N+1];
		nodes[0] = new Node(0,0,0,0);
		nodes[1] = new Node(0,0,0,0);
		
		for(int i=0;i<N-1;i++) {
			st = new StringTokenizer(in.readLine(), " ");
			
			int parent = Integer.parseInt(st.nextToken());
			int child = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			nodes[child] = new Node(parent, weight, 0, 0);
		}
		
		int answer = 0;
		
		for(int i=N;i>=1;i--) {
			int parent = nodes[i].parent;
			int weight = nodes[i].weight;
			int max = nodes[i].max;
			int hap = nodes[i].hap;
			
			int bridge = max+weight;  // 현재 child가 parent와의 연결된 가중치 (max)
			
			answer = Math.max(answer, hap);
			
			int parent_max = nodes[parent].max;
			if(nodes[parent].hap < parent_max+bridge) {
				nodes[parent].hap = bridge+parent_max;
				nodes[parent].max = Math.max(parent_max, bridge);
			}
		}
		
		System.out.println(answer);
	}
}
