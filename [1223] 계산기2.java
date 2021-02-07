import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class SWEA_1223 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		Stack<Integer> number = new Stack<Integer>();
		Stack<Character> operator = new Stack<Character>();

		for (int t = 1; t <= 10; t++) {
			//방법1 : 후위 표기법 사용 X
			int len = Integer.parseInt(in.readLine());
			String str = in.readLine();
			for (int i = 0; i < len - 1; i++) {
				if (i % 2 == 0)
					number.push(str.charAt(i) - 48);
				else
					operator.push(str.charAt(i));
			}
			number.push(str.charAt(len - 1) - 48);
			
			while (!operator.isEmpty()) {
				int num1 = number.pop();
				char op = operator.pop();
				if (op == '+') {
					while (!operator.isEmpty() && operator.peek() == '*') {
						int a = number.pop();
						int b = number.pop();
						operator.pop();
						number.push(a * b);
					}
				}

				int num2 = number.pop();
				if(op=='+')
					number.push(num1 + num2);
				else
					number.push(num1*num2);
			}
			
			//방법2 : 후위 표기법 사용 O
			int len = Integer.parseInt(in.readLine());
			String str = in.readLine();
			StringBuilder postfix = new StringBuilder();
			for (int i = 0; i < len; i++) {
				char c = str.charAt(i);
				if(c >='0' && c<='9') {
					postfix.append(c);
				}
				else {
					if(operator.isEmpty())
						operator.push(c);
					else {
						while(c=='+'&&!operator.isEmpty()) {
							postfix.append(operator.pop());
						}
						operator.push(c);
					}
				}
			}
			
			while(!operator.isEmpty())
				postfix.append(operator.pop());
			
			for(int i=0;i<postfix.length();i++) {
				char c = postfix.charAt(i);
				if(c>='0' && c<='9')
					number.push(c-48);
				else {
					int num1 = number.pop();
					int num2 = number.pop();
					if(c=='*') number.push(num1*num2);
					else number.push(num1+num2);
				}
			}
			

			StringBuilder sb = new StringBuilder();
			sb.append("#").append(t).append(" ").append(number.pop());

			System.out.println(sb.toString());

		}
	}

}
