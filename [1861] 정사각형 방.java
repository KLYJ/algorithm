import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sol_0205_SWEA1861 {

	// 방향 : 위 왼 아래 우
	static int dx[] = { -1, 0, 1, 0 };
	static int dy[] = { 0, -1, 0, 1 };
	static int N;
	static int map[][];
	static int visited[][];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());

		for (int i = 1; i <= T; i++) {
			N = Integer.parseInt(in.readLine());

			map = new int[N][N];
			visited = new int[N][N];
			for (int j = 0; j < N; j++) {
				String temp[] = in.readLine().split(" ");
				for (int k = 0; k < N; k++) {
					map[j][k] = Integer.parseInt(temp[k]);
					visited[j][k] = 0;
				}
			}

			//방법 1 : for문
			int max=0; int room = 0;
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < N; k++) { // map을 차례로 이동 : 현재 좌표 = [j, k]
					if(visited[j][k] ==0) {
						int cnt = 1;
						int room_num = map[j][k];
						int x = j;                //현재 좌표 = [x, y]
						int y = k;
						visited[x][y]++;
						for (int d = 0; d < 4; d++) { // 4방 검사
							int new_x = x + dx[d];
							int new_y = y + dy[d];
							if (new_x > -1 && new_x < N && new_y > -1 && new_y < N && (room_num + 1 == map[new_x][new_y])) { //4방 중 이동 가능하면 이동
								if(visited[new_x][new_y]>1) {
									visited[j][k] += visited[new_x][new_y];
									break;
								}
								x = new_x;
								y = new_y;
								visited[j][k]++;
								d=-1;
								room_num++;
								continue;
							}
						}
						
						room_num = map[j][k];
						cnt = visited[j][k];
						if(max<cnt) {
							max = cnt;
							room = room_num;
						}
						else if(max==cnt) {
							if(room_num<room) {
								room = room_num;
							}
						}
					}
				}
			}
			
			//방법 2 : DFS
			int max = 0; int room = Integer.MAX_VALUE;
			for(int j=0;j<N;j++) {
				for(int k=0;k<N;k++) {
					if(visited[j][k]==0)
						calc(j,k);

					if(visited[j][k]>max) {
						max = visited[j][k];
						room = map[j][k];
					} else if (visited[j][k]==max)
						room = room>map[j][k]? map[j][k]:room;
				}
			}
				
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(i).append(" ").append(room).append(" ").append(max);
			System.out.println(sb.toString());

		}
	}
	
	//방법 2 : DFS
	static private void calc(int x, int y) {
		visited[x][y] = 1;
		
		for(int i=0;i<4;i++) {
			int nx = x+dx[i];
			int ny = y+dy[i];
			
			if(nx<0||nx>=N||ny<0||ny>=N)
				continue;
			
			if(map[nx][ny] == map[x][y]+1) {
				if(visited[nx][ny]==0)
					calc(nx, ny);
				
				if(visited[nx][ny]+1 > visited[x][y]) {
					visited[x][y] = visited[nx][ny]+1;
				}
			}	
		}
	}

}
