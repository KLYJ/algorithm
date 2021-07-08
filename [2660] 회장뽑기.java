package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_2660 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		int N = Integer.parseInt(in.readLine());
		int d[][] = new int[N + 1][N + 1];

		while (true) {
			st = new StringTokenizer(in.readLine(), " ");
			int f1 = Integer.parseInt(st.nextToken());
			int f2 = Integer.parseInt(st.nextToken());

			if (f1 == -1)
				break;

			d[f1][f2] = 1;
			d[f2][f1] = 1;
		}

		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (i == j)
						continue;

					if (d[i][k] != 0 && d[k][j] != 0) {
						if (d[i][j] == 0)
							d[i][j] = d[i][k] + d[k][j];
						else {
							d[i][j] = Math.min(d[i][j], d[i][k] + d[k][j]);
						}
					}
				}
			}
		}

		int min = Integer.MAX_VALUE;
		ArrayList<Integer> list = new ArrayList<>();

		for (int i = 1; i <= N; i++) {
			int score = 0;
			for (int j = 1; j <= N; j++) {
				if (i != j && d[i][j] > score)
					score = d[i][j];
			}

			if (score < min) {
				min = score;
				list.clear();
				list.add(i);
			} else if (score == min) {
				list.add(i);
			}
		}

		System.out.println(min + " " + list.size());
		for (int i : list) {
			System.out.print(i + " ");
		}

	}

}
