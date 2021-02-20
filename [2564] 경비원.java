package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//방법1 : 노가다
public class BJ_2564 {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		int garo = Integer.parseInt(st.nextToken());
		int sero = Integer.parseInt(st.nextToken());
		int gs = garo + sero;

		int store_cnt = Integer.parseInt(in.readLine());

		int location[][] = new int[store_cnt + 1][3];
		for (int i = 0; i < store_cnt + 1; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int dir = Integer.parseInt(st.nextToken());
			int dis = Integer.parseInt(st.nextToken());
			switch (dir) {
			case 1:
				location[i][0] = 0;
				location[i][1] = dis;
				location[i][2] = dir;
				break;
			case 2:
				location[i][0] = sero;
				location[i][1] = dis;
				location[i][2] = dir;
				break;
			case 3:
				location[i][0] = dis;
				location[i][1] = 0;
				location[i][2] = dir;
				break;
			case 4:
				location[i][0] = dis;
				location[i][1] = garo;
				location[i][2] = dir;
				break;
			}
		}

		int answer = 0;
		int dong_x = location[store_cnt][0];
		int dong_y = location[store_cnt][1];
		int dong_d = location[store_cnt][2];
		for (int i = 0; i < store_cnt; i++) {
			int x = location[i][0];
			int y = location[i][1];
			int d = location[i][2];
			if(dong_d == d) {  //같은 방향
				answer += Math.abs(dong_x-x)+Math.abs(dong_y-y);
			}
			else if(dong_d<3 && d<3) { //다른 방향인데 북-남 / 남-북
				answer += sero + Math.min(2*garo-(dong_y+y), dong_y+y);
			}
			else if(dong_d>2 && d>2) { //다른 방향인데 동-서 / 서-동
				answer += garo + Math.min(2*sero-(dong_x+x), dong_x+x);
			}
			else { // 북서/북동/남서/남동 의 경우
				answer += Math.abs(dong_x-x)+Math.abs(dong_y-y);
			}
		}

		System.out.println(answer);
	}

}

//방법2 : 케이스 워크! 싸이클!!
public class BJ_2564_2 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		int garo = Integer.parseInt(st.nextToken());
		int sero = Integer.parseInt(st.nextToken());
		int gs = garo+sero;
				
		int store_cnt = Integer.parseInt(in.readLine());
		int idx[] = new int[store_cnt+1];		
		for (int i = 0; i < store_cnt + 1; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int dir = Integer.parseInt(st.nextToken());
			int dis = Integer.parseInt(st.nextToken());
			switch (dir) {
			case 1: //북쪽
				idx[i] = dis;
				break;
			case 2: //남쪽
				idx[i] = garo*2+sero-dis;
				break;
			case 3: //서쪽
				idx[i] = garo*2+sero*2-dis;
				break;
			case 4: //동쪽
				idx[i] = garo+dis;
				break;
			}
		}
		
		int dong = idx[store_cnt];
		int answer = 0;
		for(int i=0;i<store_cnt;i++) {
			int dis = Math.abs(dong-idx[i]);
			answer += dis>gs?gs*2-dis:dis;
		}
		System.out.println(answer);
	}
}
