package Level3;

public class 단어변환 {

	static String tg, word_list[];
	static int answer = Integer.MAX_VALUE;

	public static int solution(String begin, String target, String[] words) {
		tg = target;
		word_list = words;

		dfs(begin, 0, new boolean[words.length]);

		return answer == Integer.MAX_VALUE ? 0 : answer;
	}

	private static void dfs(String word, int cnt, boolean visited[]) {
		if (word.equals(tg)) {
			answer = Math.min(answer, cnt);
			return;
		}

		for (int i = 0; i < word_list.length; i++) {
			int diff = 0;
			if (!visited[i]) {
				for (int j = 0; j < word.length(); j++) {
					if (word.charAt(j) != word_list[i].charAt(j)) {
						diff++;
					}
				}
				if (diff == 1) {
					visited[i] = true;
					dfs(word_list[i], cnt + 1, visited);
					visited[i] = false;
				}
			}
		}
	}

}
