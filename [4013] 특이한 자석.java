import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Sol_0423_SWEA_4013 {
	
	static Deque<Integer> gears[];
	static int score[] = {1,2,4,8};
	static int rotate_gears[];
	static boolean visited[];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		int T = Integer.parseInt(in.readLine());
		
		for(int t=1;t<=T;t++) {
			sb.append("#").append(t).append(" ");
			
			int k = Integer.parseInt(in.readLine());
			gears = new Deque[4];
			
			for(int i=0;i<4;i++) {
				st = new StringTokenizer(in.readLine(), " ");
				gears[i] = new ArrayDeque<>();
				for(int j=0;j<8;j++) {
					gears[i].add(Integer.parseInt(st.nextToken()));
				}
			}
			
			for(int i=0;i<k;i++) {
				st = new StringTokenizer(in.readLine(), " ");
				int gear = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken());
				rotate_gears = new int[4];
				visited = new boolean[4];
				
				rotate(gear-1, dir);
				
				for(int j=0;j<4;j++) {
					if(rotate_gears[j] == 1)
						gears[j].addFirst(gears[j].pollLast());
					else if(rotate_gears[j] == -1)
						gears[j].addLast(gears[j].pollFirst());
				}
			}
			
			
			int answer = 0;
			for(int i=0;i<4;i++) {
				if(gears[i].pollFirst() == 1)
					answer += score[i];
			}
			
			sb.append(answer).append("\n");
		}
		
		System.out.println(sb.toString());
	}

	private static void rotate(int gear, int dir) {
		if(gear == 0) {
			cal(-1, gear, gear+1, dir);
		}
		else if(gear == 3){
			cal(gear-1, gear, -1, dir);
		}
		else {  // gear == 2 or 3
			cal(gear-1, gear, gear+1, dir);
		}
	}

	private static void cal(int left, int me, int right, int dir) {
		Iterator<Integer> iter = gears[me].iterator();		
		int me_right = -1, me_left = -1;
		for(int i=0;i<=6;i++) {
			int temp = iter.next();
			if(i==2)
				me_right = temp;
			else if(i==6)
				me_left = temp;
		}
		rotate_gears[me] = dir;
		visited[me] = true;
		
		if(left != -1 && !visited[left]) {
			Iterator<Integer> iter2 = gears[left].iterator();		
			int l_right = -1;
			for(int i=0;i<=2;i++) {
				int temp = iter2.next();
				if(i==2)
					l_right = temp;
			}
			
			if(me_left != l_right)
				rotate(left, dir==1?-1:1);
		}
		
		if(right != -1 && !visited[right]) {
			Iterator<Integer> iter3 = gears[right].iterator();		
			int r_left = -1;
			for(int i=0;i<=6;i++) {
				int temp = iter3.next();
				if(i==6)
					r_left = temp;
			}
			
			if(me_right != r_left)
				rotate(right, dir==1?-1:1);
		}
	}

}
