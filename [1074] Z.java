import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sol_0216_BJ1074 {

	static int r, c;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String info[] = in.readLine().split(" ");
		int N = Integer.parseInt(info[0]);
		r = Integer.parseInt(info[1]);
		c = Integer.parseInt(info[2]);

		System.out.println((int)calc(N, 0, 0));
	}

	private static double calc(int N, double x, double y) {
		if (N == 1) {
			if (r == x && c == y)
				return 0;
			else if (r == x && c == y + 1)
				return 1;
			else if (r == x + 1 && c == y)
				return 2;
			else
				return 3;
		}

		double answer = 0;
		double boundary = Math.pow(2, N - 1);
		if (r < x+boundary && c < y+boundary) {
			answer += calc(N - 1, x, y);
		} else if (r < x+boundary && c >= y+boundary) {
			answer += boundary * boundary;
			answer += calc(N - 1, x, y+boundary);
		} else if (r >= x+boundary && c < y+boundary) {
			answer += boundary * boundary*2;
			answer += calc(N - 1, x+boundary, y);
		} else {
			answer += boundary * boundary*3;
			answer += calc(N - 1, x+boundary, y+boundary);
		}

		return answer;
	}

}
