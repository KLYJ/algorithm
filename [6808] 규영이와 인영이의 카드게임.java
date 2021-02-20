package com.ssafy.algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Sol_0215_SWEA6808 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());
		StringBuilder sb = new StringBuilder();

		for (int t = 1; t <= T; t++) {
			sb.append("#").append(t).append(" ");

			String info[] = in.readLine().split(" ");
			// 규영, 인영이 9가지 숫자 저장
			int num[] = new int[18];
			for (int i = 1; i <= 18; i++) {
				num[i - 1] = i;
			}
			int kyu[] = new int[9];
			for (int i = 0; i < 9; i++) {
				kyu[i] = Integer.parseInt(info[i]);
				num[kyu[i] - 1] = 0;
			}
			int inn[] = new int[9];
			int inn_cnt = 0;
			for (int i = 0; i < 18; i++) {
				if (num[i] != 0)
					inn[inn_cnt++] = i + 1;
			}

			// 규영, 인영이 비교
//			int win = perm(0, new int[9], new boolean[9], inn, kyu);
			int win = perm2(0, 0, new boolean[9], inn, kyu);

			sb.append(win).append(" ").append(362880 - win).append("\n");

		}

		System.out.println(sb.toString());

	}

	// 방법2 : 뽑으면서 누적합 전달하면서 비교(1683ms)
	public static int perm2(int selectTo, int kyu_sum, boolean visited[], int inn[], int kyu[]) {
		if (selectTo == 9) {
			if(kyu_sum>85)
				return 1;
			else
				return 0;
		}

		int win = 0;
		for (int i = 0; i < 9; i++) {
			if (!visited[i]) {
				if(inn[i]<kyu[selectTo])
					kyu_sum += inn[i]+kyu[selectTo];
				visited[i] = true;
				win += perm2(selectTo + 1, kyu_sum, visited, inn, kyu);
				// 다시 재귀 부르기 전으로 복구해야함!!! 필수!!!!
				visited[i] = false;
				if(inn[i]<kyu[selectTo])
					kyu_sum -= inn[i]+kyu[selectTo];
			}
		}
		return win;
	}

	// 방법1 : 9개 다 뽑고나서 합한 후 비교(2290ms)
	public static int perm(int selectTo, int selected[], boolean visited[], int pick[], int kyu[]) {
		if (selectTo == 9) {
			int ky = 0;
			int iy = 0;
			for (int i = 0; i < 9; i++) {
				if (kyu[i] > selected[i])
					ky += kyu[i] + selected[i];
				else
					iy += kyu[i] + selected[i];
			}
			if (ky > iy)
				return 1;
			else
				return 0;
		}

		int win = 0;
		for (int i = 0; i < 9; i++) {
			if (!visited[i]) {
				selected[selectTo] = pick[i];
				visited[i] = true;
				win += perm(selectTo + 1, selected, visited, pick, kyu);
				visited[i] = false;
			}
		}

		return win;
	}

}
