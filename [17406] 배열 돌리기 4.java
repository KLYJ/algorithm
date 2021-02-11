import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sol_0210_BJ17406 {

	static int rotate_cnt;
	static int rotate_order[][];
	static int order_cnt = 0;
	static int min = Integer.MAX_VALUE;
	static int queue_num;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] info = in.readLine().split(" ");
		int row = Integer.parseInt(info[0]);
		int col = Integer.parseInt(info[1]);
		rotate_cnt = Integer.parseInt(info[2]);

		// 배열 입력 받기
		int arr[][] = new int[row][col];
		for (int i = 0; i < row; i++) {
			String line[] = in.readLine().split(" ");
			for (int j = 0; j < col; j++) {
				arr[i][j] = Integer.parseInt(line[j]);
			}
		}

		// 회전 연산 입력 받기
		int order = 1; // 순열의 길이(총 회전 순서)
		int t = 2;
		while (t <= rotate_cnt) {
			order *= t++;
		}
		int[][] rotate = new int[rotate_cnt][4];    // 왼쪽 위 x, 왼쪽 위 y, 오른쪽 아래 x, 오른쪽 위y 저장
		rotate_order = new int[order][rotate_cnt];  // 순서 전달
		for (int i = 0; i < rotate_cnt; i++) {
			String line[] = in.readLine().split(" ");
			int r = Integer.parseInt(line[0]);
			int c = Integer.parseInt(line[1]);
			int s = Integer.parseInt(line[2]);
			rotate[i][0] = r - s - 1;
			rotate[i][1] = c - s - 1;
			rotate[i][2] = r + s - 1;
			rotate[i][3] = c + s - 1;
		}

		// 회전 순서 순열 : rotate_order에 저장
		int index[] = new int[rotate_cnt];
		for (int i = 0; i < rotate_cnt; i++) {
			index[i] = i;
		}
		perm(0, new int[rotate_cnt], new boolean[rotate_cnt], index);

		// 회전 실행
		// arr을 계속 매개변수로 주면 다음 회전에 그대로 회전된 arr의 값이 사용됨!!!!
		int copy_arr[][] = new int[row][col]; // 그래서 copy_arr에 arr을 깊은 복사해야함!!
		for (int i = 0; i < row; i++) {
			System.arraycopy(arr[i], 0, copy_arr[i], 0, col); // 2차원 배열은 Arrays.clone()으로 깊은 복사 불가능!! System.arraycopy로
																// 한 행씩 옮기기!
		}
		for (int i = 0; i < order; i++) {
			for (int j = 0; j < rotate_cnt; j++) {
				int start_x = rotate[rotate_order[i][j]][0];
				int start_y = rotate[rotate_order[i][j]][1];
				int end_x = rotate[rotate_order[i][j]][2];
				int end_y = rotate[rotate_order[i][j]][3];
				queue_num = (end_x - start_x + 1) % 2 == 0 ? (end_x - start_x + 1) / 2 : (end_x - start_x + 1) / 2 + 1;
				dfs(0, copy_arr, start_x, start_y, end_x, end_y);
			}
			// 회전 후 a값 업데이트
			for (int k = 0; k < row; k++) {
				int a = 0;
				for (int j = 0; j < col; j++) {
					a += copy_arr[k][j];
				}
				min = Math.min(a, min);
			}
			//깊은복사
			for (int j = 0; j < row; j++) {
				System.arraycopy(arr[j], 0, copy_arr[j], 0, col); // 2차원 배열은 Arrays.clone()으로 깊은 복사 불가능!! System.arraycopy로
																	// 한 행씩 옮기기!
			}
		}

		// 출력
		System.out.println(min);

	}
	
	//재귀
	static int dx[] = { 0,1,0,-1 };
	static int dy[] = { 1,0,-1,0 };	
	private static void dfs(int cnt, int arr[][], int start_x, int start_y, int end_x, int end_y) {
		if(cnt==queue_num || start_x==end_x) {
			return;
		}
		int x = start_x;
		int y = start_y;
		int temp1 = 0;
		for(int i=0;i<4;i++) {
			if(i==0)
				temp1 = arr[x][y];
			int temp2;
			x += dx[i];
			y += dy[i];
			while (x >= start_x && x <= end_x && y >= start_y && y <= end_y) {
				temp2 = arr[x][y];
				arr[x][y] = temp1;
				temp1 = temp2;
				x += dx[i];
				y += dy[i];
			}
			x -= dx[i];
			y -= dy[i];
		}
		
		dfs(cnt+1, arr, start_x+1, start_y+1, end_x-1, end_y-1);
		
	}

	private static void perm(int selectTo, int selected[], boolean visited[], int index[]) {
		if (selectTo == rotate_cnt) {
			for (int i = 0; i < rotate_cnt; i++) {
				rotate_order[order_cnt][i] = selected[i];
			}
			order_cnt++;
			return;
		}

		for (int i = 0; i < rotate_cnt; i++) {
			if (!visited[i]) {
				selected[selectTo] = index[i];
				visited[i] = true;
				perm(selectTo + 1, selected, visited, index);
				visited[i] = false;
			}
		}
	}

}
