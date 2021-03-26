import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
	
	static int sero, garo, map[][], islandCnt = 0, parent[];
	static int dx[] = {0,0,1,-1};
	static int dy[] = {1,-1,0,0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
	
		sero = Integer.parseInt(st.nextToken());
		garo = Integer.parseInt(st.nextToken());
		map = new int[sero][garo];
		for(int i=0;i<sero;i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for(int j=0;j<garo;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 섬마다 좌표 저장
		boolean visited[][] = new boolean[sero][garo];
		for(int i=0;i<sero;i++) {
			for(int j=0;j<garo;j++) {
				if(map[i][j]==1 && !visited[i][j]) {
					islandCnt++;
					map[i][j] = islandCnt;
					distinctIsland(i, j, visited);
				}
			}
		}
		
		//섬 연결 하기 - 가중치 리스트에 저장
		ArrayList<int[]> list = new ArrayList<>(); //연결한 섬 번호 담기
		int dp[] = new int[garo];
		//가로
		for(int i=0;i<sero;i++) {
			Arrays.fill(dp, 0);
			int front = map[i][0];
			dp[0] = 1;
			for(int j=1;j<garo;j++) {
				if(map[i][j-1]==map[i][j]) {
					dp[j] = dp[j-1]+1;
				}
				else {
					if(map[i][j-1]==0 && map[i][j]!=0) {
						if(front != 0 && dp[j-1]>=2) //&& front != map[i][j])
							list.add(new int[] {front, map[i][j], dp[j-1]});
						front = map[i][j];
					}
					dp[j] = 1;
				}
			}
		}
		//세로
		dp = new int[sero];
		for(int i=0;i<garo;i++) {
			Arrays.fill(dp, 0);
			int front = map[0][i];
			dp[0] = 1;
			for(int j=1;j<sero;j++) {
				if(map[j-1][i]==map[j][i]) {
					dp[j] = dp[j-1]+1;
				}
				else {
					if(map[j-1][i]==0 && map[j][i]!=0) {
						if(front != 0 && dp[j-1]>=2)// && front != map[j][i])
							list.add(new int[] {front, map[j][i], dp[j-1]});
						front = map[j][i];
					}
					dp[j] = 1;
				}
			}
		}
		
		//크루스칼	- 가중치 기준으로 오름차순 정렬	
		Collections.sort(list, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[2]-o2[2];
			}
		});
		
		parent = new int[islandCnt+1];
		makeSet();
		
		int answer = 0;
		int cnt = 0;             // 연결한 섬의 개수
		boolean check = false;   // 모든 섬이 연결된 건가 확인
		for(int i=0;i<list.size();i++) {
			if(union(list.get(i)[0], list.get(i)[1])) { //사이클 발생 X
				answer += list.get(i)[2];
				if(++cnt==islandCnt-1) {
					check = true;  //모든 섬이 연결되었다면 true
				}
			}
		}
		
		System.out.println(check?answer:-1);
	
	}

	private static void makeSet() {
		for(int i=0;i<islandCnt;i++)
			parent[i] = i;
	}
	
	private static int find(int a) {
		if(parent[a] == a)
			return a;
		return parent[a] = find(parent[a]);
	}

	private static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if(aRoot == bRoot) {
			return false;
		}
		
		parent[bRoot] = aRoot;
		
		return true;
	}

	// BFS - map의 섬 번호 바꾸기
	private static void distinctIsland(int r, int c, boolean visited[][]) {
		visited[r][c] = true;
		
		for(int i=0;i<4;i++) {
			int nr = r+dx[i];
			int nc = c+dy[i];
			if(nr>=0 && nc>=0 && nr<sero && nc<garo && map[nr][nc] == 1 && !visited[nr][nc]) {
				map[nr][nc] = islandCnt;
				distinctIsland(nr, nc, visited);
			}
		}
		
	}

}
