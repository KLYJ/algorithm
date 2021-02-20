import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Sol_0217_BJ2039 {
	
	static int sum = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int tall[] = new int[9];
		
		for(int i=0;i<9;i++) {
			tall[i] = Integer.parseInt(in.readLine());
			sum += tall[i];
		}
		Arrays.sort(tall);
		
		sum -= 100;		
		comb(0, new int[2], 0, tall);
	}

	private static int comb(int selectTo, int selected[], int start, int tall[]) {
		if(selectTo == 2) {
			if(selected[0]+selected[1] != sum)
				return 0;
			for(int i=0;i<9;i++) {
				if(tall[i] != selected[0] && tall[i] != selected[1])
					System.out.println(tall[i]);
			}
			return 1;
		}
		
		for(int i=start;i<9;i++) {
			selected[selectTo] = tall[i];
			int flag = comb(selectTo+1, selected, i+1, tall);
			if(flag==1)
				return 1;
		}
		return 0;
	}

}
