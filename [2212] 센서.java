package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class BJ_2212 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		int K = Integer.parseInt(in.readLine());
		
		Set<Integer> set = new HashSet<>();
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		for(int i=0;i<N;i++)
			set.add(Integer.parseInt(st.nextToken()));
		
		ArrayList<Integer> list = new ArrayList<>(set);
		int arr[] = new int[list.size()];
		for(int i=0;i<list.size();i++)
			arr[i] = list.get(i);
		Arrays.sort(arr);
		
		Integer cha[] = new Integer[arr.length-1];
		int left = arr[0];
		int cha_sum = 0;
		for(int i=1;i<arr.length;i++) {
			int right = arr[i];
			cha[i-1] = right-left;
			cha_sum += cha[i-1];
			left = right;
		}
		Arrays.sort(cha, Collections.reverseOrder());
		
		int answer = Integer.MAX_VALUE;
		if(K==1) {
			System.out.println(arr[arr.length-1]-arr[0]);
			return;
		}
		else if(K >= N) {
			System.out.println(0);
			return;
		}
	
		for(int i=2;i<=K;i++) {
			int ans = cha_sum;
			for(int j=0;j<i-1;j++) {
				ans -= cha[j];
			}
			answer = Math.min(answer, ans);
		}
		
		System.out.println(answer);
	}

}
