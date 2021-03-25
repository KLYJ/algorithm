import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Sol_0325_SWEA3307 {
	
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
