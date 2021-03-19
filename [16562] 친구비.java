import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.StringTokenizer;

public class BJ_16562_2 {
	
	static int N;
	static int parents[], fee[];
	
	static void makeSet() {
		for(int i=0;i<N;i++) {
			parents[i] = i;
		}
	}
	
	static int find(int a) {
		if(parents[a] == a)
			return a;
		return parents[a] = find(parents[a]);
	}
	
	static void union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if(fee[bRoot] < fee[aRoot]) { //최상위 부모노드를 비교해야함!!! (그냥 a, b로 비교해서 계속 틀림)
			parents[aRoot] = bRoot;
			find(a);
		}
		else {
			parents[bRoot] = aRoot;
			find(b);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); //학생
		int M = Integer.parseInt(st.nextToken()); //친구관계
		int K = Integer.parseInt(st.nextToken()); //돈
		
		fee = new int[N]; //친구비 배열
		st = new StringTokenizer(in.readLine(), " ");
		for(int i=0;i<N;i++) {
			fee[i] = Integer.parseInt(st.nextToken());
		}
		
		parents = new int[N];
		makeSet();
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			union(a, b);
		}
		
		for(int i=0;i<N;i++) {
			find(i);
		}
		
		System.out.println(Arrays.toString(parents));
		
		int friends_fee = 0;
		for(int i=0;i<N;i++) {
			if(parents[i] == i) {
				friends_fee += fee[i];
			}
		}
		
		if(friends_fee > K) {
			System.out.println("Oh no");
		}
		else {
			System.out.println(friends_fee);
		}
		
		
	}

}
