import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_16935 {

	static int row;
	static int col;
	static int max;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] info = in.readLine().split(" ");
		row = Integer.parseInt(info[0]);
		col = Integer.parseInt(info[1]);
		int cnt = Integer.parseInt(info[2]);

		// 배열 입력 받기
		max = Math.max(row, col);
		int arr[][] = new int[max][max];
		int copy_arr[][] = new int[max][max];
		for (int i = 0; i < max; i++) {
			if (i < row) {
				String line[] = in.readLine().split(" ");
				for (int j = 0; j < max; j++) {
					if (j < col)
						arr[i][j] = Integer.parseInt(line[j]);
					else
						arr[i][j] = 0;
				}
			} else {
				for (int j = 0; j < max; j++) {
					arr[i][j] = 0;
				}
			}
		}

		// section 돌리기 위해서 인덱스 저장
//		int idx[][] = { { 0, 0 }, { 0, col / 2 }, { row / 2, col / 2 }, { row / 2, 0 } };
		
		// section 돌리기 위한 델타
		int rx[] = {1,0,0,-1};
		int ry[] = {0,1,-1,0};
		
		int lx[] = {0,-1,1,0};
		int ly[] = {1,0,0,-1};

		// 연산 번호 입력
		String todo[] = in.readLine().split(" ");
		for (int i = 0; i < cnt; i++) {
			char c = todo[i].charAt(0);
			switch (c) {
			case '1':
				ud_reverse(arr);
				break;
			case '2':
				rl_reverse(arr);
				break;
			case '3':
				r_rotate(arr, copy_arr);
				break;
			case '4':
				for(int j=0;j<3;j++)
					r_rotate(arr, copy_arr);
				break;
			case '5':
				section_r_rotate(arr, rx, ry, copy_arr);
				break;
			case '6':
//				for(int j=0;j<3;j++)
				section_r_rotate(arr, rx, ry, copy_arr);
				break;
			default:
				break;
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				sb.append(arr[i][j]).append(" ");
			}
			sb.append("\n");
		}

		System.out.println(sb.toString());
	}

	private static void section_r_rotate(int[][] arr, int[] dx, int[] dy, int[][] copy_arr) {
//		int copy_arr[][] = new int[max][max];
		
		//이 코드로 하면 왜 틀렸습니다가 나오는지 이해가 안간다...
//		for (int i = 0; i < 4; i++) {
//			int x = idx[(3 + i) % 4][0];  //3 0 1 2
//			int y = idx[(3 + i) % 4][1];
//			for (int m = 0; m < row / 2; m++) {
//				for (int n = 0; n < col / 2; n++) {
//					copy_arr[idx[i][0] + m][idx[i][1] + n] = arr[x + m][y + n];
//				}
//			}
//		}
		
		//(0,0)
		for(int i=0;i<row/2;i++) {
			for(int j=0;j<col/2;j++) {
				copy_arr[i][j] = arr[i+row/2*dx[0]][j+col/2*dy[0]]; //1,0 //0,1
			}
		}
		
		//(row/2, 0)
		for(int i=row/2;i<row;i++) {
			for(int j=0;j<col/2;j++) {
				copy_arr[i][j] = arr[i+row/2*dx[1]][j+col/2*dy[1]]; //0,1 //-1,0
			}
		}
		
		//(0,col/2)
		for(int i=0;i<row/2;i++) {
			for(int j=col/2;j<col;j++) {
				copy_arr[i][j] = arr[i+row/2*dx[2]][j+col/2*dy[2]]; //0,-1 //1,0
			}
		}
		
		//(row/2, col/2)
		for(int i=row/2;i<row;i++) {
			for(int j=col/2;j<col;j++) {
				copy_arr[i][j] = arr[i+row/2*dx[3]][j+col/2*dy[3]]; //-1,0 //0,-1
			}
		}

		for (int i = 0; i < max; i++) {
			System.arraycopy(copy_arr[i], 0, arr[i], 0, max);
		}
	}

	private static void r_rotate(int[][] arr, int[][] copy_arr) {
//		int copy_arr[][] = new int[max][max];

		for (int j = 0; j < col; j++) {
			for (int i = row - 1; i >= 0; i--)
				copy_arr[j][row - 1 - i] = arr[i][j];
		}

		int temp = col;
		col = row;
		row = temp;

		for (int i = 0; i < max; i++) {
			System.arraycopy(copy_arr[i], 0, arr[i], 0, max);
		}
	}

	private static void rl_reverse(int[][] arr) {
		for (int i = 0; i < col / 2; i++) {
			for (int j = 0; j < row; j++) {
				int temp = arr[j][i];
				arr[j][i] = arr[j][col - 1 - i];
				arr[j][col - 1 - i] = temp;
			}
		}
	}

	private static void ud_reverse(int[][] arr) {
		for (int i = 0; i < row / 2; i++) {
			for (int j = 0; j < col; j++) {
				int temp = arr[i][j];
				arr[i][j] = arr[row - i - 1][j];
				arr[row - i - 1][j] = temp;
			}
		}
	}
}
