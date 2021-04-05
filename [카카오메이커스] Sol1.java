package kakao;

import java.util.Arrays;

public class Sol1 {
	
	public static void main(String[] args) {
//		System.out.println(solution(new int[] {4,5,3,2,1}, new int[] {2,4,4,5,1}));
		System.out.println(solution(new int[] {5,4,5,4,5}, new int[] {1,2,3,5,4}));
	}
	
	static int gift[], want[], N, answer = Integer.MAX_VALUE;
	static boolean complete[];
	
	public static int solution(int[] gift_cards, int[] wants) {
		N = wants.length;
		gift = gift_cards;
		want = wants;
		
		// have = want 저장
		complete = new boolean[N];
		for(int i=0;i<N;i++) {
			if(gift_cards[i] == wants[i]) {
				complete[i] = true;
			}
		}
		
		dfs(0, gift_cards); //selectTo, 현재 have  
		
        return answer;
    }

	private static void dfs(int selectTo, int[] have) {
		System.out.println(Arrays.toString(have));
		System.out.println(Arrays.toString(complete));
		if(selectTo == N) {
			int cnt = 0;
			for(int i=0;i<N;i++) {
				if(have[i] != want[i])
					cnt++;
			}
			answer = Math.min(answer, cnt);
			return;
		}
		
		int have_val = have[selectTo];
		int want_val = want[selectTo];
		
		// have = want
		if(have_val == want_val) {
			complete[selectTo] = true;
		} 
		// have != want
		else {
			for(int i=0;i<N;i++) {
				if(want_val == have[i] && !complete[i]) {
					have[selectTo] = have[i];
					have[i] = have_val;
					complete[selectTo] = true;
					break;
				}
			}
		}
		
		dfs(selectTo+1, have);
	}

}
