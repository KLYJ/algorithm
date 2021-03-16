package SWEA;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.IOException;

public class SWEA_5644 {

	static int map[][][];
	static int dx[] = { 0, -1, 0, 1, 0 };
	static int dy[] = { 0, 0, 1, 0, -1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int T = Integer.parseInt(in.readLine());

		for (int t = 1; t <= T; t++) {
			sb.append("#").append(t).append(" ");

			st = new StringTokenizer(in.readLine(), " ");
			int time = Integer.parseInt(st.nextToken());
			int ap_cnt = Integer.parseInt(st.nextToken());

			int A[] = { 0, 0 };
			int B[] = { 9, 9 };
			int A_dir[] = new int[time+1];
			int B_dir[] = new int[time+1];
			st = new StringTokenizer(in.readLine(), " ");
			for (int i = 1; i <= time; i++) {
				A_dir[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(in.readLine(), " ");
			for (int i = 1; i <= time; i++) {
				B_dir[i] = Integer.parseInt(st.nextToken());
			}

			map = new int[10][10][ap_cnt]; // map의 위치에 놓여진 ap의 처리량 저장
			int ap[][] = new int[ap_cnt][4];
			for (int i = 0; i < ap_cnt; i++) {
				st = new StringTokenizer(in.readLine(), " ");
				int c = Integer.parseInt(st.nextToken()) - 1;
				int r = Integer.parseInt(st.nextToken()) - 1;
				int dis = Integer.parseInt(st.nextToken());
				int power = Integer.parseInt(st.nextToken());
				ap[i] = new int[] {r, c, dis, power};
			}
			
			//power대로 ap 내림차순
			Arrays.sort(ap, new Comparator<int[]>() {
				@Override
				public int compare(int[] o1, int[] o2) {
					return o2[3]-o1[3];
				}
			});
			
			//map마다 내림차순으로 power 저장
			for(int i=0;i<ap_cnt;i++) {
				ap_set(i, ap[i][0], ap[i][1], ap[i][2], ap[i][3]);
			}
			
			//A, B 이동하면서 충전
			int answer = 0;
			for(int k=0;k<=time;k++) {
				//A, B 이동
				A[0] += dx[A_dir[k]];
				A[1] += dy[A_dir[k]];
				B[0] += dx[B_dir[k]];
				B[1] += dy[B_dir[k]];
				
				//A와 B가 속해있는 ap의 power 최대 2개 저장
				int A_ap[][] = new int[2][2];
				int B_ap[][] = new int[2][2];
				int A_idx = 0; int B_idx = 0;
				for(int i=0;i<ap_cnt;i++) {
					if(A_idx < 2 && A_ap[A_idx][0]==0 && map[A[0]][A[1]][i] != 0) {
						A_ap[A_idx][0] = map[A[0]][A[1]][i];
						A_ap[A_idx++][1] = i;
					}
					if(B_idx < 2 && B_ap[B_idx][0]==0 && map[B[0]][B[1]][i] != 0) {
						B_ap[B_idx][0] = map[B[0]][B[1]][i];
						B_ap[B_idx++][1] = i;
						
					}
				}
				
				//충전되는 경우의 수
				if(A_ap[0][0] == B_ap[0][0]) { //최대 충전 power 같음
					if(A_ap[0][1] == B_ap[0][1]) {//같은 ap내
						answer += A_ap[0][0]+Math.max(A_ap[1][0], B_ap[1][0]);
					}
					else {  //다른 ap 내
						answer += 2*A_ap[0][0];
					}
				}
				else {
					if(A_ap[0][0] == 0) { //A는 ap 없음
						answer += B_ap[0][0];
					}
					else if(B_ap[0][0] == 0) { //B는 ap 없음
						answer += A_ap[0][0];
					}
					else {
						answer += A_ap[0][0]+B_ap[0][0];
					}
				}
			}
			sb.append(answer).append("\n");
		}
		System.out.println(sb.toString());
	}

	
	private static void ap_set(int idx, int r, int c, int dis, int power) {
		for(int i=r-dis, j=0;i<=r+dis;i++) {
			for(int k=c-j;k<=c+j;k++) {
				if(i>-1 && k>-1 && i<10 && k<10) {
					map[i][k][idx] = power;
				}
			}
			
			if(i<r) j++;
			else j--;
		}
	}
  
}
