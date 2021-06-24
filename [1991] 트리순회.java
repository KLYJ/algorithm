package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.StringTokenizer;

public class BJ_1991_2 {

	static class Children {
		char left, right;

		public Children(char left, char right) {
			super();
			this.left = left;
			this.right = right;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int N = Integer.parseInt(in.readLine());

		Map<Character, Children> map = new HashMap<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			map.put(st.nextToken().charAt(0), new Children(st.nextToken().charAt(0), st.nextToken().charAt(0)));
		}

		StringBuilder sb1 = new StringBuilder();
		StringBuilder sb2 = new StringBuilder();
		StringBuilder sb3 = new StringBuilder();

		// 전위순회 + 중위순회
		Stack<Character> stack = new Stack<>();
		boolean visited[] = new boolean[N];
		stack.add('A');
		while (!stack.isEmpty()) {
			char now = stack.peek();

			if (visited[now - 'A']) {
				char right = map.get(now).right;

				// 중위순회
				sb2.append(now);

				// 오른쪽 push & 현재 pop
				stack.pop();
				if (right != '.')
					stack.push(right);

			} else {
				char left = map.get(now).left;

				// 왼쪽 push
				if (left != '.')
					stack.push(left);
				visited[now - 'A'] = true;
				
				// 전위순회
				sb1.append(now);
			}
		}

		// 후위순회
		stack.push('A');
		Arrays.fill(visited, false);
		while (!stack.isEmpty()) {
			char now = stack.peek();

			if (visited[now - 'A']) {
				char right = map.get(now).right;

				// 오른쪽 push
				if (right != '.' && !visited[right - 'A'])
					stack.push(right);
				else {
					sb3.append(now);
					stack.pop();
				}
			} else {
				char left = map.get(now).left;

				// 왼쪽 push
				if (left != '.')
					stack.push(left);
				visited[now - 'A'] = true;
			}
		}

		System.out.println(sb1.append("\n").append(sb2).append("\n").append(sb3).toString());
	}

}
