package ect;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 단식원 {
	
	static int N, M, arr[][], answer;
	static int dx[] = {0,0,1,-1};
	static int dy[] = {1,-1,0,0};
	static LinkedList<int []> chickens;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		int T = Integer.parseInt(in.readLine());
		
		for(int t=1;t<=T;t++) {
			sb.append("#").append(t).append(" ");
			
			st = new StringTokenizer(in.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			arr = new int[N][M];
			chickens = new LinkedList<>();
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(in.readLine(), " ");
				for(int j=0;j<M;j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
					
					if(arr[i][j] == 2)
						chickens.add(new int[] {i, j});
				}
			}
			
			answer = 0;
			
			// 조합 - 3개 선택
			comb(0, 0, new int[3]);
			
			sb.append(answer).append("\n");			
		}
		
		System.out.println(sb.toString());
	}

	private static void comb(int selectTo, int start, int[] selected) {
		if(selectTo == 3) {
			// arr 복사
			int arr_copy[][] = new int[N][M];
			for(int i=0;i<N;i++)
				System.arraycopy(arr[i], 0, arr_copy[i], 0, M);
			
			// 탈취제 셋팅 : 탈취제를 놓을 수 있는지 여부 리턴
			boolean isPossible = setFebreeze(arr_copy, selected);
			if(!isPossible)
				return;
			
			// 치킨 냄새 퍼뜨리기
			spreadChicken(arr_copy);
			
			// 날씬이존 구하기
			getSlimZone(arr_copy);
			
			return;
		}
		
		for(int i=start;i<N*M;i++) {
			selected[selectTo] = i;
			comb(selectTo+1, i+1, selected);
		}
		
	}

	private static void getSlimZone(int[][] arr_copy) {
		int ans = 0;
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(arr_copy[i][j] == 0)
					ans++;
			}
		}
		
		answer = Math.max(answer, ans);
	}

	private static void spreadChicken(int[][] arr_copy) {
		Queue<int []> queue = new LinkedList<>(chickens);
		boolean visited[][] = new boolean[N][M];
		
		while(!queue.isEmpty()) {
			int r = queue.peek()[0];
			int c = queue.poll()[1];
			visited[r][c] = true;
			
			for(int d=0;d<4;d++) {
				int nr = r+dx[d];
				int nc = c+dy[d];
				
				if(nr>=0 && nr<N && nc>=0 && nc<M && !visited[nr][nc] && arr_copy[nr][nc] == 0) {
					arr_copy[nr][nc] = 2;
					queue.add(new int[] {nr, nc});
				}
			}
		}
		
	}

	private static boolean setFebreeze(int arr_copy[][], int[] selected) {
		for(int i=0;i<3;i++) {
			int num = selected[i];
			int r = num/M;
			int c = num%M;
			
			if(arr_copy[r][c] == 0)
				arr_copy[r][c] = 1;
			else
				return false;	
		}
		return true;
	}

}
