import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int dx[] = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int dy[] = { 0, 1, 1, 1, 0, -1, -1, -1 };

	static class FireBall {
		int r, c, m, s, d, cnt;

		public FireBall(int r, int c, int m, int s, int d, int cnt) {
			super();
			this.r = r;
			this.c = c;
			this.m = m;
			this.s = s;
			this.d = d;
			this.cnt = cnt;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		int N = Integer.parseInt(st.nextToken()); // map 크기
		int M = Integer.parseInt(st.nextToken()); // 파이어볼 개수
		int K = Integer.parseInt(st.nextToken()); // 명령 개수

		Queue<FireBall> queue = new LinkedList<>(); // 파이어볼 모두 이동
		FireBall map[][] = new FireBall[N+1][N+1]; 		// 파이어볼 이동 후 저장
//		FireBall map[][] = new FireBall[N][N]; 		// 파이어볼 이동 후 저장
		

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			queue.offer(new FireBall(r, c, m, s, d, 1));
		}

		for (int k = 0; k < K; k++) {
			int size = queue.size();
			if(size == 0) break;
			
			// 파이어볼 이동
			for (int s = 0; s < size; s++) {
				FireBall fb = queue.poll();
				int r = fb.r+dx[fb.d]*fb.s;
				int c = fb.c+dy[fb.d]*fb.s;
				if(r>N)
					r %= N;
				if(r<1)
					r = N-Math.abs(r)%N;
				if(c>N)
					c %= N;
				if(c<1)
					c = N-Math.abs(c)%N;
				
				// 이 위치(r,c)에 fireball 없는 상태
				if(map[r][c] == null) { 
					fb.r = r;
					fb.c = c;
					map[r][c] = fb;
				}
				// 이 위치(r,c)에 fireball이 이미 있는 상태
				else {
					map[r][c].m += fb.m;
					map[r][c].s += fb.s;
					map[r][c].cnt++;
					if(map[r][c].d != -1) { // 방향이 계속 짝수거나 계속 홀수라면 방향 저장 / 다르면 -1 저장
						map[r][c].d = map[r][c].d%2==fb.d%2?map[r][c].d:-1;
					}
				}
			}
			
			// 충돌
			for(int i=0;i<N+1;i++) {
				for(int j=0;j<N+1;j++) {
					FireBall fb = map[i][j];
					// fireball이 없다면
					if(fb == null) 
						continue;
					// fireball이 하나라면
					else if(fb.cnt == 1) { 
						queue.offer(new FireBall(fb.r, fb.c, fb.m, fb.s, fb.d, 1));
					}
					// fireball이 여러 개라면 = 충돌
					else { 
						if(fb.m/5 != 0) {
							int d_start = 0;
							if(fb.d == -1)
								d_start = 1;
							
							for(int m=0;m<4;m++) {
								queue.offer(new FireBall(fb.r, fb.c, fb.m/5, fb.s/fb.cnt, d_start, 1));
								d_start += 2;
							}
						}
					}
					map[i][j] = null;
				}
			}
		}
		
		// 남은 fireball의 질량의 합
		int answer = 0;
		while(!queue.isEmpty()) {
			answer += queue.poll().m;
		}
		System.out.println(answer);
	}
}
