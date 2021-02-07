import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Sol_0204_SWEA1225 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		
		for(int i=1;i<=10;i++) {
			int TC = Integer.parseInt(in.readLine());
			String str[] = in.readLine().split(" ");
			
			Queue<Integer> queue = new LinkedList<>();
			for(int j=0;j<8;j++) {
				queue.offer(Integer.parseInt(str[j]));
			}
			
			boolean flag = true;
			int minus = 1;
			while(flag) {
				if(minus==6)
					minus = 1;
				int num = queue.poll()-minus;
				if(num<=0) {
					num = 0;
					flag = false;
				}
				queue.offer(num);
				minus++;
			}
			
			System.out.print("#"+i);
			for(int j=0;j<8;j++)
				System.out.print(" "+queue.poll());
			System.out.println();
		}

	}

}
