import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Sol_0216_BJ2839 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		
		// 방법1 : DP - 메모리 초과
//		Queue<int[]> queue = new LinkedList<int[]>();
//		queue.add(new int[] { 3, 1 });
//		queue.add(new int[] { 5, 1 });
//		int info2[] = new int[2];
//
//		while (!queue.isEmpty()) {
//			int info[] = queue.poll(); // info는 새로 생성되는거 아닐까?
//			int kg = info[0];
//			int cnt = info[1];
//			System.out.println(kg + " " + cnt);
//
//			if (kg == N) {
//				System.out.println(cnt);
//				break;
//			} else if (kg > N + 5) {
//				System.out.println(-1);
//				break;
//			} else {
//				info[0] = kg + 3;
//				info[1] = cnt + 1;
//				queue.offer(info); // info는 새로운게 offer
//				info2[0] = kg + 5;
//				info2[1] = cnt + 1;
//				queue.offer(info2); // queue에서 대기하던 모든 info2가 바뀜, 바뀐게 또 offer
//			}
//		}
		
		// 방법1.5 : 무게 queue, 개수 queue 나누기 - 메모리 초과
//		Queue<Integer> kg_queue = new LinkedList<>();
//		Queue<Integer> cnt_queue = new LinkedList<>();
//		kg_queue.offer(3); cnt_queue.offer(1);
//		kg_queue.offer(5); cnt_queue.offer(1);
//		
//		while (!kg_queue.isEmpty()) {
//			int kg = kg_queue.poll();
//			int cnt = cnt_queue.poll();
//			
//			if (kg == N) {
//				System.out.println(cnt);
//				break;
//			} else if (kg > N + 5) {
//				System.out.println(-1);
//				break;
//			} else {
//				kg_queue.offer(kg+3);
//				cnt_queue.offer(cnt+1);
//				kg_queue.offer(kg+5);
//				cnt_queue.offer(cnt+1);
//			}
//		}

		// 방법2 : 그리디 - 성공
		int five = N/5;
		for(int i=five;i>=0;i--) {
			if((N-5*i)%3==0) {
				System.out.println(i+(N-5*i)/3);
				break;
			}
			if(i==0) {
				System.out.println(-1);
				break;
			}
		}

	}

}
