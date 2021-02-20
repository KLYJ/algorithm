import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public class Sol_0209_SWEA1233 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		// 방법1 : 조건&배열 이용
		for(int t=1;t<=10;t++) {
			int N = Integer.parseInt(in.readLine());
			int num[] = new int[N+1];  //연산자 - 0, 숫자 - 1
			
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(t).append(" ");
			
			int flag = 0;
			for(int i=0;i<N;i++) {
				String info[] = in.readLine().split(" ");
				if(flag == 1) {
					continue;
				}
				int idx = Integer.parseInt(info[0]);
				char c = info[1].charAt(0);
				if(info.length==2) {  //최고 레벨은 무조건 숫자여야 함!
					if(!Character.isDigit(c)) {
						sb.append(0);
						flag = 1;
					}
				}
				//부모가 숫자인지 검사
				if(idx!=1 && num[idx/2]==1) {
					sb.append(0);
					flag = 1;
				}
				num[idx] = Character.isDigit(c)?1:0;
			}
			if(flag == 0) {
				sb.append(1);
			}
			
			System.out.println(sb.toString());
		}

		// 방법2 : DFS 이용
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= 10; t++) {
			int N = Integer.parseInt(in.readLine());
			int num[] = new int[N + 1]; // 연산자 - 0, 숫자 - 1

			// 제일 왼쪽 자식 노드의 번호 찾기
			int start = 2;
			while (start < N) {
				start *= 2;
			}
			start /= 2;
			
			sb.append("#").append(t).append(" ");

			for (int i = 0; i < N; i++) {
				String info[] = in.readLine().split(" ");
				int idx = Integer.parseInt(info[0]);
				char c = info[1].charAt(0);
				num[idx] = Character.isDigit(c)?1:0;
			}
			
			sb.append(dfs(num,1)).append("\n");

		}
		System.out.println(sb.toString());
	}

	private static int dfs(int[] num, int idx) {
		if(num[idx] == 0) {  //연산자일 때
			if(2*idx>=num.length)
				return 0;
			return dfs(num, 2*idx) * dfs(num, 2*idx+1);
		}
		else {  //숫자일 때
			if(2*idx<num.length)
				return 0;
			else
				return 1;
		}
	}

}
