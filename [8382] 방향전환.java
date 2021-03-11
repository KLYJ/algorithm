package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_8382 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());
		StringBuilder sb = new StringBuilder();

		for (int t = 1; t <= T; t++) {
			sb.append("#").append(t).append(" ");
			int answer = 0;

			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			int xy[] = new int[4];
			for (int i = 0; i < 4; i++) {
				xy[i] = Integer.parseInt(st.nextToken());
			}

			int sero = Math.abs(xy[2] - xy[0]) + 1;
			int garo = Math.abs(xy[3] - xy[1]) + 1;

			int min = Math.min(garo, sero);
			answer = 2 * (min - 1);
			
			if (garo != sero) {
				int spare = garo - min + sero - min;
				
				if(spare%2==0) {
					answer += 2*spare;
				}
				else {
					answer += 2*spare-1;
				}
			}
      
			sb.append(answer).append("\n");
		}
    
		System.out.println(sb.toString());

	}

}
