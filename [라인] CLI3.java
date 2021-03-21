package line;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Solution7 {

	public static void main(String[] args) {
		System.out.println(Arrays.toString(solution("line", new String[] { "-s STRING", "-num NUMBER", "-e NULL", "-n ALIAS -num" },
				new String[] { "line -n 100 -s hi -e", "line -n 100 -e -num 150" })));
		System.out.println(Arrays.toString(solution("bank", new String[] { "-send STRING", "-a ALIAS -amount", "-amount NUMBERS" },
				new String[] { "bank -send abc -amount 500 200 -a 400", "bank -send abc -a hey" })));
	}

	public static boolean[] solution(String program, String[] flag_rules, String[] commands) {
		StringTokenizer st = null;

		int len = commands.length;
		boolean[] answer = new boolean[len];
		Arrays.fill(answer, true);

		// flag_rules를 HashMap에 저장 - <flag_name : flag_argument_type>
		HashMap<String, String> rule = new HashMap<>();
		HashMap<String, String> alias = new HashMap<>();
		for (int i = 0; i < flag_rules.length; i++) {
			st = new StringTokenizer(flag_rules[i], " ");
			if (st.countTokens() == 2) {  //별칭이 아닌경우
				String flag_name = st.nextToken();
				String flag_type = st.nextToken();
				rule.put(flag_name, flag_type);
			}
			else { //별칭인 경우
				String flag_name1 = st.nextToken();
				st.nextToken();
				String flag_name2 = st.nextToken();
				if(rule.containsKey(flag_name1)) {
					rule.put(flag_name2, rule.get(flag_name1));
				}
				if(rule.containsKey(flag_name2)) {
					rule.put(flag_name1, rule.get(flag_name2));
				}
				alias.put(flag_name1, flag_name2);
			}
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
		ArrayList<String> use = new ArrayList<>(); //이미 사용한 명령어 저장
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
				
				//별칭 사용 여부 검사
				if(use.contains(flag_name)) {
					answer[i] = false;
					break;
				}
				else {
					use.add(flag_name);
					use.add(alias.get(flag_name));
				}

				// flag_name이 제대로 왔는지 확인
				if (flag_type == null) {
					answer[i] = false;
					break;
				}

				// flag_argument가 알맞게 왔는지 확인
				// flag_type에 해당하는 정규표현식으로 확인
				String flag_regex = "";
				if (flag_type.startsWith("STRING"))
					flag_regex = "^[A-Za-z]*$";
				else
					flag_regex = "^[0-9]+$";

				int check = checkArugument(flag_type, flag_regex, list[i], j + 1);
				if (check == -1) {
					answer[i] = false;
					break;
				} else {
					j = check; // 다음 명령어로 이동
				}
			}

		}

		return answer;
	}

	private static int checkArugument(String flag_type, String flag_regex, ArrayList<String> list, int idx) {
		// NULL일 때 commands 끝이라면 true
		if (flag_type.equals("NULL") && list.size() == idx) {
			return idx + 1;
		}

		// STRING, NUMBER일 때 뒤에 명령어가 오면 false
		if (!flag_type.equals("NULL") && list.get(idx).startsWith("-")) {
			return -1;
		}

		while (idx < list.size() && !list.get(idx).startsWith("-")) { // argument일 때 검사(다음 명령어 전까지 검사)
			String argument = list.get(idx);

			// 1) NULL일 때 argument가 있으므로 false
			if (flag_type.equals("NULL")) {
				return -1;
			}

			// 2) STRING이나 NUMBER일 때 flag_regex에 해당하지 않는다면 false
			if (!list.get(idx).matches(flag_regex)) {
				return -1;
			}
			idx++;

			// STRING, NUMBER의 경우 한 번만 검사
			if (flag_type.length() == 6) {
				break;
			}
		}

		return idx - 1;
	}

}
