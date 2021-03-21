package line;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Solution5 {

	public static void main(String[] args) {
		System.out.println(Arrays.toString(solution("line", new String[] { "-s STRING", "-n NUMBER", "-e NULL" },
				new String[] { "line -n 100 -s hi -e", "lien -s Bye" })));
		System.out.println(Arrays.toString(solution("line", new String[] { "-s STRING", "-n NUMBER", "-e NULL" },
				new String[] { "line -s 123 -n HI", "line fun" })));
	}

	public static boolean[] solution(String program, String[] flag_rules, String[] commands) {
		StringTokenizer st = null;

		int len = commands.length;
		boolean[] answer = new boolean[len];
		Arrays.fill(answer, true);

		// flag_rules를 HashMap에 저장 - <flag_name : flag_argument_type>
		HashMap<String, String> rule = new HashMap<>();
		for (int i = 0; i < flag_rules.length; i++) {
			st = new StringTokenizer(flag_rules[i], " ");
			String flag_name = st.nextToken();
			String flag_type = st.nextToken();

			rule.put(flag_name, flag_type);
		}

		// commands를 ArrayList에 저장
		ArrayList<String>[] list = new ArrayList[len];
		for (int i = 0; i < len; i++) {
			list[i] = new ArrayList<>();
			st = new StringTokenizer(commands[i], " ");
			while (st.hasMoreTokens()) {
				list[i].add(st.nextToken());
			}
		}

		// commands 검사
		for (int i = 0; i < len; i++) {
			// 프로그램 이름과 같은지 검사
			if (!list[i].get(0).equals(program)) {
				answer[i] = false;
				continue;
			}

			// flag_rule에 맞는지 검사
			for (int j = 1; j < list[i].size(); j++) {
				// 명령어 flag_name과 타입
				String flag_name = list[i].get(j);
				String flag_type = rule.get(flag_name);

				// flag_name이 제대로 왔는지 확인
				if (flag_type == null) {
					answer[i] = false;
					break;
				}

				// flag_argument가 알맞게 왔는지 확인
				// flag_type에 해당하는 정규표현식으로 확인
				String flag_regex = "";
				if (flag_type.equals("STRING"))
					flag_regex = "^[A-Za-z]*$";
				else
					flag_regex = "^[0-9]+$";

				int check = checkArugument(flag_type, flag_regex, list[i], j + 1);
				if (check == -1) {
					answer[i] = false;
					break;
				}
				else {
					j++; //다음 명령어로 이동
				}
			}

		}

		return answer;
	}

	private static int checkArugument(String flag_type, String flag_regex, ArrayList<String> list, int idx) {
		//NULL일 때 commands 끝이라면 true
		if(flag_type.equals("NULL") && list.size()==idx) {
			return 0;
		}
		
		//STRING, NUMBER일 때 뒤에 명령어가 오면 false
		if(!flag_type.equals("NULL") && list.get(idx).startsWith("-")) {
			return -1;
		}
		
		if(!list.get(idx).startsWith("-")) { //argument일 때 검사(다음 명령어 전까지 검사)
			String argument = list.get(idx);
			
			// 1) NULL일 때 argument가 있으므로 false
			if (flag_type.equals("NULL")) {
				return -1;
			}

			// 2) STRING이나 NUMBER일 때 flag_regex에 해당하지 않는다면 false
			if (!list.get(idx).matches(flag_regex)) {
				return -1;
			}
		}
		
		return 0;
	}
}
