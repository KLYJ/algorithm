import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Sol_0225_SWEA1767_2 {
	
	static int N,max,totalCnt,min,map[][];
	static int dr[] = {-1,1,0,0};
	static int dc[] = {0,0,-1,1};
	static ArrayList<int[]> list;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int TC = Integer.parseInt(in.readLine());
		
		for(int tc=1;tc<=TC;tc++) {
			N = Integer.parseInt(in.readLine());
			map = new int[N][N];
			list = new ArrayList<int[]>();
			max = 0;
			min = Integer.MAX_VALUE;
			totalCnt = 0;
			
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(in.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] == 1 && i != 0 && j != 0 && i != N - 1 && j != N - 1) {
						list.add(new int[] { i, j });
						totalCnt++;
					}
				}
			}
			
			go(0,0);
			System.out.println("#"+tc+" "+min);
			
		}
	}
	
	private static void go(int index, int cCnt) { //index:부분집합의 코어의 인덱스, cCnt:연결된 코어 개수
		//기저조건
		if(index == totalCnt) {
			int res = getLength(); //놓아진 전선 길이 구하기
			
			if(max<cCnt) {
				max = cCnt;
				min = res;
			} else if(max==cCnt) {
				if(res<min)
					min = res;
			}
			return;
		}
		
		//코어 선택 전선 놓아보기(4방향 놓아보기)
		int[] cur = list.get(index);
		int r = cur[0];
		int c = cur[1];
		for(int d=0;d<4;d++) {
			if(isAvailable(r,c,d)) {
				//전선 놓기
				setStatus(r, c, d, 2);
				//다음 코어로 넘어가기
				go(index+1, cCnt+1);
				//놓았던 전선 되돌려 놓기
				setStatus(r, c, d, 0);
			}
		}
		//코어 비선택
		go(index+1, cCnt);
		
	}
	
	private static int getLength() {
		int lCnt = 0;
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(map[i][j]==2)
					lCnt++;
			}
		}
		
		return lCnt;
	}

	private static void setStatus(int r, int c, int d, int s) {
		int nr=r, nc=c;
		while(true) {
			nr += dr[r];
			nc += dc[d];
			if(nr<0 || nr>=N || nc<0 || nc>=N) //경계 벗어나면 break
				break;
			map[nr][nc] = s;
		}
	}
	
	private static boolean isAvailable(int r, int c, int d) {
		int nr = r, nc = c;
		while(true) {
			nr += dr[r];
			nc += dc[d];
			if(nr<0 || nr>=N || nc<0 || nc>=N) //경계 벗어나면 break
				break;
			if(map[nr][nc]>=1) //코어나 전선이 있어서 불가능
				return false;
		}
		return true;
	}
	
	

}
