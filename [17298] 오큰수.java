package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BJ_17298 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		
		Stack<Integer> input = new Stack<>();
		for(int i=0;i<N;i++)
			input.push(Integer.parseInt(st.nextToken()));
		
		Stack<Integer> oknsu = new Stack<>();
		Stack<Integer> answer = new Stack<>();
		
		while(!input.isEmpty()) {
			int num = input.pop();
			
			while(!oknsu.isEmpty() && oknsu.peek() <= num)
				oknsu.pop();
			
			if(oknsu.isEmpty())
				answer.push(-1);
			else
				answer.push(oknsu.peek());
			
			oknsu.push(num);
		}
		
		StringBuilder sb = new StringBuilder();
		while(!answer.isEmpty())
			sb.append(answer.pop()).append(" ");
		
		System.out.println(sb.toString());
		
	}

}
