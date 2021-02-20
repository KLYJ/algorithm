import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

public class SWEA_1974 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());

		StringBuilder sb = new StringBuilder();

		for (int t = 1; t <= 10; t++) {
			int map[][] = new int[9][9];
			for (int i = 0; i < 9; i++) {
				String s[] = in.readLine().split(" ");
				for (int j = 0; j < 9; j++) {
					map[i][j] = Integer.parseInt(s[j]);
				}
			}

			sb.append("#").append(t).append(" ");

			int flag = 0;
			for (int i = 0; i < 9; i++) {
				int sum1 = 0;
				int sum2 = 0;
				for (int j = 0; j < 9; j++) {
					sum1 += map[i][j];
					sum2 += map[j][i];
				}
				if (sum1 != 45 || sum2 != 45) {
					sb.append(0);
					flag = 1;
					break;
				}
			}

			//방법 1 : 인덱스 배열 미리 선언 (box)
			int idx[][] = {{0,0},{3,0},{6,0},{3,0},{3,3},{3,6},{6,0},{6,3},{6,6}};
			for(int id[]:idx) {
				int x = id[0];
				int y = id[1];
				int sum = 0;
				for(int i=x;i<x+3;i++) {
					for(int j=y;j<y+3;j++) {
						sum += map[i][j];
					}
				}
				if(sum!=45) {
					sb.append(0);
					flag = 1;
					break;
				}
			}

			// 방법2: delta 이용 (box)
			int dx[] = { 1, 1, 1, 0, -1, -1, -1, 0 };
			int dy[] = { 0, -1, 1, 1, 0, -1, 1, -1 };
			for (int i = 1; i <= 7; i += 3) {
				for (int j = 1; j <= 7; j += 3) {
					int sum = map[i][j];
					for (int k = 0; k < 8; k++)
						sum += map[i + dx[k]][j + dy[k]];
					if (sum != 45) {
						sb.append(0);
						flag = 1;
						i=8; j=8;
					}
				}
			}

			// 방법3 : boolean 배열 이용 (1~9) 두번 쓰인 수 검사(가로, 세로, box 한번에 )
			boolean garo[][] = new boolean[9][10];
			boolean sero[][] = new boolean[9][10];
			boolean nemo[][] = new boolean[9][10];
			int flag = 0;
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					int temp = map[i][j];
					if (garo[j][temp] || sero[i][temp] || nemo[3 * (i / 3) + j / 3][temp]) {
						sb.append(0);
						i = 9;
						j = 9;
						flag = 1;
					} else {
						garo[j][temp] = true;
						sero[i][temp] = true;
						nemo[3 * (i / 3) + j / 3][temp] = true;
					}
				}
			}

			sb.append(flag == 0 ? "1\n" : "\n");
		}

		System.out.println(sb.toString());

	}

}
