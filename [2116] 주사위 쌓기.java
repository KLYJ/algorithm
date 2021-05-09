package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_2116 {

	static int dice[][];
	static int N, answer = 0;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		
		dice = new int[N][6];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			// 쌍끼리 연달아 저장 : 순서  0 2 4 3 5 1 = A F / B D / C E 순서가 되도록 저장!
			dice[i][0] = Integer.parseInt(st.nextToken());
			dice[i][2] = Integer.parseInt(st.nextToken());
			dice[i][4] = Integer.parseInt(st.nextToken());
			dice[i][3] = Integer.parseInt(st.nextToken());
			dice[i][5] = Integer.parseInt(st.nextToken());
			dice[i][1] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < 6; i++) {
			if(i%2==0)
				setDice(dice[0][i], dice[0][i+1], 0, 0);
			else
				setDice(dice[0][i], dice[0][i-1], 0, 0);
		}
		System.out.println(answer);

	}

	//down과 up은 값(주사위의 숫자)!
	private static void setDice(int down, int up, int selectTo, int sum) {
		//현재 쌓은 주사위의 옆면 중 가장 큰 수 합에 누적
		for(int i=6;i>=1;i--) {
			if(i!=down && i!=up) {
				sum += i;
				break;
			}
		}
		
		
		if (selectTo == N-1) {
			answer = Math.max(answer, sum);
			return;
		}
		
		//다음 쌓을 주사위의 윗면 찾아주기
		down = up;  //현재 윗면이 다음 아랫면이니까
		for(int i=0;i<6;i++) {
			if(dice[selectTo+1][i]==down) {
				if(i%2==0)
					up = dice[selectTo+1][i+1];
				else
					up = dice[selectTo+1][i-1];
			}
		}

		setDice(down, up, selectTo+1, sum);
	}

}
