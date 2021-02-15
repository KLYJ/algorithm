import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class 모의고사 {

	//방법1 : max 찾은 후 stack에 max와 같은 번호 저장
	public int[] solution(int[] answers) {
		int supoja[][] = { { 1, 2, 3, 4, 5 }, { 2, 1, 2, 3, 2, 4, 2, 5 }, { 3, 3, 1, 1, 2, 2, 4, 4, 5, 5 } };
		int score[] = new int[3];

		// 수포자들의 점수 저장
		for (int i = 0; i < answers.length; i++) {
			for (int j = 0; j < 3; j++) {
				int len = supoja[j].length;
				if (answers[i] == supoja[j][i % len])
					score[j]++;
			}
		}

		// 최고 점수 찾기
		int max = score[0];
		for (int i = 1; i < 3; i++) {
			if (score[i] > max)
				max = score[i];
		}

		// 최고 점수를 갖는 수포자 번호 저장
		Stack<Integer> st = new Stack<>();
		for (int i = 0; i < 3; i++) {
			if (max == score[i])
				st.push(i + 1);
		}

		int answer[] = new int[st.size()];
		for (int i = st.size() - 1; i >= 0; i--)
			answer[i] = st.pop();

		return answer;
	}
	
	//방법2 : max 찾으면서 ArrayList 업데이트(빠름)
	public int[] solution2(int[] answers) {
		int supoja[][] = { { 1, 2, 3, 4, 5 }, { 2, 1, 2, 3, 2, 4, 2, 5 }, { 3, 3, 1, 1, 2, 2, 4, 4, 5, 5 } };
		int score[] = new int[3];

		// 수포자들의 점수 저장
		for (int i = 0; i < answers.length; i++) {
			if (answers[i] == supoja[0][i % 5])	score[0]++;
			if (answers[i] == supoja[1][i % 8])	score[1]++;
			if (answers[i] == supoja[2][i % 10])score[2]++;
		}
		
		List<Integer> list = new ArrayList<>();
		int max = 0;
		for(int i=0;i<3;i++) {
			if(max<score[i]) {
				max = score[i];
				list.clear();
				list.add(i+1);
			}
			else if(max == score[i])
				list.add(i+1);
		}
		
		int answer[] = new int[list.size()];
		for(int i=0;i<list.size();i++)
			answer[i] = list.get(i);
		
		return answer;
		
	}
}
