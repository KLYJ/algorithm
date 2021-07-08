package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_14621 {
	
	static int parents[];
	static char schools[];
	
	static class Edge implements Comparable<Edge>{
		int school1, school2, road;

		public Edge(int school1, int school2, int road) {
			super();
			this.school1 = school1;
			this.school2 = school2;
			this.road = road;
		}

		@Override
		public int compareTo(Edge o) {
			return this.road-o.road;
		}
	}
	
	public static int findSet(int a) {
		if(parents[a] == a)
			return a;
		
		return parents[a] = findSet(parents[a]);	
	}
	
	public static boolean union(int a, int b) {
		if(schools[a] == schools[b])
			return false;
		
		int rootA = findSet(a);
		int rootB = findSet(b);
		
		if(rootA == rootB)
			return false;
		
		parents[rootB] = rootA;
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		
		int S = Integer.parseInt(st.nextToken());  // school
		int R = Integer.parseInt(st.nextToken());  // road
		
		schools = new char[S];
		st = new StringTokenizer(in.readLine(), " ");
		for(int i=0;i<S;i++) {
			schools[i] = st.nextToken().charAt(0);
		}
		
		Edge edges[] = new Edge[R];
		for(int i=0;i<R;i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int s1 = Integer.parseInt(st.nextToken());
			int s2 = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			
			edges[i] = new Edge(s1-1, s2-1, r);
		}
		
		Arrays.sort(edges);
		
		// makeSet
		parents = new int[S];
		for(int i=0;i<S;i++)
			parents[i] = i;
		
		int cnt = 0;
		int result = 0;
		
		for(int i=0;i<R;i++) {
			Edge edge = edges[i];
			if(union(edge.school1, edge.school2)) {
				result += edge.road;
				if(++cnt == S-1)
					break;
			}
		}
		
		System.out.println(cnt!=S-1?-1:result);
	}

}
