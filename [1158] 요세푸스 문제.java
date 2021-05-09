import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Sol_0209_BJ_1158 {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String info[] = in.readLine().split(" ");
		int N = Integer.parseInt(info[0]); // 배열 길이
		int K = Integer.parseInt(info[1]); // 몇번째

		// 방법1 : queue
		Queue<Integer> queue = new LinkedList<Integer>();
		for(int i=1;i<N+1;i++)
			queue.offer(i);
		
		StringBuilder sb = new StringBuilder();
		sb.append("<");
		
		while(!queue.isEmpty()) {
			for(int i=0;i<K;i++) {
				if(i<K-1) {
					queue.offer(queue.poll());
				}
				else {
					sb.append(queue.poll());
					if(queue.size()==0)
						sb.append(">");
					else
						sb.append(", ");
				}
			}
		}
		
		System.out.println(sb.toString());

		// 방법2 : 배열
		int num[] = new int[N];
		for (int i = 1; i < N + 1; i++) {
			num[i - 1] = i;
		}

		StringBuilder sb = new StringBuilder();
		sb.append("<");
		
		int idx = 0;
		int cnt = N;
		while(cnt!=0) {
			int check = 0;
			while(true) {
				if(num[idx]!=0)
					check++;
				if(check==K) break;
				idx = idx==N-1?0:idx+1;
			}
			//선택하면
			sb.append(num[idx]);
			cnt--;
			if (cnt == 0)
				sb.append(">");
			else
				sb.append(", ");
			num[idx] = 0;
		}

		System.out.println(sb.toString());

	}

}
