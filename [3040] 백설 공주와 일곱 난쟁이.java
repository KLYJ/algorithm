import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_3040 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int num[] = new int[9];
		int sum = 0;
		for(int i=0;i<9;i++) {
			num[i] = Integer.parseInt(in.readLine());
			sum += num[i];
		}
		
		//comb(0, num, new int[7], 0, 0);
		
		comb2(0, num, new int[2], 0, sum);
		
	}

	//방법2 : 2명 골라서 빼기(72ms)
	private static int comb2(int selectTo, int[] num, int[] selected, int start, int sum) {
		if(selectTo == 2) {
			if(sum == 100) {
				for(int i=0;i<9;i++) {
					if(selected[0]!=num[i] && selected[1]!=num[i])
						System.out.println(num[i]);
				}
				return 1;
			}
			else
				return 0;
		}
		
		for(int i=start;i<9;i++) {
			selected[selectTo] = num[i];
			int flag = 0;
			if(sum-num[i]>=100)
				flag = comb2(selectTo+1, num, selected, i+1, sum-num[i]);
			if(flag == 1)
				break;
		}
		return 0;
	}

	//방법1 : 7명 골라서 더하기(80ms)
	private static int comb(int selectTo, int[] num, int[] selected, int start, int sum) {
		if(selectTo == 7) {
			if(sum == 100) {
				for(int i=0;i<7;i++) {
					System.out.println(selected[i]);
				}
				return 1;
			}
			return 0;
		}
		
		for(int i=start;i<9;i++) {
			selected[selectTo] = num[i];
			int flag = 0;
			if(sum+num[i]<=100)
				flag = comb(selectTo+1, num, selected, i+1, sum+num[i]);
			if(flag == 1)
				break;
		}
		return 0;
	}	

}
