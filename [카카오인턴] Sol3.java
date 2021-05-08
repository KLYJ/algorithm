package kakao_intern_21;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;

public class Sol3 {

	public static void main(String[] args) {
//		System.out.println(solution(8, 2, new String[] {"D 2","C","U 3","C","D 4","C","U 2","Z","Z"}));
//		System.out.println(solution(8, 2, new String[] {"D 2","C","U 3","C","D 4","C","U 2","Z","Z","U 1","C"}));
		System.out.println(solution(4, 0, new String[] {"C","Z","C","Z","C","Z","C"}));
	}
	
	public static String solution(int n, int k, String[] cmd) {
		// LinkedList 생성
		LinkedList<Integer> list = new LinkedList<>();
		for(int i=0;i<n;i++)
			list.add(i);
		
		// 현재 위치 설정
		int current = k;
		
		// 현재 마지막 위치
		int lastIdx = n-1;
		
		// 복구할 내용 저장 stack<idx><value> -> 위치, 값
		Stack<Integer> stack = new Stack<>();
		
		// 명령어 실행
		for(String com : cmd) {
			System.out.println("명령어 : "+com);
			System.out.println("수행 전 위치 : "+current);
			
			char c = com.charAt(0);
			
			// 커서 아래로
			if(c=='D') {
				current += com.charAt(2)-48;
				if(current > lastIdx)
					current -= lastIdx+1;
			}
			// 커서 위로
			else if(c=='U') {
				current -= com.charAt(2)-48;
				if(current < 0)
					current += lastIdx+1;
			}
			// 삭제
			else if(c=='C') {
				// 스택에 저장
				stack.push(current);
				stack.push(list.get(current));
				
				// 삭제 수행
				list.remove(current);
				
				// 현재 위치 수정
				if(current == lastIdx)
					current--;
				lastIdx--;
			}
			
			// 재실행
			else {
				// 최근 삭제 노드 가져오기
				int value = stack.pop();
				int idx = stack.pop();
				
				// 다시 리스트에 넣어주기
				list.add(idx, value);
				lastIdx++;
				
				// 현재 위치 수정
				if(idx <= current)
					current++;
			}
			System.out.println("수행 후 위치 : "+current);
			System.out.println(list);
		}		
		
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<n;i++) {
			if(list.contains(i))
				sb.append("O");
			else
				sb.append("X");
		}
		
        return sb.toString();
    }

}
