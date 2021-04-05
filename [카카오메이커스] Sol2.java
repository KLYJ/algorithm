package kakao;

import java.util.ArrayList;
import java.util.Arrays;

public class Sol2_2 {
	
	public static void main(String[] args) {
		System.out.println(solution(
				new int[][] { { 1, 0, 0 }, { 1, 1, 0 }, { 1, 1, 0 }, { 1, 0, 1 }, { 1, 1, 0 }, { 0, 1, 1 } }, 2));
	}
	
	static int len, answer = Integer.MIN_VALUE;
	static ArrayList<Integer> list[];
	
	public static int solution(int[][] needs, int r) {
		list = new ArrayList[needs.length];
		for(int i=0;i<needs.length;i++) {
			list[i] = new ArrayList<>();
			for(int j=0;j<needs[0].length;j++) {
				if(needs[i][j]==1)
					list[i].add(j);
			}
		}
		
		// 조합 만들기
		len = needs[0].length;
		comb(0, r, new int[r], 0);
		
        return answer;
    }

	private static void comb(int selectTo, int r, int[] selected, int start) {
		if(selectTo == r) {
			ArrayList<Integer> temp = new ArrayList<>();
			for(int i=0;i<r;i++)
				temp.add(selected[i]);
			
			int cnt = 0;
			for(int i=0;i<list.length;i++) {
				boolean check = true;
				for(int j=0;j<list[i].size();j++) {
					if(!temp.contains(list[i].get(j))) {
						check = false;
						break;
					}
				}
				if(check)
					cnt++;
			}
			
			answer = Math.max(answer, cnt);
			return;
		}
		
		for(int i=start;i<len;i++) {
			selected[selectTo] = i;
			comb(selectTo+1, r, selected, i+1);
		}
	}

}
