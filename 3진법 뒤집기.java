package Level1;

import java.util.ArrayList;
import java.util.List;

public class _3진법뒤집기 {
	
	public static void main(String[] args) {
		System.out.println(solution(45));
		System.out.println(solution(125));
	}
	
	public static int solution(int n) {
		List<Integer> list = new ArrayList<>();
		//10진법->3진법 뒤집어서 list에 저장
		while(n!=0) {
			list.add(n%3);
			n /= 3;
		}
		
		//3진법 -> 10진법
        	int answer = 0;
		int num=1;
		for(int i=list.size()-1;i>=0;i--) {
			answer += list.get(i)*num;
			num *= 3;
		}
		
        	return answer;
   	}	
	
	public int solution2(int n) {
	    	StringBuilder sb = new StringBuilder();
		while(n>0){
 		   sb.append(n%3);
 		   n/=3;
   		}
        
  	        int answer = Integer.parseInt(sb.toString(),3);
  	        return answer;
 	}	

}
