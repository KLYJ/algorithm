package com.ssafy.algo.sp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class JO_1733_2 {

	static char map[][];

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		map = new char[21][21];
		for (int i = 1; i < 20; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			for(int j=1;j<20;j++) {
				map[i][j] = st.nextToken().charAt(0);
			}
		}

		// 검색
		int flag  = 0;
		for (int i = 1; i < 20; i++) {
			for (int j = 1; j < 20; j++) {
				flag = 0;
				if (map[i][j] == '1') { // 검정색
					flag = search(i, j, '1');
				} else if (map[i][j] == '2') {
					flag = search(i, j, '2');
				}
				if(flag == 1) {
					i = 19;
					break;
				}
			}
		}
		if(flag == 0)
			System.out.println(0);

	}

	static int dx[] = {-1, 0,1,1};
	static int dy[] = {1, 1, 1,0};
	
	private static int search(int r, int c, char color) {
		for(int i=0;i<4;i++) {
			if(map[r-dx[i]][c-dy[i]]==color)
				continue;
			int nr = r+ dx[i];
			int nc = c+ dy[i];
			int cnt = 1;
			while(map[nr][nc]==color) {
				nr += dx[i];
				nc += dy[i];
				cnt++;
			}
			if(cnt == 5) {
				System.out.println(color);
				System.out.println(r+" "+c);
				return 1;
			}
		}
		
		return 0;
	}

}
