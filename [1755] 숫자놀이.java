import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Algo1_서울_07반_이유진 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader in  = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		
		// M과 N 입력
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		// ArrayList에 인덱스(숫자)에 따른 영단어 저장
		ArrayList<String> engNum = new ArrayList<>();
		engNum.add("zero"); engNum.add("one"); engNum.add("two"); 
		engNum.add("three"); engNum.add("four"); engNum.add("five"); 
		engNum.add("six"); engNum.add("seven"); engNum.add("eight"); engNum.add("nine"); 
		
		// 정수 -> 영단어 : M이상 N이하의 정수를 영단어로 바꿔서 num배열에 저장
		String num[] = new String[N-M+1];
		int nowNum = M; // num배열에 저장할 수
		for(int i=0;i<N-M+1;i++) {
			if(nowNum<10) { // nowNum이 한 자리 수라면
				num[i] = engNum.get(nowNum);  // nowNum에 해당하는 영단어 저장
			}
			else { // nowNum이 두 자리 수라면
				sb.setLength(0);
				sb.append(engNum.get(nowNum/10)).append(" "); // 십의 자리 수에 해당하는 영단어 저장
				sb.append(engNum.get(nowNum%10)).append(" "); // 일의 자리 수에 해당하는 영단어 저장
				num[i] = sb.toString();
			}
			nowNum++; // 현재 수++
		}
		
		// num의 영단어들을 사전 순으로 정렬
		Arrays.sort(num, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);  // 사전순 정렬
			}
		});
		
		// 영단어 -> 정수 : M이상 N이하의 영단어를 정수로 바꿔서 answer배열에 저장
		int answer[] = new int[N-M+1];
		for(int i=0;i<N-M+1;i++) {
			st = new StringTokenizer(num[i], " ");     // num[i]의 영단어를 스페이스 기준으로 분리
			int num1 = engNum.indexOf(st.nextToken()); // 앞 자리 숫자를 num1에 저장(engNum의 인덱스로)
			if(st.hasMoreTokens()) { // 두 자리라면(10보다 큰 수라면)
				num1 *= 10;          // 앞 자리 수를 십의 자리로 만들기
				int num2 = engNum.indexOf(st.nextToken()); // 뒷 자리 숫자를 num2에 저장(engNum의 인덱스로)
				num1 += num2;        // 십의 자리 수  + 일의 자리 수
			}
			answer[i] = num1; // 숫자를 answer에 저장
		}
		
		// 결과를 10개씩 출력
		sb.setLength(0);
		for(int i=0;i<answer.length;i++) {
			// 출력할 숫자 sb에 저장
			sb.append(answer[i]).append(" ");
			
			// 10개 출력했으면 줄바꿈
			if((i+1)%10==0) {     
				sb.append("\n");
			}
		}
		
		System.out.println(sb.toString());
	}

}
