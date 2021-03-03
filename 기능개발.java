package Level2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class 기능개발 {

	public static int[] solution(int[] progresses, int[] speeds) {
		Queue<Integer> queue = new LinkedList<Integer>();
		for (int i = 0; i < progresses.length; i++) {
			int days = (100 - progresses[i]) / speeds[i];
			int check = (100 - progresses[i]) % speeds[i];
			queue.offer(check == 0 ? days : days + 1);
		}

		ArrayList<Integer> answer = new ArrayList<>();
		int days = queue.poll();
		int cnt = 1;
		while (!queue.isEmpty()) {
			if (days >= queue.peek()) {
				cnt++;
				queue.poll();
			} else {
				answer.add(cnt);
				cnt = 1;
				days = queue.poll();
			}
		}
		answer.add(cnt);

		int result[] = new int[answer.size()];
		for (int i = 0; i < answer.size(); i++) {
			result[i] = answer.get(i);
		}

		return result;

	}

}
