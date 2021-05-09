import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

	static ArrayList<int[]> chicken = new ArrayList<>();
	static ArrayList<int[]> home = new ArrayList<>();
	static int ans = Integer.MAX_VALUE;
	static int M;
	static int csize;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String info[] = in.readLine().split(" ");
		int N = Integer.parseInt(info[0]);
		M = Integer.parseInt(info[1]);

		// 입력
		for (int i = 0; i < N; i++) {
			info = in.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				if (info[j].equals("1"))
					home.add(new int[] { i, j });
				else if (info[j].equals("2"))
					chicken.add(new int[] { i, j });
			}
		}

		// 방법2 : 조합
		csize = chicken.size();
		comb(0, new int[M], 0);
		System.out.println(ans);

	}

	private static void comb(int selectTo, int[] selected, int start) {
		if (selectTo == M) {
			// 치킨집 조합에 따라 도시의 치킨 거리 구하기
			int ch_dis = 0; // 도시의 치킨 거리
			for (int[] ho : home) {
				int min_dis = Integer.MAX_VALUE; // 한 집의 치킨 거리
				for (int i = 0; i < M; i++) {
					min_dis = Math.min(min_dis, Math.abs(chicken.get(selected[i])[0] - ho[0])+ Math.abs(chicken.get(selected[i])[1] - ho[1]));
				}
				ch_dis += min_dis;
			}
			ans = Math.min(ans, ch_dis);
			return;
		}

		for (int i = start; i < csize; i++) {
			selected[selectTo] = i;
			comb(selectTo + 1, selected, i + 1);
		}
	}
}
