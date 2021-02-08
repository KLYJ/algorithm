import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class hw0201_1_swea_5432 {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());
		
		//stack
		for (int k = 1; k <= T; k++) {
			String gh = in.readLine();
			char c[] = gh.replace("()", "0").toCharArray(); // 괄호 저장
			Stack<Integer> piece = new Stack<>(); // 조각 저장
			int piece_cnt = 0; // 총 조각 개수

			for (int i = 0; i < c.length; i++) {
				if (c[i] == '(')
					piece.push(1);
				else if(c[i]=='0') {  //()
					piece_cnt += piece.size();
				}
				else { //)
					piece_cnt += piece.pop();
				}
			}

			System.out.println("#" + k + " " + piece_cnt);
		}
	}
}
