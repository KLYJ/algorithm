import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 라인 별로 주석 달기!!

public class Algo2_서울_7_이유진 {
	
	static int R, C, map[][], answer;  // 가로, 세로, 지형 배열, 정답
	static int dx[] = {0,0,1,-1};  // 행 방향 전환
	static int dy[] = {1,-1,0,0};  // 열 방향 전환

	public static void main(String[] args) throws NumberFormatException, IOException {
		// 입력
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int TC = Integer.parseInt(in.readLine()); // 테스트 케이스 입력 받기
		
		// 테스트 케이스만큼 반복
		for(int t=1;t<=TC;t++) {
			sb.append("#").append(t).append(" ");  // 출력 형식
			
			// 가로, 세로 입력 받기
			st = new StringTokenizer(in.readLine(), " ");
			R = Integer.parseInt(st.nextToken());  // 가로
			C = Integer.parseInt(st.nextToken());  // 세로
			
			// 지형 입력 받기
			map = new int[R][C];  // 지형의 높이 저장
			for(int i=0;i<R;i++) {
				st = new StringTokenizer(in.readLine());
				for(int j=0;j<C;j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			answer = 0;  // 정답 0으로 초기화
			boolean visited[][] = new boolean[R][C]; // 방문 했는지 여부 확인 배열
			
			// DFS 호출
			dfs(0, 0 ,visited);
			
			sb.append(answer).append("\n"); // 출력 형식대로 정답 저장
		}
		
		System.out.println(sb.toString()); // 정답 출력
	}

	private static void dfs(int r, int c, boolean visited[][]) {
		// 기저 조건
		if(r == R-1 && c == C-1) {
			answer++;  // 정답 하나 추가
			return;
		}
		
		// 4방 탐색
		for(int d=0;d<4;d++) {
			int nr = r+dx[d]; // 다음 칸의 행
			int nc = c+dy[d]; // 다음 칸의 열
			// '다음 칸이 map의 범위 내에 있으며 && 아직 방문하지 않았고 && 내리막 길'이라면
			if(nr>=0 && nr<R && nc>=0 && nc<C && !visited[nr][nc] && map[r][c] > map[nr][nc]) {
				visited[r][c] = true;  // 현재 칸 방문 체크
				dfs(nr, nc, visited);  // 다음 칸 확인(재귀)
				visited[r][c] = false; // 현재 칸 다시 미방문 상태로 복귀
			}
		}
		
	}
}