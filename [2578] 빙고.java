import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_2578 {

	static int bingo = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
    // 빙고판 저장
		int map[][] = new int[5][5];
		for (int i = 0; i < 5; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < 5; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 숫자 부르면 빙고판 확인
		// 빙고 개수 저장
		int bingo[] = new int[12];
		int total_bingo = 0;
		int stop = 0;
		for (int i = 0; i < 5; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			if (stop == 1)
				continue;
			for (int j = 0; j < 5; j++) {
				int call = Integer.parseInt(st.nextToken());

				//빙고판 검사
				for (int m = 0; m < 5; m++) {
					for (int n = 0; n < 5; n++) {
						if (call == map[m][n]) {
							bingo[m]++;
							if (bingo[m] == 5)
								total_bingo++;
							bingo[5 + n]++;
							if (bingo[5 + n] == 5)
								total_bingo++;
							if (m == n) {
								bingo[10]++;
								if (bingo[10] == 5)
									total_bingo++;
							}
							if (m == 4 - n) {
								bingo[11]++;
								if (bingo[11] == 5)
									total_bingo++;
							}
							m = 5;
							break;
						}
					}
				}

				if (stop==0 && total_bingo >= 3) {
					System.out.println(i*5+j+1);
					stop = 1;
				}

			}
		}

	}

}
