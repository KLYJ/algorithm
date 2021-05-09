package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class BJ_3584 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		int T = Integer.parseInt(in.readLine());

		for (int t = 1; t <= T; t++) {
			int N = Integer.parseInt(in.readLine());

			int parents[] = new int[N + 1];
			for (int i = 0; i < N - 1; i++) {
				st = new StringTokenizer(in.readLine(), " ");
				int A = Integer.parseInt(st.nextToken());
				int B = Integer.parseInt(st.nextToken());

				parents[B] = A;
			}

			st = new StringTokenizer(in.readLine(), " ");
			int find_A = Integer.parseInt(st.nextToken());
			int find_B = Integer.parseInt(st.nextToken());

			int answer = 0;
			Set<Integer> A_parents = new HashSet<>();
			Set<Integer> B_parents = new HashSet<>();
			while (true) {
				A_parents.add(find_A);
				find_A = parents[find_A];

				B_parents.add(find_B);
				find_B = parents[find_B];

				if (A_parents.contains(find_B)) {
					answer = find_B;
					break;
				}

				if (B_parents.contains(find_A)) {
					answer = find_A;
					break;
				}

				if (find_A == find_B) {
					answer = find_A;
					break;
				}

			}

			sb.append(answer).append("\n");
		}
		System.out.println(sb.toString());
	}

}
