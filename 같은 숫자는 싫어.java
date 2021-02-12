package Level1;

import java.util.*;

public class 같은숫자는싫어 {

	public static void main(String[] args) {
		System.out.println(Arrays.toString(solution(new int[] { 1, 1, 3, 3, 0, 1, 1 })));
	}

	public static int[] solution(int[] arr) {
		//방법1 : ArrayList(훨씬 빠름)
        List<Integer> hm = new ArrayList<>();
        hm.add(arr[0]);
        for(int i=1;i<arr.length;i++) {
        	if(arr[i-1]!=arr[i])
        		hm.add(arr[i]);
        }
        
        int[] answer = new int[hm.size()];
        for(int i=0;i<hm.size();i++)
        	answer[i] = hm.get(i);        	
        return answer;

        //방법2 : Stack
		Stack<Integer> stack = new Stack<>();
		stack.add(arr[0]);
		for (int i = 1; i < arr.length; i++) {
			if (arr[i - 1] != arr[i])
				stack.push(arr[i]);
		}
		int[] answer = new int[stack.size()];
		for (int i = stack.size()-1; i >=0; i--)
			answer[i] = stack.pop();
		return answer;
	}
}
