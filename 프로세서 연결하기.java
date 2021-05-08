import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {
	
	static int N, maxCore, minLine;
	static int map[][];
	static ArrayList<int[]> one = new ArrayList<>();
	static int dx[] = {0,0,1,-1};
	static int dy[] = {1,-1,0,0};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		int T = Integer.parseInt(in.readLine());
		
		for(int t=1;t<=T;t++) {
			sb.append("#").append(t).append(" ");
			
			//초기화
			one.clear();
			
			//입력
			N = Integer.parseInt(in.readLine());
			map = new int[N][N];
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(in.readLine(), " ");
				for(int j=0;j<N;j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j]==1 && i!=0 && j!=0 && i!=N-1 && j!=N-1) {
						one.add(new int[] {i, j});
					}
				}
			}
			
			maxCore = 0;
			minLine = Integer.MAX_VALUE;
			//dfs 실시
			dfs(0, 0); //cnt(one의 인덱스), 
			
			sb.append(minLine).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static void dfs(int cnt, int core_cnt) {
		if(cnt==one.size()) { //모든 core 검사 완료
//			System.out.println("maxCore: "+maxCore);
//			System.out.print("코어: "+core_cnt+"개 /");
//			System.out.println(" 전선: "+lineSum());
			if(maxCore < core_cnt) {
				maxCore = core_cnt;
				minLine = lineSum();
			}
			else if(maxCore == core_cnt) {
				minLine = Math.min(minLine, lineSum());
			}
			return;
		}
		
		int r = one.get(cnt)[0];
		int c = one.get(cnt)[1];
		for(int i=0;i<4;i++) {
			if(checkLine(r+dx[i], c+dy[i], i)) { //전선을 놓을 수 있다면
				setLine(r+dx[i], c+dy[i], i, 2); //2로 전선을 놓아라(마지막 값:어떤 것으로 놓을 지)
				dfs(cnt+1, core_cnt+1);
				setLine(r+dx[i], c+dy[i], i, 0); //전선 다시 초기화
			}
			else { //전선 못놓아
				dfs(cnt+1, core_cnt);
			}
		}
	}

	private static int lineSum() {
		int cnt = 0;
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(map[i][j] == 2)
					cnt++;
			}
		}
		return cnt;
	}

	//전선 놓기(2) or 0으로 초기화하기
	private static void setLine(int r, int c, int d, int set) {
		while(r>-1 && r<N && c>-1 && c<N) {
			map[r][c] = set;
			r += dx[d];
			c += dy[d];
		}
	}

	//전선 놓을 수 있는지 확인
	private static boolean checkLine(int r, int c, int d) {
		while(r>-1 && r<N && c>-1 && c<N) {
			if(map[r][c] != 0)
				return false;
			r += dx[d];
			c += dy[d];
		}
		
		return true;
	}
}
