import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_3238 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		int T = Integer.parseInt(in.readLine());

		for (int t = 1; t <= T; t++) {
			sb.append("#").append(t).append(" ");
			st = new StringTokenizer(in.readLine(), " ");
			long n = Long.parseLong(st.nextToken());
			long r = Long.parseLong(st.nextToken());
			int p = Integer.parseInt(st.nextToken());

			long fact[] = new long[p];
			fact[0] = 1;
			for (int i = 1; i < p; i++) {
				fact[i] = fact[i - 1] * i;
				fact[i] %= p;
			}

			long answer = 1;
			while (n != 0 && r != 0) {
				int tempn = (int) (n % p);
				int tempr = (int) (r % p);
				answer *= fact[tempn] % p;

				for (int i = 0; i < p - 2; i++)
					answer = ((answer * fact[tempr]) % p * fact[tempn - tempr]) % p;

				n /= p;
				r /= p;
			}

			sb.append(answer).append("\n");
		}

		System.out.println(sb.toString());
	}

}
