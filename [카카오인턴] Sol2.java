package kakao_intern_21;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Sol2 {
	
	static int dx[] = {0,0,-1,1};
	static int dy[] = {-1,1,0,0};
	
	static class Seat{
		int r, c, dis;

		public Seat(int r, int c, int dis) {
			super();
			this.r = r;
			this.c = c;
			this.dis = dis;
		}
	}
	
	public static void main(String[] args) {
		System.out.println(Arrays.toString(solution(new String [][] {{"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"}, {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"}, {"PXOPX", "OXOXP", "OXPXX", "OXXXP", "POOXX"}, {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"}, {"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}})));
	}
	
	public static int[] solution(String[][] places) {
		int[] answer = new int[5];
		
		for(int t=0;t<5;t++) {
			int p_cnt = 0;
			int p_ok = 0;
			boolean stop = false;
			
			for(int i=0;i<5;i++) {
				for(int j=0;j<5;j++) {
					if(places[t][i].charAt(j) == 'P') {
						p_cnt++;
						
						if(!BFS(places[t], i, j)) {
							i=5;
							stop = true;
							break;
						}
						else {
							p_ok++;
						}
					}
				}
			}
			
			if(stop)
				continue;
			
			if(p_cnt == p_ok)
				answer[t] = 1;
		}
		
        return answer;
    }

	private static boolean BFS(String[] place, int i, int j) {
		boolean visited[][] = new boolean[5][5];
		
		Queue<Seat> queue = new LinkedList<>();
		queue.add(new Seat(i, j, 0));
		
		while(!queue.isEmpty()) {
			int r = queue.peek().r;
			int c = queue.peek().c;
			int dis = queue.poll().dis;
			
			visited[r][c] = true;
			
			for(int d=0;d<4;d++) {
				int nr = r+dx[d];
				int nc = c+dy[d];
				
				if(nr>=0 && nr<5 && nc>=0 && nc< 5 && !visited[nr][nc]) {
					if(place[nr].charAt(nc) == 'P')
						return false;
					else if(place[nr].charAt(nc) == 'O') {
						if(dis == 0) {
							// queue에 넣기
							queue.add(new Seat(nr, nc, dis+1));
						}
					}
				}
			}
			
		}
		
		return true;
	}

}














