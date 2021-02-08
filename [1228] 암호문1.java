import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Sol_0208_SWEA1228 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		for(int t=1;t<=10;t++) {
			int N = Integer.parseInt(in.readLine());
			String pw[] = in.readLine().split(" ");
			LinkedList<Integer> pw_list = new LinkedList<>();
			for(int i=0;i<10;i++) {
				pw_list.add(Integer.parseInt(pw[i]));
			}
			
			int cm_cnt = Integer.parseInt(in.readLine());
			StringTokenizer commands = new StringTokenizer(in.readLine());
			for(int i=0;i<cm_cnt;i++) {
				String I = commands.nextToken();
				int idx = Integer.parseInt(commands.nextToken());
				int num_cnt = Integer.parseInt(commands.nextToken());
				for(int j=0;j<num_cnt;j++) {
					int num = Integer.parseInt(commands.nextToken());
					if(idx>=10) {
						continue;
					}
					pw_list.add(idx++, num);
					pw_list.removeLast();
				}
			}
			
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(t);
			for(int i=0;i<10;i++) {
				sb.append(" ").append(pw_list.get(i));
			}
			System.out.println(sb.toString());
		}
	}

}
