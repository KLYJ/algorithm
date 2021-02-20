import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Sol_0217_BJ17135 {

	static int col, row;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		row = Integer.parseInt(st.nextToken());
		col = Integer.parseInt(st.nextToken());
		int dis = Integer.parseInt(st.nextToken());

		int map[][] = new int[row][col];
		for (int i = 0; i < row; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < col; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		//D=1일 때는 열마다 적의 수만 보면 됨
		//적의 수가 제일 많은 열 3개 고르면 됨
		if (dis == 1) {
			int col_arr[] = new int[col];
			for (int i = 0; i < col; i++) {
				for (int j = 0; j < row; j++) {
					if (map[j][i] == 1)
						col_arr[i]++;
				}
			}
			int answer = 0;
			Arrays.sort(col_arr);
			for (int i = col - 1; i > col - 4; i--) {
				answer += col_arr[i];
			}
			System.out.println(answer);
		} 
		//D>1일 떄는 시뮬레이션 해야함
		else {
			//np 사용
			int flag[] = new int[col];
			int cnt = 0;
			while (++cnt <= 3) {
				flag[col - cnt] = 1;
			}

			int answer = 0;
			int copy_map[][] = new int[row][col];
			boolean visited[][] = new boolean[row][col];
			do {
				int die = 0;
				//map copy & visited 초기화
				for (int i = 0; i < row; i++) {
					System.arraycopy(map[i], 0, copy_map[i], 0, col);
					for(int k=0;k<col;k++) {
						visited[i][k] = false;
					}
				}

				//행의 밑에서부터 검사
				for (int i = row - 1; i >= 0; i--) {
					for (int j = 0; j < col; j++) {
						if (flag[j] == 1) {  // 궁수를 놓은 위치면
							die += attack(i, j, dis - 1, copy_map, visited); //dis 안에 적이 있는지 검사
						}
					}
					//visited 리셋
					for(int j=0;j<row;j++) {
						for(int k=0;k<col;k++) {
							visited[j][k] = false;
						}
					}
				}
				answer = Math.max(answer, die);
			} while (np(flag));
			
			System.out.println(answer);
		}

	}

	private static int attack(int r, int c, int dis, int[][] map, boolean visited[][]) {
		//현재 위치 먼저 검사
		if(!visited[r][c]) { //아무도 안죽임 = 내가 죽일거
			if(map[r][c] == 1) {
				map[r][c] = 0;
				visited[r][c] = true;
				return 1;
			}
		}
		else { //앞에서 누가 죽임 = 같은 애 죽임
			if(map[r][c] == 0) {
				return 0;
			}
		}

		//왼쪽부터 산모양으로 검사
		for (int i = 1; i <= dis; i++) {
			for (int j = c - i, h = r; j <= c + i; j++) {
				if (h >= 0 && h < row && j >= 0 && j < col) {  //범위 확인
					if(!visited[h][j]) {  //아무도 안죽임 = 내가 죽일거
						if(map[h][j] == 1) {
							map[h][j] = 0;
							visited[h][j] = true;
							return 1;
						}
					}
					else {  //앞에서 누가 죽임 = 같은 애 죽임
						if(map[h][j] == 0) {
							return 0;
						}
					}
				}
				//열에 따른 행의 값 변환
				if (j < c) {
					h--;
				} else {
					h++;
				}
			}
		}
		return 0;
	}

	private static boolean np(int flag[]) {
		int i = flag.length - 1;
		while (i > 0 && flag[i - 1] >= flag[i])
			i--;

		if (i == 0)
			return false;

		int j = flag.length - 1;
		while (flag[i - 1] >= flag[j])
			j--;

		swap(flag, i - 1, j);

		j = flag.length - 1;
		while (i < j)
			swap(flag, i++, j--);

		return true;
	}

	private static void swap(int[] flag, int i, int j) {
		int temp = flag[i];
		flag[i] = flag[j];
		flag[j] = temp;
	}

}
