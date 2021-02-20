import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sol_0201_1_SWEA1289 {

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());
		StringBuilder answer = new StringBuilder();
		
		for (int i = 1; i < T + 1; i++) {
			int cnt = 0;
			// 원래 메모리 값 -> 00...0 으로 바꾸기
			String input = in.readLine(); 
			int len = input.length();
			char c[] = new char[len];
			c = input.toCharArray();
			while (String.valueOf(c).contains("1")) {
				for (int j = 0; j < len; j++) {
					if (c[j] != '0') {
						c[j] = '0';
						for (int z = j + 1; z < len; z++) {
							c[z] = c[z] == '0' ? '1' : '0';
						}
						cnt++;
					} else
						continue;
				}
			}
			answer.append("#" + i + " " + cnt+"\n");
		}
		System.out.println(answer.toString());
	}

}
