import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sol_0202_2_SWEA1210 {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		//방향 : 왼, 오, 위
		int dx[] = {0,0,-1};
		int dy[] = {-1,1,0};
		int N = 100;
		
		for(int i=1;i<11;i++) {
			in.readLine();
			int now[] = new int[2];  //현재 사다리 index
			int board[][] = new int[N][N];  //전체 사다리
			for(int j=0;j<N;j++) {
				String arr[] = in.readLine().split(" ");
				for(int k =0;k<N;k++) {
					int value =  Integer.parseInt(arr[k]);
					board[j][k] = value;
					if(value == 2) { //출발점 now에 저장1
						now[0] = j;
						now[1] = k; 
					}
				}
			}	
			
			//왼오 방향 알려워야함
			int dir = 2;
			while(now[0]!=0) {
				for(int j=0;j<3;j++) {
					if((dir==0&&j==1) || (dir==1&&j==0)) continue;
					int new_x = now[0]+dx[j];
					int new_y = now[1]+dy[j];
					if(new_x>-1&&new_x<100&&new_y>-1&&new_y<100&&(board[new_x][new_y]==1)) {
						now[0] = new_x;
						now[1] = new_y;
						dir = j;
						break;
					}
				}
			}
			
			System.out.println("#"+i+" "+now[1]);
		}
	}
}
