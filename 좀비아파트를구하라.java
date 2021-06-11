package ect;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 좀비아파트를구하라 {
	
	static int N, M, H, apart[][][], answer;
	static LinkedList<Vaccine> vaccine;
	static int dx[] = {0,0,1,-1,0,0};
	static int dy[] = {1,-1,0,0,0,0};
	static int dh[] = {0,0,0,0,1,-1};
	
	static class Vaccine{
		int r, c, h, cnt;

		public Vaccine(int r, int c, int h, int cnt) {
			super();
			this.r = r;
			this.c = c;
			this.h = h;
			this.cnt = cnt;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		int T = Integer.parseInt(in.readLine());
		
		for(int t=1;t<=T;t++) {
			sb.append("#").append(t).append(" ");
			
			st = new StringTokenizer(in.readLine(), " ");
			M = Integer.parseInt(st.nextToken()); // 세로
			N = Integer.parseInt(st.nextToken()); // 가로
			H = Integer.parseInt(st.nextToken()); // 높이
			
			apart = new int[H][N][M];
			vaccine = new LinkedList<>();
			boolean hasZombies = false;
			for(int i=0;i<H;i++) {
				for(int j=0;j<N;j++) {
					st = new StringTokenizer(in.readLine(), " ");
					for(int k=0;k<M;k++) {
						apart[i][j][k] = Integer.parseInt(st.nextToken());
						
						if(apart[i][j][k] == 1)
							vaccine.add(new Vaccine(j,k,i,0));
						
						if(apart[i][j][k] == -1)
							hasZombies = true;
					}
				}
			}
			
			// 백신 있는 사람만 존재하는 지 확인
			if(!hasZombies) {
				sb.append("ALL HUMANS\n");
				continue;
			}
			// 좀비가 존재
			else {
				if(vaccine.size() == 0) {
					sb.append("STILL ZOMBIES\n");
					continue;
				}
			}
			
			answer = 0;
			
			// 백신 퍼뜨리기
			spreadVaccine();
			
			// 좀비가 남아있는 지 확인
			boolean hasZombie = checkZombies();
			if(hasZombie)
				sb.append("STILL ZOMBIES\n");
			else
				sb.append(answer).append("\n");
			
		}
		
		System.out.println(sb.toString());
	}

	private static boolean checkZombies() {
		for(int i=0;i<H;i++) {
			for(int j=0;j<N;j++) {
				for(int k=0;k<M;k++) {
					if(apart[i][j][k] == -1)
						return true;
				}
			}
		}
		
		return false;
	}

	private static void spreadVaccine() {
		Queue<Vaccine> queue = new LinkedList<>(vaccine);
		
		while(!queue.isEmpty()) {
			Vaccine vac = queue.poll();
			
			for(int d=0;d<6;d++) {
				int nr = vac.r + dx[d];
				int nc = vac.c + dy[d];
				int nh = vac.h + dh[d];
				
//				System.out.println(nr+"/"+nc+"/"+nh);
				
				if(nr >= 0 && nr < N && nc>=0 && nc < M && nh >= 0 && nh < H && apart[nh][nr][nc] == -1) {
					apart[nh][nr][nc] = 1;
					queue.add(new Vaccine(nr, nc, nh, vac.cnt+1));
				}
			}
			
			answer = vac.cnt;
		}
	}

}









