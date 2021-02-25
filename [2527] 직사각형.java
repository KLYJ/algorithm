package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_2527 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int rec1[] = new int[4];
		int rec2[] = new int[4];
		
		for(int i=0;i<4;i++) {
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			for(int j=0;j<4;j++)
				rec1[j] = Integer.parseInt(st.nextToken());
			for(int j=0;j<4;j++)
				rec2[j] = Integer.parseInt(st.nextToken());
			sb.append(calc(rec1, rec2)).append("\n");			
		}
		
		System.out.println(sb.toString());
				
	}
	
	private static char calc(int rec1[], int rec2[]) {
		//c
		if((rec1[0]==rec2[2]&&rec1[1]==rec2[3]) || (rec2[0]==rec1[2]&&rec2[1]==rec1[3])
				|| (rec1[2]==rec2[0]&&rec1[1]==rec2[3]) || (rec1[0]==rec2[2]&&rec1[3]==rec2[1]))
			return 'c';
		//d
		if(rec1[2]<rec2[0] || rec1[3]<rec2[1] || rec2[2]<rec1[0] || rec2[3]<rec1[1])
			return 'd';
		//b
		if(rec1[0]==rec2[2] || rec1[1]==rec2[3] || rec2[0]==rec1[2] || rec2[1]==rec1[3])
			return 'b';
		//a
		return 'a';
	}

}
