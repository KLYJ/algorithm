package line;

import java.util.Arrays;
import java.util.HashSet;

public class Solution3 {

	public static void main(String[] args) {
		System.out.println(Arrays.toString(solution(new int[] {1,3,2}, new int[] {1,2,3})));
		System.out.println(Arrays.toString(solution(new int[] { 1, 4, 2, 3 }, new int[] { 2, 1, 3, 4 })));
		System.out.println(Arrays.toString(solution(new int[] {3,2,1}, new int[] {2,1,3})));
		System.out.println(Arrays.toString(solution(new int[] {3,2,1}, new int[] {1,3,2})));
		System.out.println(Arrays.toString(solution(new int[] {1,4,2,3}, new int[] {2,1,4,3})));
	}

	public static int[] solution(int[] enter, int[] leave) {
		int len = enter.length;

		boolean in[] = new boolean[len];
		HashSet<Integer>[] met = new HashSet[len];
		for (int i = 0; i < len; i++) {
			met[i] = new HashSet<>();
		}

		int out = 0;
		for (int i = 0; i < len; i++) {
			int en = enter[i] - 1;
			in[en] = true;
			for (int j = 0; j < len; j++) {
				if (en != j && in[j]) {
					met[en].add(j);
					met[j].add(en);
				}
			}
			int le = leave[out] - 1;
			while (in[le]) {
				in[le] = false;
				out++;
				if (out >= len)
					break;
				le = leave[out] - 1;
			}
		}

		int[] answer = new int[len];
		for (int i = 0; i < len; i++) {
			answer[i] = met[i].size();
		}
		return answer;
	}

}
