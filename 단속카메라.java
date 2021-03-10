package Level3;

import java.util.Arrays;
import java.util.Comparator;

public class 단속카메라 {
	
	public static void main(String[] args) {
		System.out.println(solution2(new int[][] {{-20,15}, {-14,-5}, {-18,-13}, {-5,-3}}));
		System.out.println(solution2(new int[][] {{0,3}, {2,4}, {7,9}, {5,9}}));
	}
	
	static class Compare implements Comparator<int []>{

		@Override
		public int compare(int[] o1, int[] o2) {
			return o1[1]-o2[1];
		}
		
	}
	
	public static int solution2(int[][] routes) {
		Arrays.sort(routes, new Comparator<int []>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				int diff = o1[0]-o2[0];
				return diff==0?o1[1]-o2[1]:diff;
			}
		});
		
		int answer=1;
		int start = -30000;
		int end = 30000;
		for(int i=0;i<routes.length;i++) {
//			System.out.println("routes:"+routes[i][0]+"/"+routes[i][1]);
			if(start<=routes[i][0]) {
				start = routes[i][0];
				if(routes[i][1]<=end) {
					end = routes[i][1];
				}
			}
			if(end<routes[i][0]) {
				answer++;
				start = routes[i][0];
				end = routes[i][1];
			}
//			System.out.println(start+"/"+end);
//			System.out.println("answer:"+answer);
		}
		
		return answer;
	}
	
	//TC1 실패 / 나머지 시간초과
	public static int solution(int[][] routes) {
		int start[][] = new int[routes.length][2];  //인덱스 : 진입시점
		int end[][] = new int[routes.length][2];    //인덱스 : 진출시점
		
		for(int i=0;i<routes.length;i++) {
			start[i][0] = i;
			start[i][1] = routes[i][0];
			end[i][0] = i;
			end[i][1] = routes[i][1];
		}
		
		//시점 기준 정렬
		Compare c = new Compare(); 
		Arrays.sort(start, c);
		Arrays.sort(end, c);
		
//		for(int i=0;i<start.length;i++) {
//			System.out.println(Arrays.toString(start[i]));
//			System.out.println(Arrays.toString(end[i]));
//		}
		
		int answer = 1;
		
		int start_idx = 0;
		int end_idx = 0;
		while(start_idx<start.length){
//			System.out.println(start_idx+" "+end_idx);
			if(start[start_idx][1]<=end[end_idx][1]) {
				start_idx++;
			}
			else {
				answer++;
				if(start_idx==start.length-1) {
					break;
				}
				while(start[start_idx][0]>end[end_idx][0]) {
					end_idx++;
				}
			}
		}
		
        return answer;
    }

}
