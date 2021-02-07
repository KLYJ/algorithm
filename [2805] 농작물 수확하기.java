import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sol_0203_SWEA2805 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());
		
		for(int i=1;i<T+1;i++) {
			int N = Integer.parseInt(in.readLine());
			int farm[][] = new int[N][N];
			for(int j=0;j<N;j++) {
				String line = in.readLine();
				for(int k=0;k<N;k++) {
					farm[j][k] = line.charAt(k)-48;
				}
			}
			
			int sum = 0;
			int k =N/2;
			for(int j=0;j<N;j++) {
				for(int m=k;m<N-k;m++) {
					sum += farm[j][m];
				}
				if(j<N/2) k--;
				else k++;
			}
		
			System.out.println("#"+i+" "+sum);
		}

	}

}
