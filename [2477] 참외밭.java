package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_2477 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int K = Integer.parseInt(in.readLine());

		int dir[] = new int[10];
		int num[] = new int[10];
		int garo = 0;
		int sero = 0;
		for (int i = 0; i < 6; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			dir[i+2] = Integer.parseInt(st.nextToken());
			num[i+2] = Integer.parseInt(st.nextToken());
			if (i % 2 == 0)
				garo = Math.max(garo, num[i+2]);
			else
				sero = Math.max(sero, num[i+2]);
		}

		dir[0] = dir[6];
		dir[1] = dir[7];
		dir[8] = dir[2];
		dir[9] = dir[3];
		num[0] = num[6];
		num[1] = num[7];
		num[8] = num[2];
		num[9] = num[3];
		
		int idx1 = 0;
		int idx2 = 0;
		for (int i = 2; i <= 7; i++) {
			if (dir[i - 1] == dir[i + 1]) {
				if (dir[i + 2] == dir[i]) {
					idx1 = i;
					idx2 = i + 1;
					break;
				}
				
				if (dir[i - 2] == dir[i]) {
					idx1 = i - 1;
					idx2 = i;
					break;
				}

			}

		}

		System.out.println(K * (garo * sero - num[idx1] * num[idx2]));

	}

}
