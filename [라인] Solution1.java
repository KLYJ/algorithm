package line;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution1 {
	
	public static void main(String[] args) {
		System.out.println(solution(new String[] {"SI JAVA JAVASCRIPT SQL PYTHON C#", "CONTENTS JAVASCRIPT JAVA PYTHON SQL C++", "HARDWARE C C++ PYTHON JAVA JAVASCRIPT", "PORTAL JAVA JAVASCRIPT PYTHON KOTLIN PHP", "GAME C++ C# JAVASCRIPT C JAVA"},
				new String[] {"PYTHON", "C++", "SQL"}, new int[] {7,5,5}));
		System.out.println(solution(new String[] {"SI JAVA JAVASCRIPT SQL PYTHON C#", "CONTENTS JAVASCRIPT JAVA PYTHON SQL C++", "HARDWARE C C++ PYTHON JAVA JAVASCRIPT", "PORTAL JAVA JAVASCRIPT PYTHON KOTLIN PHP", "GAME C++ C# JAVASCRIPT C JAVA"},
				new String[] {"JAVA", "JAVASCRIPT"}, new int[] {7,5}));
	}
	
	public static String solution(String[] table, String[] languages, int[] preference) {
		ArrayList<String>[] list = new ArrayList[5];
		
		//ArrayList에 table 저장
		StringTokenizer st = null;
		for(int i=0;i<5;i++) {
			list[i] = new ArrayList<>();
			st = new StringTokenizer(table[i], " ");
			for(int j=0;j<6;j++) {
				list[i].add(st.nextToken());
			}
		}
		
		//score 계산, max 찾기
		int max = 0;
		String job = "";
		for(int i=0;i<5;i++) {
			int score = 0;
			for(int j=0;j<languages.length;j++) {
				if(list[i].contains(languages[j])) {
					score += (6-list[i].indexOf(languages[j]))*preference[j];
				}
			}
			if(score>max) {
				max = score;
				job = list[i].get(0);
			}
			else if(score==max) {
				if(job.compareTo(list[i].get(0)) > 0) {
					job = list[i].get(0);
				}
			}
		}
		
        return job;
    }

}
