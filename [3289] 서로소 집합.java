import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Sol_0318_SWEA3289 {
	
	static int N;
	static int parents[];
	
	static void makeSet() {
		for(int i=0;i<N;i++) {
			parents[i] = i;
		}
	}
	
	static int findSet(int a) {
		if(parents[a] == a) {
			return a;
		}
		return parents[a] = findSet(parents[a]);
	}
	
	static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		
		if(aRoot==bRoot) {
			return false;
		}
		
		parents[bRoot] = aRoot;
		return true;		
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		int T = Integer.parseInt(in.readLine());
		
		for(int t=1;t<=T;t++) {
			sb.append("#").append(t).append(" ");
			
			st = new StringTokenizer(in.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			
			parents = new int[N];
			makeSet();
			
			for(int i=0;i<m;i++) {
				st = new StringTokenizer(in.readLine(), " ");
				int type = Integer.parseInt(st.nextToken());
				int a = Integer.parseInt(st.nextToken())-1;
				int b = Integer.parseInt(st.nextToken())-1;
				
				if(type==0) { //union
					union(a, b);
				}
				else { //find
					if(findSet(a)==findSet(b))
						sb.append(1);
					else
						sb.append(0);
				}
				
			}
			
			
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
}
