package Level1;

import java.util.Arrays;
import java.util.Stack;

public class 체육복 {
	
  //방법1 : 
	public static int solution(int n, int[] lost, int[] reserve) {
        int answer = n-lost.length;
        Arrays.sort(lost);
        Arrays.sort(reserve);
        
        Stack<Integer> s_lost = new Stack<>(); 
        Stack<Integer> s_reserve = new Stack<>();
        
        for(int i=lost.length-1;i>=0;i--) {
        	s_lost.push(lost[i]);
        }
        for(int i=reserve.length-1;i>=0;i--) {
        	s_reserve.push(reserve[i]);
        }
        
        while(!s_lost.isEmpty() && !s_reserve.isEmpty()) {
        	//잃어버린 체육복 번호-1이 빌려줄 체육복 번호보다 크면 빌려줄 체육복 번호 pop
        	if(s_lost.peek()-1 > s_reserve.peek()) {
        		s_reserve.pop();
        		continue;
        	}
        	//잃어버린 체육복 번호+1보다 빌려줄 체육복 번호가 크면 잃어버린 체육복 번호 pop
        	if(s_lost.peek()+1 < s_reserve.peek()) {
        		s_lost.pop();
        		continue;
        	}
        	//잃어버린 체육복 번호 == 빌려줄 체육복 번호 -> 둘 다 pop, answer++
        	if(s_lost.peek() == s_reserve.peek()) {
    			s_reserve.pop();
    			s_lost.pop();
        		answer++;
    		}
        	//잃어버린 체육복 번호의 앞번호 == 빌려줄 체육복 번호
        	else if(s_lost.peek()-1 == s_reserve.peek()) {
        		s_reserve.pop();
        		//빌려줄 다음 체육복 번호와 잃어버린 체육복 번호가 같은지 확인
        		if(!s_reserve.isEmpty() && s_lost.peek() == s_reserve.peek()) {
        			//같은게 있으면 빌려줄 체육복 pop
        			s_reserve.pop();
        		}
        		s_lost.pop();
        		answer++;
        	}
        	//잃어버린 체육복 번호의 뒷번호 == 빌려줄 체육복 번호
        	else if(s_lost.peek()+1 == s_reserve.peek()) {
        		s_lost.pop();
        		//잃어버린 다음 체육복 번호와 빌려줄 체육복 번호가 같은지 확인
        		if(!s_lost.isEmpty() && s_lost.peek() == s_reserve.peek()) {
        			//같은게 있으면 잃어버린 체육복 pop
        			s_lost.pop();
        		}
        		s_reserve.pop();
        		answer++;
        	}
        }
        
        return answer;
    }
	
  //방법2 : 계수 배열 이용
	public static int solution2(int n, int[] lost, int[] reserve) {
		int arr[] = new int[n]; 
		Arrays.fill(arr, 1);
		
		for(int i=0;i<lost.length;i++)
			arr[lost[i]-1]--;
		
		for(int i=0;i<reserve.length;i++)
			arr[reserve[i]-1]++;
		
		for(int i=0;i<n;i++) {
			if(arr[i]==0 && i+1<n && arr[i+1] == 2) {
				arr[i]++;
				arr[i+1]--;
			}
			if(arr[i]==2 && i+1<n && arr[i+1] == 0) {
				arr[i]--;
				arr[i+1]++;
			}
		}
		
		int cnt = 0;
		for(int i=0;i<n;i++) {
			if(arr[i] >= 1)
				cnt++;
		}
		
		return cnt;
	}
}
