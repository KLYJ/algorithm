package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_10163 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());

		int board[][] = new int[101][101];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());
			for (int m = x; m < x + w; m++) {
				for (int n = y; n < y + h; n++) {
					board[m][n] = i+1;
				}
			}
		}

		int answer[] = new int[N+1];
		for (int i = 0; i < 101; i++) {
			for (int j = 0; j < 101; j++) {
				answer[board[i][j]]++;
			}
		}

		for (int i = 1; i < N+1; i++)
			System.out.println(answer[i]);
	}

}
