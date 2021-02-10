import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class SWEA_1224 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		Stack<Character> op = new Stack<>();
		Stack<Integer> num = new Stack<>();
		
		for(int t=1;t<=10;t++) {
			sb.append("#").append(t).append(" ");
			StringBuilder postfix = new StringBuilder();
			int len = Integer.parseInt(in.readLine());
			String input = in.readLine();			
			
			//중위표기 -> 후위표기
			for(int i=0;i<len;i++) {
				char c = input.charAt(i);
				if(Character.isDigit(c)) //숫자
					postfix.append(c);
				else { //연산자
					if(c=='(' || c=='*')
						op.push(c);
					else {
						while(!op.isEmpty()&&op.peek()!='(') {
							postfix.append(op.pop());
						}
						if(c=='+')
							op.push(c);
						else  // )
							op.pop();
					}
				}
			}
			
			//연산
			for(int i=0;i<postfix.length();i++) {
				char c = postfix.charAt(i);
				if(Character.isDigit(c))
					num.push(c-48);
				else {
					int num1 = num.pop();
					int num2 = num.pop();
					if(c=='*')
						num.push(num1*num2);
					else
						num.push(num1+num2);
				}
			}
			sb.append(num.pop()).append("\n");
		}
		System.out.println(sb.toString());
	}

}
