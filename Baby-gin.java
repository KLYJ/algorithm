import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sol_0203_1_baby_gin {

	static int num[]; // 뽑을 수
	static int pick[]; // 뽑힌 수
	static boolean selected[];

	private static boolean perm(int cnt, int total) {
		if (cnt == 6) {
			// solve 불러서 baby-gin인지 확인
			if (solve())
				return true;
			// 다 아닐때는?
			else {
				if (total == 720)
					return false;
			}
		}
		for (int i = 0; i < 6; i++) {
			if (selected[i] == true)
				continue;
			pick[cnt] = num[i];
			selected[i] = true;
			if (perm(cnt + 1, total + 1))
				return true;
			selected[i] = false;
		}
		return false;
	}

	private static boolean solve() {
		int cnt = 0;
		for (int i = 0; i < 4; i += 3) {
			if (pick[i] + 1 == pick[i + 1] && pick[i + 1] + 1 == pick[i + 2]
					|| pick[i] - 1 == pick[i + 1] && pick[i + 1] - 1 == pick[i + 2])
				cnt++;
			if (pick[i] == pick[i + 1] && pick[i + 1] == pick[i + 2])
				cnt++;
		}
		if (cnt == 2)
			return true;
		else
			return false;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());

		for (int i = 1; i < T + 1; i++) {

			// 방법1 : 완전 탐색 - 순열
			num = new int[6];
			pick = new int[6];
			selected = new boolean[6];
			
			String input[] = in.readLine().split(" ");
			
			for(int j=0;j<6;j++)
				num[j] = input[j].charAt(0)-'0';
			
			if(perm(0, 1))
				System.out.println("#"+i+" "+"yes");
			else
				System.out.println("#"+i+" "+"no");

			// 방법2 : 계수 배열 이용
			int num[] = new int[10]; // 계수 배열
			String input[] = in.readLine().split(" ");
			for (int j = 0; j < 6; j++)
				num[Integer.parseInt(input[j])]++;

			int cnt = 0;
			for (int j = 0; j < 10; j++) {
				if (num[j] >= 3) {
					cnt++;
					num[j] -= 3;
					j--;
					continue;
				}
				if (num[j] > 0 && j - 1 > -1 && j + 1 < 6 && num[j - 1] > 0 && num[j + 1] > 0) {
					cnt++;
					num[j - 1]--;
					num[j]--;
					num[j + 1]--;
					j--;
					continue;
				}
			}
			
			if(cnt==2) System.out.println("yes");
			else System.out.println("no");

		}

	}

}
