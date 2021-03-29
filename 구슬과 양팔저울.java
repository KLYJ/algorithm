import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Algo2_서울_07반_이유진 {
	
	static int chuCnt, ballCnt, chu[], ball[];
	static boolean answer[];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		// 추 입력 + 저장
		chuCnt = Integer.parseInt(in.readLine());
		chu = new int[chuCnt];
		st = new StringTokenizer(in.readLine());
		for(int i=0;i<chuCnt;i++) {
			chu[i] = Integer.parseInt(st.nextToken());
		}
		
		// 구슬 입력 + 저장
		ballCnt = Integer.parseInt(in.readLine());
		ball = new int[ballCnt];
		st = new StringTokenizer(in.readLine());
		for(int i=0;i<ballCnt;i++) {
			ball[i] = Integer.parseInt(st.nextToken());
		}
		
		// 결과 저장 - true : Y(가능을 확인한 상태), false : N(아직 가능한지 확인이 안된 상태)
		answer = new boolean[ballCnt];
		
		// 추 조합
		for(int i=1;i<=chuCnt;i++) {
			comb(0, i, new int[i], 0);
		}
		
		// 결과 출력
		for(int i=0;i<ballCnt;i++) {
			sb.append(answer[i]?"Y":"N").append(" ");
		}
		
		System.out.println(sb.toString());
		
	}

	private static void comb(int selectTo, int cnt, int[] selected, int start) {
		if(selectTo == cnt) {
			// 추의 조합을 부분집합으로 나눈 후 구슬 올려보기 위해 부분집합 구하기
			subset(0, selected, new boolean[selectTo]);  
			return;
		}
		
		// 추의 조합을 구해서 selected에 누적
		for(int i=start;i<chu.length;i++) {
			selected[selectTo] = chu[i];
			comb(selectTo+1, cnt, selected, i+1);
		}
	}

	private static void subset(int selectTo, int[] selected, boolean visited[]) {
		if(selectTo == selected.length) {
			int weight1=0, weight2=0; // 왼쪽(weight1), 오른쪽(weight2) 저울의 무게
			
			// 부분집합에서 비선택된 추는 왼쪽 저울에 올리고 선택된 추는 오른쪽 저울에 올리기 
			for(int i=0;i<selectTo;i++) {
				if(!visited[i]) {  // 비선택 : weight1에 저장
					weight1 += selected[i];
				}
				else {  // 선택 : weight2에 저장
					weight2 += selected[i];
				}
			}
			
			// 모든 구슬에 대해 왼쪽 저울이나 오른쪽 저울에 올리면 무게가 같아지는 지 확인
			for(int i=0;i<ballCnt;i++) {
				if(!answer[i]) { // 아직 이 구슬이 확인이 안 된 상태라면
					// 양쪽 저울 중 한 쪽에 구슬을 올리면 무게가 같아지는지 확인
					if(weight1+ball[i] == weight2 || weight2+ball[i] == weight1) {
						answer[i] = true;  // 결과를 answer에 반영
					}
				}
			}
			return;
		}
		
		// 부분집합
		visited[selectTo] = true;
		subset(selectTo+1, selected, visited);
		
		visited[selectTo] = false;
		subset(selectTo+1, selected, visited);
	}

}
