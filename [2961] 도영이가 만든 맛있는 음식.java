import java.io.InputStreamReader;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.IOException;

public class Sol_0215_BJ2961 {
	
	static int T;
	static int min = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(in.readLine());
		int taste[][] = new int[T][2];
		for(int i=0;i<T;i++) {
			String info[] = in.readLine().split(" ");
			taste[i][0] = Integer.parseInt(info[0]);
			taste[i][1] = Integer.parseInt(info[1]);
		}
		
		subset(0, new boolean[T], taste);
		subset2(0, 1, 0, taste);
		
		System.out.println(min);
		
	}
	
	//방법2 : 뽑으면서 계산
	private static void subset2(int selectTo, int sour, int bitter, int taste[][]) {
		if(selectTo == T) {
			min = Math.min(min, Math.abs(sour-bitter));
			return;
		}
		
		subset2(selectTo+1, sour*taste[selectTo][0], bitter+taste[selectTo][1], taste);
		subset2(selectTo+1, sour, bitter, taste);
	}
	

	//방법1 : 다 뽑고 계산
	private static void subset(int selectTo, boolean[] selected, int taste[][]) {
		if(selectTo == T) {
			int sour = 1, bitter = 0;
			int cnt = 0;
			for(int i=0;i<T;i++) {
				if(selected[i] == true) {
					sour *= taste[i][0];
					bitter += taste[i][1];
				}
				else {
					cnt++;
				}
			}
			if(cnt==T) 
				return;
			min = Math.min(min, Math.abs(sour-bitter));
			return;
		}
		
		selected[selectTo] = true;
		subset(selectTo+1, selected, taste);
		selected[selectTo] = false;
		subset(selectTo+1, selected, taste);
	}

}
