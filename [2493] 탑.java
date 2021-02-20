package com.ssafy.algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());

		String str[] = in.readLine().split(" ");

		// 스택2개 - 앞에서부터 검색 - 통과
		int top;
		Stack<Integer> stack = new Stack<Integer>();
		Stack<Integer> index = new Stack<Integer>();

		int num = Integer.parseInt(str[0]);
		stack.push(num);
		index.push(1);
		System.out.print("0");

		for (int i = 1; i < N; i++) {
			num = Integer.parseInt(str[i]);
			while (true) {
				if (!stack.empty()) {
					top = stack.peek();
					if (top > num) {
						System.out.print(" " + index.peek());
						stack.push(num);
						index.push(i+1);
						break;

					} else {
						stack.pop();
						index.pop();
					}
				} else { 
					System.out.print(" 0");
					stack.push(num);
					index.push(i+1);
					break;
				}
			}
		} 
	}

}
