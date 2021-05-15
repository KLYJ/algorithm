package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_2096 {

	static int arr[][], maxArr[][], minArr[][];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());

		arr = new int[N][3];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < 3; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		maxArr = new int[2][3];
		minArr = new int[2][3];
		System.arraycopy(arr[0], 0, maxArr[0], 0, 3);
		System.arraycopy(arr[0], 0, minArr[0], 0, 3);
		Arrays.fill(minArr[1], Integer.MAX_VALUE);

		for (int i = 1; i < N; i++) {
			cal(i, 0, 0);
			cal(i, 0, 1);

			cal(i, 1, 0);
			cal(i, 1, 1);
			cal(i, 1, 2);

			cal(i, 2, 1);
			cal(i, 2, 2);

//			System.out.println("max");
//			System.out.println(Arrays.toString(maxArr[0]));
//			System.out.println(Arrays.toString(maxArr[1]));
//			System.out.println("min");
//			System.out.println(Arrays.toString(minArr[0]));
//			System.out.println(Arrays.toString(minArr[1]));

			// 이동
			System.arraycopy(maxArr[1], 0, maxArr[0], 0, 3);
			System.arraycopy(minArr[1], 0, minArr[0], 0, 3);
			Arrays.fill(maxArr[1], 0);
			Arrays.fill(minArr[1], Integer.MAX_VALUE);

		}

		int max = 0;
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < 3; i++) {
			if (max < maxArr[0][i])
				max = maxArr[0][i];
			if (min > minArr[0][i])
				min = minArr[0][i];
		}

		System.out.println(max + " " + min);

	}

	private static void cal(int i, int j, int down_j) {
		int maxSum = maxArr[0][j] + arr[i][down_j];

		if (maxSum > maxArr[1][down_j])
			maxArr[1][down_j] = maxSum;

		int minSum = minArr[0][j] + arr[i][down_j];
		if (minSum < minArr[1][down_j])
			minArr[1][down_j] = minSum;
	}

}
