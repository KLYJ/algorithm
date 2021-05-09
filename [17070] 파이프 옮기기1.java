package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//방법1 : DFS
public class BJ_17070 {

	static int N, answer = 0;
	static int map[][];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		
		map = new int[N+1][N+1];  // [0,0]~[N-1,N-1]에 입력값 저장, 외곽은 1로 저장
		for(int i=0;i<N;i++) {
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			for(int j=0;j<N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken()); 
			}
			map[i][N] = 1;
		}
		for(int i=0;i<N+1;i++) {
			map[N][i] = 1;
		}
		
		DFS(0,1,0);
		
		System.out.println(answer);
	}

	private static void DFS(int row, int col, int dir) {
		if(row==N-1 && col==N-1) {
			answer++;
			return;
		}
		
		if(dir==0) { //가로 방향
			if(isRight(row, col)) {
				DFS(row, col+1, 0);
			}
			if(isRightDown(row, col)) {
				DFS(row+1, col+1, 2);
			}
		}
		else if(dir==1) { //세로 방향
			if(isDown(row, col)) {
				DFS(row+1, col, 1);
			}
			if(isRightDown(row, col)) {
				DFS(row+1, col+1, 2);
			}
		}
		else {  //오른쪽대각아래 방향
			if(isRight(row, col)) {
				DFS(row, col+1, 0);
			}
			if(isDown(row, col)) {
				DFS(row+1, col, 1);
			}
			if(isRightDown(row, col)) {
				DFS(row+1, col+1, 2);
			}
		}
		
	}

	private static boolean isDown(int row, int col) {
		if(map[row+1][col] == 0)
			return true;
		return false;
	}

	private static boolean isRightDown(int row, int col) { //3영역 검사
		if(map[row+1][col+1]==0)
			return true&&isDown(row,col)&&isRight(row, col);
		return false;
	}

	private static boolean isRight(int row, int col) {
		if(map[row][col+1]==0)
			return true;
		return false;
	}

}

//방법2 : DP
public class BJ_17070_2 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());

		int map[][] = new int[N + 2][N + 2]; // [1,1]~[N,N]에 입력값 저장, 외곽은 1로 저장
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
			map[i][0] = 1;
			map[i][N + 1] = 1;
		}
		for (int i = 0; i <= N + 1; i++) {
			map[N+1][i] = 1;
			map[0][i] = 1;
		}

		// DP에 현 위치까지 놓을 수 있는 파이프 개수 저장 
		int dp[][][] = new int[N + 2][N + 2][3]; // 어떤 방향으로부터 파이프가 몇 개 놓아졌다
		dp[1][2][0] = 1; // 초기 파이프 놓은 자리, 방향에 1개 저장

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				// 가로 방향
				if (dp[i][j - 1][0] > 0 && map[i][j] == 0) { // 가로
					dp[i][j][0] += dp[i][j - 1][0];
				}
				if (dp[i - 1][j - 1][0] > 0 && map[i][j] == 0 && map[i - 1][j] == 0 && map[i][j - 1] == 0) { // 대각
					dp[i][j][2] += dp[i - 1][j - 1][0];
				}

				// 세로 방향
				if (dp[i - 1][j][1] > 0 && map[i][j] == 0) { // 세로
					dp[i][j][1] += dp[i - 1][j][1];
				}
				if (dp[i - 1][j - 1][1] > 0 && map[i][j] == 0 && map[i - 1][j] == 0 && map[i][j - 1] == 0) { // 대각
					dp[i][j][2] += dp[i - 1][j - 1][1];
				}

				// 대각 방향
				if (dp[i][j - 1][2] > 0 && map[i][j] == 0) { // 가로
					dp[i][j][0] += dp[i][j - 1][2];
				}
				if (dp[i - 1][j][2] > 0 && map[i][j] == 0) { // 세로
					dp[i][j][1] += dp[i - 1][j][2];
				}
				if (dp[i - 1][j - 1][2] > 0 && map[i][j] == 0 && map[i - 1][j] == 0 && map[i][j - 1] == 0) { // 대각
					dp[i][j][2] += dp[i - 1][j - 1][2];
				}
			}
		}

		System.out.println(dp[N][N][0] + dp[N][N][1] + dp[N][N][2]);
	}

}

