import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_1251 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		int T = Integer.parseInt(in.readLine());

		for (int t = 1; t <= T; t++) {
			sb.append("#").append(t).append(" ");

			int N = Integer.parseInt(in.readLine());

			st = new StringTokenizer(in.readLine(), " ");
			double x[] = new double[N];
			for (int i = 0; i < N; i++)
				x[i] = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(in.readLine(), " ");
			double y[] = new double[N];
			for (int i = 0; i < N; i++)
				y[i] = Integer.parseInt(st.nextToken());
			// x, y 배열에 위치를 넣어줬으므로 따로 인접행렬, 인접리스트가 필요하지 않다!
			// [거리 = x차이^2 + y차이^2]니까 x, y 배열을 이용하면 됨!!

			double E = Double.parseDouble(in.readLine());

			boolean visited[] = new boolean[N];
			double minEdge[] = new double[N];
			Arrays.fill(minEdge, Double.MAX_VALUE);

			double answer = 0;
			minEdge[0] = 0;

			for (int i = 0; i < N; i++) {
				double min = Double.MAX_VALUE;
				int minVertex = 0;
				for (int j = 0; j < N; j++) {
					if (!visited[j] && min > minEdge[j]) {
						minVertex = j;
						min = minEdge[j];
					}
				}

				answer += min * E;
				visited[minVertex] = true;

				for (int j = 0; j < N; j++) {
					double weight = Math.pow(Math.abs(x[minVertex] - x[j]), 2)
							+ Math.pow(Math.abs(y[minVertex] - y[j]), 2);
					if (!visited[j] && minEdge[j] > weight) {
						minEdge[j] = weight;
					}
				}

			}

			sb.append(Math.round(answer)).append("\n");
		}
		System.out.println(sb.toString());
	}

}
