import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sol_0208_SWEA5215 {

	static int N, L, max;
	static int taste[];
	static int kcal[];

	//완전 탐색 O
	private static void subset1(int selectTo, boolean selected[]) {	
		if(selectTo==N) {
			int ksum = 0;
			int tsum = 0;
			for(int i=0;i<N;i++) {
				if(selected[i] == true) {
					ksum += kcal[i];
					tsum += taste[i];
				}
			}
			if(ksum<=L)
				max = Math.max(max, tsum);
			return;
		}
		
		selected[selectTo] = true;
		subset1(selectTo+1, selected);
		selected[selectTo] = false;
		subset1(selectTo+1, selected);
	}
	
	//완전 탐색X : 호출 후 확인
	private static void subset2(int selectTo, int sum_taste, int sum_kcal) {
		if(sum_kcal>L) return;
		if(sum_kcal<=L) max = Math.max(max, sum_taste);
		if(selectTo == N) return;
		
		subset2(selectTo+1, sum_taste+taste[selectTo], sum_kcal+kcal[selectTo]);
		subset2(selectTo, sum_taste, sum_kcal);
	}
		
	//완전 탐색 X : 호출 전 확인
	private static void subset3(int selectTo, int sum_taste, int sum_kcal) {	
		if(selectTo==N) {
			return;
		}
		
		int new_sum_kcal = sum_kcal+kcal[selectTo];
		int new_sum_taste = sum_taste+taste[selectTo];
		if(new_sum_kcal<=L) {
			max = Math.max(max, new_sum_taste);
			subset3(selectTo+1, new_sum_taste, new_sum_kcal);  //선택했을 때
		}
		subset3(selectTo+1, sum_taste, sum_kcal);              //안했을 때
		
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());

		for (int t = 1; t <= T; t++) {
			String info[] = in.readLine().split(" ");
			N = Integer.parseInt(info[0]); // 재료 수
			L = Integer.parseInt(info[1]); // 제한 칼로리

			taste = new int[N];
			kcal = new int[N];
			max = 0;

			for (int i = 0; i < N; i++) {
				String input[] = in.readLine().split(" ");
				taste[i] = Integer.parseInt(input[0]);
				kcal[i] = Integer.parseInt(input[1]);
			}

			subset1(0, new boolean[N]);
			subset2(0, 0, 0);
			subset3(0, 0, 0);
			
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(t).append(" ").append(max);
			System.out.println(sb.toString());

		}

	}
}
