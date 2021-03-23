import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Sol_0322_BJ1753 {

	static class Edge implements Comparable<Edge>{
		int vertex, weight;

		public Edge(int vertex, int weight) {
			this.vertex = vertex;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		int start = Integer.parseInt(in.readLine()) - 1;

		int distance[] = new int[V];
		Arrays.fill(distance, Integer.MAX_VALUE);
		boolean visited[] = new boolean[V];

		ArrayList<List<Edge>> list = new ArrayList<>();
		for(int i=0;i<V;i++) {
			list.add(new ArrayList<Edge>());
		}
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int u = Integer.parseInt(st.nextToken()) - 1;
			int v = Integer.parseInt(st.nextToken()) - 1;
			int w = Integer.parseInt(st.nextToken());
			list.get(u).add(new Edge(v, w));
		}

		distance[start] = 0;

		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.add(new Edge(start, 0));

		while (!pq.isEmpty()) {
			int v = pq.poll().vertex;
			
			if(visited[v])
				continue;
			visited[v] = true;

			for(Edge e:list.get(v)) {
				if (!visited[e.vertex] && e.weight < distance[e.vertex]) {
					distance[e.vertex] = Math.min(distance[e.vertex], distance[v] + e.weight);
					pq.add(new Edge(e.vertex, distance[e.vertex]));
				}
			}
		}

		for (int i = 0; i < V; i++) {
			System.out.println(distance[i] == Integer.MAX_VALUE ? "INF" : distance[i]);
		}

	}

}
