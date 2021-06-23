package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class BJ_1759 {

	static StringBuilder sb = new StringBuilder();
	static Set<Character> moeum = new HashSet<>();
	static char ch[];
	static int L, C;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		ch = new char[C];
		st = new StringTokenizer(in.readLine(), " ");
		for (int i = 0; i < C; i++)
			ch[i] = st.nextToken().charAt(0);
		Arrays.sort(ch);

		// 모음을 set에 추가
		moeum.add('a');
		moeum.add('e');
		moeum.add('i');
		moeum.add('o');
		moeum.add('u');
		comb2(0, 0, new char[L], 0);

		System.out.println(sb.toString());
	}

	// 가지치기 후
	private static void comb2(int selectTo, int start, char[] selected, int mo) {
		// 가지치기 - 자음 최소 2개 이상인지 여부 확인
		if(mo > L-2)
			return;
		
		if (selectTo == L) {
			// 모음이 하나도 없으면 안됨
			if(mo == 0)
				return;
			
			sb.append(String.valueOf(selected)).append("\n");
			return;
		}

		for (int i = start; i < C; i++) {
			selected[selectTo] = ch[i];
			comb2(selectTo + 1, i + 1, selected, moeum.contains(ch[i])?mo+1:mo);
		}

	}

}
