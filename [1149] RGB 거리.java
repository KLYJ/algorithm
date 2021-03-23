import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_1149 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		
		StringTokenizer st = null;
		int house[][] = new int[N][3];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for(int j=0;j<3;j++) {
				house[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=1;i<N;i++) {
			house[i][0] += Math.min(house[i-1][1], house[i-1][2]);
			house[i][1] += Math.min(house[i-1][0], house[i-1][2]);
			house[i][2] += Math.min(house[i-1][0], house[i-1][1]);
		}
		
		int min = Integer.MAX_VALUE;
		for(int i=0;i<3;i++) {
			min = Math.min(house[N-1][i], min);
		}
		
		System.out.println(min);
		
	}

}
