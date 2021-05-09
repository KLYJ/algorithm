import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Sol_0217_BJ2605 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		LinkedList<Integer> list = new LinkedList<>();
		
		String[] info = in.readLine().split(" ");
		for(int i=0;i<N;i++) {
			list.add(list.size()-Integer.parseInt(info[i]), i+1);
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<N;i++)
			sb.append(list.get(i)).append("\n");
		
		System.out.println(sb.toString());
	}

}
