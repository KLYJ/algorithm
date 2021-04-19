package ct01hw_서울_7_이유진;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_5607 {
	
	static long MOD = 1234567891;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		int T = Integer.parseInt(in.readLine());
		
		long fact[] = new long[1000001];
		fact[0]=1;
		for(int i=1;i<1000001;i++) {
			fact[i]=fact[i-1]*i;
			fact[i]%=MOD;
		}
		
		for(int t=1;t<=T;t++) {
			sb.append("#").append(t).append(" ");
			st = new StringTokenizer(in.readLine(), " ");
			int n = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			
			long up = 1;
			long down = 1;
			up = fact[n];
			down = (fact[n-r]*fact[r])%MOD;
			down = fermat(down,MOD-2);
			
			long answer = (up*down)%MOD;
			
			sb.append(answer).append("\n");
		}
		
		System.out.println(sb.toString());
	}
	
	private static long fermat(long a, long remain) {
		if(remain==0) return 1;
		else if(remain==1) return a;
		if(remain%2==0) {
			long temp = fermat(a,remain/2);
			return (temp*temp)%MOD;
		}
		long temp = fermat(a,remain-1)%MOD;
		return (temp*a)%MOD;
	}

}
