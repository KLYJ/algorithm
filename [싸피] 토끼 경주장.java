import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Algo1_서울_7_이유진 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// 입력
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int TC = Integer.parseInt(in.readLine()); // 테스트 케이스 입력 받기

		// 테스트 케이스만큼 반복
		for (int t = 1; t <= TC; t++) {
			sb.append("#").append(t).append(" "); // 출력 형식

			// 경주장 높이의 차를 저장하는 배열
			int road[] = new int[10];
			road[0] = 0;  // 첫번째 높이는 0 (이전 높이가 없으니까)

			// 경주장 높이의 차 저장 로직
			st = new StringTokenizer(in.readLine(), " ");
			int before = Integer.parseInt(st.nextToken());  // 이전 높이
			for (int i = 1; i < 10; i++) {
				int now = Integer.parseInt(st.nextToken()); // 현재 높이
				road[i] = now - before;  // '현재 높이 - 이전 높이' 저장 (높이의 차 저장)
				before = now;            // 이전 높이 = 현재 높이
			}

			// 토끼의 한계치 저장
			int rabbit[][] = new int[5][2];
			for (int i = 0; i < 5; i++) {
				st = new StringTokenizer(in.readLine(), " ");

				rabbit[i][0] = Integer.parseInt(st.nextToken());  // 오르막 한계치
				rabbit[i][1] = -Integer.parseInt(st.nextToken()); // 내리막 한계치
			}

			int answer = 5; // 정답 기본 셋팅 : 5(토끼의 수)
			boolean possible[] = new boolean[5]; // 경주 완수 가능한 토끼인지 여부 저장
			Arrays.fill(possible, true);         // 처음에는 다 가능하다고 셋팅
			
			// 경주장 높이 차 배열을 돌면서
			for (int i = 0; i < 10; i++) {
				// 토끼가
				for (int j = 0; j < 5; j++) {
					// 경주 가능한 토끼라면 
					if (possible[j]) {
						// '토끼의 오르막 한계치 < 현재 경주장 높이 차' 라면
						if(rabbit[j][0] < road[i]) {
							possible[j] = false;  // 해당 토끼는 경주 환수 불가능 
							answer--;			  // 정답 - 1 
						}
						else {
							// '토끼의 내리막 한계치 > 현재 경주장 높이 차' 라면
							if(rabbit[j][1] > road[i]) {
								possible[j] = false; // 해당 토끼는 경주 환수 불가능 
								answer--;            // 정답 - 1 
							}
						}
					}
				}
			}

			sb.append(answer).append("\n"); // 출력 형식대로 정답 저장
		}

		System.out.println(sb.toString()); // 정답 출력
	}

}
