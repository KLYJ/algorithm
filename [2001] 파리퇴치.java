import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SWEA_2001 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());
		
		for(int i=1;i<T+1;i++) {
			String input[] = in.readLine().split(" ");
			int N = Integer.parseInt(input[0]);
			int M = Integer.parseInt(input[1]);
			int map[][] = new int[N][N];
			for(int j=0;j<N;j++) {
				String pari[] = in.readLine().split(" ");
				for(int k=0;k<N;k++){
					map[j][k] = Integer.parseInt(pari[k]);
				}
			}
			
			int total = 0;
			for(int j=0;j<=N-M;j++) {
				for(int k=0;k<=N-M;k++) {
					int sum = 0;
					for(int m=j;m<j+M;m++) {
						for(int n=k;n<k+M;n++) {
							sum += map[m][n];
						}
					}
					total = Math.max(total, sum);
				}
			}
			
			System.out.println("#"+i+" "+total);
		}
		 

	}

}
