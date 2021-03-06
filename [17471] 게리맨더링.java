package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_17471_2 {

	static int graph[][];
	static boolean visited[];
	static int N, sum, people[], answer = Integer.MAX_VALUE;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());

		people = new int[N];
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		sum = 0;
		for (int i = 0; i < N; i++) {
			people[i] = Integer.parseInt(st.nextToken());
			sum += people[i];
		}

		graph = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int len = Integer.parseInt(st.nextToken());
			for (int j = 0; j < len; j++) {
				int con = Integer.parseInt(st.nextToken()) - 1;
				graph[i][con] = 1;
				graph[con][i] = 1;
			}
		}

		subset(0, new int[N], 0);

		System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);

	}

	private static void subset(int selectTo, int[] selected, int select_sum) {
		if (selectTo == N) {
			if(select_sum == sum || select_sum==0) {
				return;
			}

			visited = new boolean[N];
			int section = 0;
			for (int i = 0; i < N; i++) {
				if (!visited[i]) {
					check(selected, selected[i], i);
					section++;
				}
			}

			if(section!=2) {
				return;
			}

			answer = Math.min(answer, Math.abs(2 * select_sum - sum));

			return;
		}

		selected[selectTo] = 1;
		subset(selectTo + 1, selected, select_sum + people[selectTo]);

		selected[selectTo] = 2;
		subset(selectTo + 1, selected, select_sum);
	}

	private static void check(int[] selected, int check, int idx) {
		visited[idx] = true;
		for (int i = 0; i < N; i++) {
			if (!visited[i] && selected[i] == check && graph[idx][i] == 1)
				check(selected, check, i);
		}
	}

}
