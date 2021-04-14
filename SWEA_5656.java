package com.ssafy.algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Node {
	int r, c, dis;

	public Node(int r, int c, int dis) {
		super();
		this.r = r;
		this.c = c;
		this.dis = dis;
	}
	
}

public class SWEA_5656 {
	
	static int N, W, H, answer;
	static int[] dx = {0,0,1,-1};
	static int[] dy = {1,-1,0,0};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		int T = Integer.parseInt(in.readLine());
		
		for(int t=1;t<=T;t++) {
			sb.append("#").append(t).append(" ");
			st = new StringTokenizer(in.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			
			int map[][] = new int[H][W];
			for(int i=0;i<H;i++) {
				st = new StringTokenizer(in.readLine());
				for(int j=0;j<W;j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			answer = W*H;
			perm(0, new int[N], map);
			sb.append(answer).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static void perm(int selectTo, int[] selected, int map[][]) {
		if(selectTo==N) {
			// 벽돌 깨기 시작
			simul(0, selected, map);
			return;
		}
		
		for(int i=0;i<W;i++) {
			selected[selectTo] = i;
			perm(selectTo+1, selected, map);
		}
	}

	private static int cal(int[][] map) {
		int cnt = 0;
		for(int i=0;i<H;i++) {
			for(int j=0;j<W;j++) {
				if(map[i][j]!=0) {
					cnt++;
				}
			}
		}
		return cnt;
	}

	private static void simul(int cnt, int[] selected, int map[][]) {
		if(answer == 0)
			return;
		
		if(cnt == N) {
			answer = Math.min(answer, cal(map));
			return;
		}
		
		int copy_map[][] = new int[H][W];
		for(int i=0;i<H;i++) {
			System.arraycopy(map[i], 0, copy_map[i], 0, W);
		}
		
		if(!isEmpty(map, selected[cnt])) {
			// 구슬 던지기
			bomb(copy_map, selected[cnt]);
			simul(cnt+1, selected, copy_map);
		}
		else {
			simul(cnt+1, selected, map);
		}

	}
	
	private static void bomb(int[][] map, int idx) {
		Queue<Node> queue = new LinkedList<>();
		
		for(int i=0;i<H;i++) {
			if(map[i][idx] != 0) {
				queue.add(new Node(i, idx, map[i][idx]));
				break;
			}
		}
		
		// bfs
		while(!queue.isEmpty()) {
			int r = queue.peek().r;
			int c = queue.peek().c;
			int dis = queue.poll().dis;
			map[r][c] = 0;
			
			if(dis > 1) {
				for(int i=0;i<4;i++) {
					int nr = r+dx[i];
					int nc = c+dy[i];
					int cnt = dis;
					while(nr>=0 && nr<H && nc>=0 && nc<W && --cnt>0) {
						if(map[nr][nc]!=0) {
							queue.add(new Node(nr, nc, map[nr][nc]));
						}
						nr += dx[i];
						nc += dy[i];
					}
				}
			}
		}
		
		// 밑으로 내려주기
		for(int i=0;i<W;i++) {
			int zidx = -1;
			for(int j=H-1;j>=0;j--) {
				if(map[j][i] == 0 && zidx == -1) {
					zidx = j;
				}
				else if(map[j][i] != 0 && zidx != -1) {
					map[zidx][i] = map[j][i];
					map[j][i] = 0;
					zidx--;
				}
			}
		}
	}

	private static boolean isEmpty(int map[][], int idx) {
		for(int i=0;i<H;i++) {
			if(map[i][idx] != 0) {
				return false;
			}
		}
		return true;
	}

}