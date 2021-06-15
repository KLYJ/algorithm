import java.util.Arrays;

class Solution {
    public int solution(int[] A, int[] B) {
        Arrays.sort(A);
		Arrays.sort(B);
		
		int answer = 0;
		for(int i=0, j=0;i<A.length && j<B.length;i++, j++) {
			if(A[i] < B[j])
				answer++;
			else
				i--;
		}
		
        return answer;
    }
}
