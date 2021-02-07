import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Sol_0205_SWEA3499 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());
		
		int N;
		Queue<String> front = new LinkedList<String>();
		Queue<String> back = new LinkedList<String>();
		for(int t=1;t<=T;t++) {
			N = Integer.parseInt(in.readLine());
			String middle = "#";
			int temp = 0;
			StringTokenizer st = new StringTokenizer(in.readLine());
			for(int i=0;i<N/2;i++)
				front.offer(st.nextToken());
			if(N%2==1) {
				middle = st.nextToken();
				temp = 1;
			}
			for(int i=N/2+temp;i<N;i++)
				back.offer(st.nextToken());
			
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(t).append(" ");
			while(!front.isEmpty())
				sb.append(front.poll()).append(" ").append(back.poll()).append(" ");
			if(!middle.equals("#"))
				sb.append(middle);
			
			System.out.println(sb.toString());
		}
	}

}
