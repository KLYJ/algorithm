import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Sol_0325_SWEA3307 {
	
	// DP1 : O(n^2)
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		int T = Integer.parseInt(in.readLine());

		for (int t = 1; t <= T; t++) {
			sb.append("#").append(t).append(" ");

			int N = Integer.parseInt(in.readLine());

			int arr[] = new int[N];
			int dp[] = new int[N];

			st = new StringTokenizer(in.readLine(), " ");
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}

			int max = 0;
			for (int i = 0; i < N; i++) {
				dp[i] = 1;
				for (int j = 0; j < i; j++) {
					if (arr[j] < arr[i] && dp[i] < dp[j] + 1) {
						dp[i] = dp[j] + 1;
					}
				}
				if (max < dp[i])
					max = dp[i];

			}

			sb.append(max).append("\n");
		}
		System.out.println(sb.toString());
	}
	
	// DP2 : O(nlogn)
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		int T = Integer.parseInt(in.readLine());
		
		for(int t=1;t<=T;t++) {
			sb.append("#").append(t).append(" ");
			
			int N = Integer.parseInt(in.readLine());
			st = new StringTokenizer(in.readLine(), " ");
			int dp[] = new int[N];
			Arrays.fill(dp, Integer.MAX_VALUE);
			
			for(int i=0;i<N;i++) {
				int num = Integer.parseInt(st.nextToken());
				for(int j=0;j<N;j++) {
					if(dp[j]>num) {
						dp[j] = num;
						break;
					}
				}
			}
			
			int answer = 0;
			for(int i=0;i<N;i++) {
				if(dp[i] == Integer.MAX_VALUE)
					break;
				answer++;
			}
			
			sb.append(answer).append("\n");
		}
		System.out.println(sb.toString());
	}

}
