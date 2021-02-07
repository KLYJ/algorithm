import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SWEA_1954 {
	
	//방법1 : for문
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());
		
		//방향 : 아 왼 위 오
		int dx[] = {1,0,-1,0};
		int dy[] = {0,-1,0,1};
		
		for(int i=1;i<T+1;i++) {
			int N = Integer.parseInt(in.readLine());
			int arr[][] = new int[N][N]; //달팽이 배열
			int cnt=1; //배열에 들어갈 수
			for(int j=0;j<N;j++)
				arr[0][j] = cnt++;  //첫째줄 저장
			int now[] = new int[2]; 
			now[0] = 0; now[1] = N-1; //현재 arr 인덱스
			
			int dir = 0;
			for(int j=0;j<N-1;j++) {
				for(int k = 0;k<2;k++) {
					for(int m=0;m<N-1-j;m++) {
						now[0] += dx[dir]; now[1] += dy[dir];
						arr[now[0]][now[1]] = cnt++;
					}
					dir = (dir+1)%4;
				}
			}
			
			System.out.println("#"+i);
			for(int j=0;j<N;j++) {
				for(int k=0;k<N;k++) 
					System.out.print(arr[j][k]+" ");
				System.out.println();
			}
			
		}
		
	}

	
	
	
	//방법2 : 재귀
	//방향 : 아 왼 위 오
	static int dx[] = {1,0,-1,0};
	static int dy[] = {0,-1,0,1};
	static int N;
	static int cnt;
	

	private static int[][] sol(int arr[][], int x, int y, int dir, int len) {
		if(cnt>N*N) return arr;
		int dirr = dir%4;
		int new_x = x; int new_y = y;
		for(int i=0;i<len;i++) {
			new_x += dx[dirr]; new_y += dy[dirr];
			arr[new_x][new_y] = cnt++;
		}
		return sol(arr, new_x, new_y, dir+1, dirr%2==0?len:len-1);
		
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());
		
		for(int i=1;i<T+1;i++) {
			N = Integer.parseInt(in.readLine());
			int arr[][] = new int[N][N];
			cnt = 1;
			for(int j=0;j<N;j++)
				arr[0][j] = cnt++;  //첫째줄 저장
			int x = 0; int y = N-1; //현재 arr 인덱스

			arr = sol(arr, x, y, 0, N-1);
			
			System.out.println("#"+i);
			for(int j=0;j<N;j++) {
				for(int k=0;k<N;k++) 
					System.out.print(arr[j][k]+" ");
				System.out.println();
			}
			
		}

	}

}
