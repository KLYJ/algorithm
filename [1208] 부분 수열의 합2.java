package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BJ_1208 {

	static int N, S, arr[];
	static ArrayList<Integer> list1 = new ArrayList<>();
	static ArrayList<Integer> list2 = new ArrayList<>();	
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(in.readLine(), " ");
		arr = new int[N];
		for(int i=0;i<N;i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		makeSet(0, 0, N/2, list1);
		makeSet(0, N/2, N, list2);
		
		Collections.sort(list1);
		Collections.sort(list2);
		
//		System.out.println(list1);
//		System.out.println(list2);
		
		long answer = calc();
		
		if(S==0)
			answer--;
		
		System.out.println(answer);
		
	}

	private static long calc() {
		int pointerL = 0;
		int pointerR = list2.size()-1;
		long cnt = 0;
		
		while(true) {
			if(pointerL == list1.size() || pointerR < 0)
				break;
			
			int lv = list1.get(pointerL);
			int rv = list2.get(pointerR);
//			System.out.println(lv+"/"+rv);
			
			if(lv+rv == S) {
				int lc = 0;
				while(pointerL<list1.size() && lv == list1.get(pointerL)) {
					lc++;
					pointerL++;
				}
				
				int rc = 0;
				while(pointerR>=0 && rv == list2.get(pointerR)) {
					rc++;
					pointerR--;
				}
				
				cnt += lc*rc;
			}
			else if(lv+rv < S)
				pointerL++;
			else
				pointerR--;
		}
		
//		System.out.println(cnt);
		return cnt;
		
	}

	private static void makeSet(int sum, int start, int end, ArrayList<Integer> list) {
		if(start==end) {
			list.add(sum);
			return;
		}
		
		makeSet(sum, start+1, end, list);
		makeSet(sum+arr[start], start+1, end, list);
	}

}
