package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_2669 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int map[][] = new int[100][100];
		int cnt = 0;
		
		for(int f=0;f<4;f++) {
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			for(int i=x;i<a;i++) {
				for(int j=y;j<b;j++) {
					if(map[i][j] != 1) {
						cnt++;
						map[i][j] = 1;
					}
				}
			}
			
		}
		
		System.out.println(cnt);
		
	}

}
