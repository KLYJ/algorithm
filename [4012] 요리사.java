import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Sol_0219_SWEA_4012 {
	
	static int N;
	static int map[][];
	static ArrayList<Integer> taste_list = new ArrayList<Integer>();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());
		StringBuilder sb = new StringBuilder();
		
		for(int t=1;t<=T;t++) {
			sb.append("#").append(t).append(" ");
			
			N = Integer.parseInt(in.readLine());
			map = new int[N][N];
			for(int i=0;i<N;i++) {
				StringTokenizer st = new StringTokenizer(in.readLine(), " ");
				for(int j=0;j<N;j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int answer = Integer.MAX_VALUE;
			comb(0, new int[N/2], 0, 0);
			int size = taste_list.size();
			for(int i=0;i<size/2;i++) {
				answer = Math.min(answer, Math.abs(taste_list.get(i)-taste_list.get(size-1-i)));
			}
			taste_list.clear();
			sb.append(answer).append("\n");
		}
		
		System.out.println(sb.toString());
	}

	private static void comb(int selectTo, int[] selected, int start, int taste) {
		if(selectTo==N/2) {
			taste_list.add(taste);
			return;
		}
		
		for(int i=start;i<N;i++) {
			selected[selectTo] = i;
			int add = 0;
			if(selectTo != 0) {
				for(int j=0;j<selectTo;j++) {
					add += map[selected[j]][i]+map[i][selected[j]];
				}
			}
			comb(selectTo+1, selected, i+1, taste+add);
		}
	}

}
