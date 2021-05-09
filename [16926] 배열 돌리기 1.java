import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Sol_0210_BJ16926 {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] info = in.readLine().split(" ");
		int row = Integer.parseInt(info[0]);
		int col = Integer.parseInt(info[1]);
		int rotate = Integer.parseInt(info[2]);

		// 배열 입력 받기
		int arr[][] = new int[row][col];
		for (int i = 0; i < row; i++) {
			String line[] = in.readLine().split(" ");
			for (int j = 0; j < col; j++) {
				arr[i][j] = Integer.parseInt(line[j]);
			}
		}

		// queue 설정
		int queue_num = row % 2 == 0 ? row / 2 : row / 2 + 1;
		Queue<Integer>[] queue = new LinkedList[queue_num];
		for (int i = 0; i < queue_num; i++) {
			queue[i] = new LinkedList<>();
		}
		
		// queue에 배열 띠 저장 : 1번 돈 상태로 저장
		int dx[] = {0,1,0,-1};
		int dy[] = {1,0,-1,0};		
		for(int i=0;i<queue_num;i++) {
			int x = i;
			int y = i;
			for(int j=0;j<4;j++) {
				x += dx[j];
				y += dy[j];
				while(x>=i && x<row-i && y>=i && y<col-i) {
					queue[i].offer(arr[x][y]);
					x += dx[j];
					y += dy[j];
				}
				x -= dx[j];
				y -= dy[j];
			}
		}
		
		//queue 돌리기
		for(int i=0;i<rotate;i++) {
			for(int j=0;j<queue_num;j++) {
				queue[j].offer(queue[j].poll());
			}
		}
		
		//queue를 다시 배열에 저장
		for(int i=0;i<queue_num;i++) {
			int x = i;
			int y = i;
			for(int j=0;j<4;j++) {
				x += dx[j];
				y += dy[j];
				while(x>=i && x<row-i && y>=i && y<col-i) {
					arr[x][y] = queue[i].poll();
					x += dx[j];
					y += dy[j];
				}
				x -= dx[j];
				y -= dy[j];
			}
		}
		
		//출력
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<row;i++) {
			for(int j=0;j<col;j++) {
				sb.append(arr[i][j]).append(" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
		

	}

}
