import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_2491 {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());

		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		int num[] = new int[N];
		for (int i = 0; i < N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}

		int answer = 1;
		int more = 1;
		int before = num[0];
		for(int i=1;i<N;i++) {
			if(before<=num[i])
				more++;
			else 
				more = 1;
			answer = Math.max(answer, more);
			before = num[i];
		}
		
		int less = 1;
		before = num[N-1];
		for(int i=N-2;i>=0;i--) {
			if(before<=num[i])
				less++;
			else 
				less = 1;
			answer = Math.max(answer, less);
			before = num[i];
		}
		
		System.out.println(answer);
		
	}

}
