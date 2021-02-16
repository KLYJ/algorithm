import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class JO_1828 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		
		int chem[][] = new int[N][2]; 
		for(int i=0;i<N;i++) {
			String info[] = in.readLine().split(" ");
			chem[i][0] = Integer.parseInt(info[0]);
			chem[i][1] = Integer.parseInt(info[1]);
		}
		
		Arrays.sort(chem, new Comparator<int[]>(){
			@Override
			public int compare(int[] o1, int[] o2) {
				int diff = o1[0]-o2[0];
				return diff!=0?diff:o2[1]-o1[1];
			}
		});
		
		//방법1 : ArrayList
		ArrayList<Integer> list = new ArrayList<>();
		list.add(chem[0][1]);
		for(int i=0, size = chem.length;i<size;i++) {
			if(list.get(list.size()-1) < chem[i][0])
				list.add(chem[i][1]);
			else {
				if(list.get(list.size()-1) > chem[i][1])
					list.set(list.size()-1, chem[i][1]);
			}
		}
		
		System.out.println(list.size());
		
		//방법2 : 변수 (20ms 빠름)
		int compare = chem[0][1];
		int cnt = 1;
		for(int i=0, size = chem.length;i<size;i++) {
			if(compare < chem[i][0]) {
				compare = chem[i][1];
				cnt++;
			}
			else {
				if(compare > chem[i][1])
					compare = chem[i][1];
			}
		}
		
		System.out.println(cnt);
	}

}
