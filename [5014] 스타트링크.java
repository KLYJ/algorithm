package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_5014 {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		int max = Integer.parseInt(st.nextToken());
		int from = Integer.parseInt(st.nextToken());
		int to = Integer.parseInt(st.nextToken());
		int up = Integer.parseInt(st.nextToken());
		int down = Integer.parseInt(st.nextToken());

		Queue<int[]> queue = new LinkedList<int[]>();
		boolean visited[] = new boolean[max + 1];
		queue.add(new int[] { from, 0 });
		visited[from] = true;

		boolean check = false;
		while (!queue.isEmpty()) {
			int floor = queue.peek()[0];
			int cnt = queue.poll()[1];
			
			if(floor == to) {
				System.out.println(cnt);
				check = true;
				break;
			}

			if (floor - down >= 1 && !visited[floor - down]) {
				queue.add(new int[] {floor-down, cnt+1});
				visited[floor-down] = true;
			}
			if (floor + up <= max && !visited[floor + up]) {
				queue.add(new int[] {floor+up, cnt+1});
				visited[floor+up] = true;
			}
		}
		
		if(!check)
			System.out.println("use the stairs");

	}

}
