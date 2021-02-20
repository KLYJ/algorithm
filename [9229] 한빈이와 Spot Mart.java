import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SWEA_9229 {

	static int max, weight, snack_cnt;
	static int snack[];
	
	private static void comb(int selectTo, int[] selected, int start) {
		if(selectTo==2) {
			int sum = selected[0]+selected[1];
			if(sum<=weight)
				max = Math.max(max, sum);
			return;
		}
		
		for(int i=start;i<snack_cnt;i++) {
			selected[selectTo] = snack[i];
			comb(selectTo+1, selected, i+1);
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());

		for (int t = 1; t <= T; t++) {
			String info[] = in.readLine().split(" ");
			snack_cnt = Integer.parseInt(info[0]);
			weight = Integer.parseInt(info[1]);
			max = -1;

			String input[] = in.readLine().split(" ");
			snack = new int[snack_cnt];
			for (int i = 0; i < snack_cnt; i++) {
				snack[i] = Integer.parseInt(input[i]);
			}
			comb(0, new int[2], 0);
			
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(t).append(" ").append(max);
			System.out.println(sb.toString());
		}

	}
}
