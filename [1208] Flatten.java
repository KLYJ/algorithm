import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Sol_0202_1_SWEA1208 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		for (int i = 1; i <11; i++) {
			int dump = Integer.parseInt(in.readLine());
			List<Integer> box = new ArrayList<Integer>();
			String temp[] = in.readLine().split(" ");
			for (int j = 0; j < 100; j++)
				box.add(Integer.parseInt(temp[j]));

			int j=0;
			while (dump > 0) {
				Collections.sort(box); //nlogn
				
				int max = box.get(99);
				int min = box.get(0);
				int dif_fir = max - min;
				if(dif_fir <= 1) break;
				int dif_sec = box.get(98) - box.get(1);
				int dif = (dif_fir - dif_sec) / 2;
				if(dif==0)
					dif = 1;				
				if (dump < dif) {
					dump = 0;
					box.set(99, max - (dif-dump));
					box.set(0, min + (dif-dump));
				} else {
					box.set(99, max - dif);
					box.set(0, min + dif);
					dump -= dif;
				}
			}
			
			Collections.sort(box);
			System.out.println("#"+i+" "+(box.get(99)-box.get(0)));

		}

	}

}
