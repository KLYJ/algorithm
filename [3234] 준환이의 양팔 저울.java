import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Sol_0219_SWEA3234 {
	
	static int answer;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());
		StringBuilder sb = new StringBuilder();

		for (int t = 1; t <= T; t++) {
			sb.append("#").append(t).append(" ");
			int N = Integer.parseInt(in.readLine());
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			int input[] = new int[N];
			for (int i = 0; i < N; i++) {
				input[i] = Integer.parseInt(st.nextToken());
			}

			//방법1 : 순열로 추를 고른 후, 추를 올릴 수 있는 저울 선택한 후 재귀 호출
			sb.append(perm(input, N, 0, 0, 0, 0)).append("\n");
			
			//방법2 : 순열로 한 수열을 완성한 후, 부분집합 함수를 호출하여 가능한 경우인지 판단
			answer = 0;
			perm2(input, N, 0, new int[N], 0);
			sb.append(answer).append("\n");
		}
		
		
		System.out.println(sb);

	}

	private static void perm2(int[] input, int N, int selectTo, int selected[], int flag) {
		if(selectTo == N) {
			subset(selected, N, 0, 0, 0);
			return;
		}
		
		for(int i=0;i<N;i++) {
			if((flag & 1<<i) == 0) {
				selected[selectTo] = input[i];
				perm2(input, N, selectTo+1, selected, flag|1<<i);
			}
		}
	}

	private static void subset(int[] selected, int N, int selectTo, int left, int right) {
		if(selectTo == N) {
			answer++;
			return;
		}
		
		if(left>=right+selected[selectTo])
			subset(selected, N, selectTo+1, left, right+selected[selectTo]);
		subset(selected, N, selectTo+1, left+selected[selectTo], right);
	}

	//방법1
	private static int perm(int input[], int N, int selectTo, int flag, int left, int right) {
		if(selectTo == N) {
			return 1;
		}
		
		int answer = 0;
		
		for(int i=0;i<N;i++) {
			if((flag & 1<<i) == 0) {
				if(left >= right+input[i])
					answer += perm(input, N, selectTo+1, flag|1<<i, left, right+input[i]);
				answer += perm(input, N, selectTo+1, flag|1<<i, left+input[i], right);
			}
		}
		
		return answer;
	}

}
