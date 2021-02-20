import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Stack;

public class Sol_0204_SWEA1218 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		//1. 스택 사용
		HashMap<Character, Character> map = new HashMap<>();
		map.put('{', '}'); map.put('[', ']'); map.put('<', '>'); map.put('(', ')');
		for(int j=1;j<11;j++) {
			int len = Integer.parseInt(in.readLine());
			String str = in.readLine();
			Stack<Character> st = new Stack<>();
			int flag = 0;
			for(int i=0;i<len;i++) {
				char c = str.charAt(i);
				if(map.keySet().contains(c))
					st.push(c);
				else {
					if(map.get(st.peek())==c)
						st.pop();
					else {
						flag = 1;
						break;
					}
				}
			}
			if(flag==0)
				System.out.println("#"+j+" "+1);
			else
				System.out.println("#"+j+" "+0);
		}
		
		//2. for문 사용
		for(int j=1;j<11;j++) {
			int len = Integer.parseInt(in.readLine());
			String str = in.readLine();
			int sum = 0;
			for(int i=0;i<len;i++) {
				char c = str.charAt(i);
				switch(c) {
				case '{':
					sum += 4;
					break;
				case '[':
					sum += 3;
					break;
				case '(':
					sum += 2;
					break;
				case '<':
					sum += 1;
					break;
				case '}':
					sum -= 4;
					break;
				case ']':
					sum -= 3;
					break;
				case ')':
					sum -= 2;
					break;
				case '>':
					sum -= 1;
					break;
				default:
					break;
				}
				if(sum<0)
					break;
			}
			if(sum==0) 
				System.out.println("#"+j+" "+1);
			else
				System.out.println("#"+j+" "+0);
		}
		
	}
}
