import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
		    int answer = 0;
		
		    Arrays.sort(people);
		
		    //left : 작은거  / right : 큰 거
		    int left = 0;
		    int right = people.length-1;
		    while(left<right) {
			      if(people[left]+people[right] <= limit) {
				        left++;
				        right--;
			      }
			      else {
				        right--;
			      }
			      answer++;
		    }
		    if(left==right)
			      answer++;
		
		    return answer;
    }
}
