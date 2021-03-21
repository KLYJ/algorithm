package line;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Solution2 {
	
	public static void main(String[] args) {
		System.out.println(Arrays.toString(solution("AaTa+!12-3")));
		System.out.println(Arrays.toString(solution("aaaaZZZZ)")));
		System.out.println(Arrays.toString(solution("CaCbCgCdC888834A")));
		System.out.println(Arrays.toString(solution("UUUUU")));
		System.out.println(Arrays.toString(solution("ZzZz9Z824")));
	}

	public static int[] solution(String inp_str) {
		ArrayList<Integer> ans = new ArrayList<>();
		
		//1번 규칙
		int len = inp_str.length();
		if(8>len || 15<len) {
			ans.add(1);
		}
		
		//2번 규칙
		if(inp_str.matches(".*[^A-za-z0-9~!@#$%^&*].*")) {
			ans.add(2);
		}
		
		//3번 규칙
		int kinds = 0;
		if(inp_str.matches(".*[A-Z].*")) {
			kinds++;
		}
		if(inp_str.matches(".*[a-z].*")) {
			kinds++;
		}
		if(inp_str.matches(".*[0-9].*")) {
			kinds++;
		}
		if(inp_str.matches(".*[~!@#$%^&*].*")) {
			kinds++;
		}
		if(kinds < 3) {
			ans.add(3);
		}
		
		//4번 규칙
		int dp[] = new int[len];
		dp[0] = 1;
		for(int i=1;i<len;i++) {
			if(inp_str.charAt(i-1)==inp_str.charAt(i)) {
				dp[i] = dp[i-1]+1;
			}
			else {
				dp[i] = 1;
			}
			if(dp[i] == 4) {
				ans.add(4);
				break;
			}
		}
		
		//5번 규칙
		HashMap<Character, Integer> ch = new HashMap<>();
		for(int i=0;i<len;i++) {
			char c = inp_str.charAt(i);
			if(ch.containsKey(c) && ch.get(c) == 4) {
				ans.add(5);
				break;
			}
			ch.put(c, ch.getOrDefault(c, 0)+1);
		}
		
		
		if(ans.size()==0)
			return new int[] {0};
		else {
			int answer[] = new int[ans.size()];
			for(int i=0;i<ans.size();i++) {
				answer[i] = ans.get(i);
			}
			return answer;
		}
	}
}
