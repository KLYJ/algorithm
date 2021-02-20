import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SWEA_1940 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());
		
		for(int t=1;t<=T;t++) {
			int N = Integer.parseInt(in.readLine());
			int speed = 0;
			int move = 0;
			for(int i=0;i<N;i++) {
				String input[] = in.readLine().split(" ");
				int command = Integer.parseInt(input[0]);
				int new_speed = 0;
				if(command != 0)
					new_speed = Integer.parseInt(input[1]);
				if(command==1)
					speed += new_speed;
				else if(command==2) {
					if(speed-new_speed <=0)
						speed = 0;
					else
						speed -= new_speed;
				}
				move += speed;
			}
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(t).append(" ").append(move);
			System.out.println(sb.toString());
		}
	}

}
