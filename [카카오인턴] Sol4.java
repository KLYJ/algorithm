package kakao_intern_21;

import java.util.ArrayList;
import java.util.LinkedList;

public class Sol4 {

	static int N, end, answer;
	static ArrayList<Integer> trap = new ArrayList<>();

	public static void main(String[] args) {
		System.out.println(solution(3, 1, 3, new int[][] { { 1, 2, 2 }, { 3, 2, 3 } }, new int[] { 2 }));
		System.out
				.println(solution(4, 1, 4, new int[][] { { 1, 2, 1 }, { 3, 2, 1 }, { 2, 4, 1 } }, new int[] { 2, 3 }));
	}

	public static int solution(int n, int start, int e, int[][] roads, int[] traps) {
		N = n;
		end = e;

		for (int i = 0; i < traps.length; i++)
			trap.add(traps[i]);

		int matrix[][] = new int[n + 1][n + 1];
		for (int i = 0; i < roads.length; i++) {
			int p = roads[i][0];
			int q = roads[i][1];
			int s = roads[i][2];

			matrix[p][q] = s;
		}

//		for (int i = 0; i < n + 1; i++)
//			System.out.println(Arrays.toString(matrix[i]));

		answer = Integer.MAX_VALUE;

		LinkedList<Integer> history = new LinkedList<>();
		DFS(matrix, start, 0, history);

		return answer;
	}

	private static void DFS(int matrix[][], int now, int time, LinkedList<Integer> history) {
//		System.out.println(now + "/" + time);

		if (now == end) {
//			System.out.println("time:" + time);
			answer = Math.min(answer, time);
			return;
		}

		if (time >= answer)
			return;

		int copyM[][] = new int[N + 1][N + 1];
		for (int i = 0; i < N + 1; i++)
			System.arraycopy(matrix[i], 0, copyM[i], 0, N + 1);

		boolean visited[][] = new boolean[N + 1][N + 1];

		// 사이클 여부 검사
		if(history.contains(now)) {
			
		}

		// 트랩에 왔다면
		if (trap.contains(now)) {
			// 인접행렬 바꿔주기 : 내가 받는 화살표
			for (int i = 1; i < N + 1; i++) {
				int s = copyM[i][now];
				if (s != 0 && !visited[i][now]) {
					copyM[i][now] = 0;
					copyM[now][i] = s;
					visited[i][now] = true;
					visited[now][i] = true;
				}
			}
			// 인접행렬 바꿔주기 : 내가 보내는 화살표
			for (int i = 1; i < N + 1; i++) {
				int s = copyM[now][i];
				if (s != 0 && !visited[now][i]) {
					copyM[i][now] = s;
					copyM[now][i] = 0;
				}
			}

//			for (int i = 0; i < N + 1; i++)
//				System.out.println(Arrays.toString(copyM[i]));
		}

		// 이동
		history.add(now);
		for (int i = 1; i < N + 1; i++) {
			int s = copyM[now][i];
			if (s != 0) {
				DFS(copyM, i, time + s, history);
			}
		}

	}

}
