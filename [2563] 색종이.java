import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_2563 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		boolean paper[][] = new boolean[100][100]; //도화지
		int answer = 0; //색종이 넓이
		for(int i=0;i<N;i++) {
			String info[] = in.readLine().split(" ");
			int x = Integer.parseInt(info[0]);
			int y = Integer.parseInt(info[1]);
			for(int m=x;m<x+10;m++) {
				for(int n=y;n<y+10;n++) {
					if(paper[m][n]==false) {
						paper[m][n] = true;
						answer++;
					}
				}
			}
		}
		System.out.println(answer);
	}

}
