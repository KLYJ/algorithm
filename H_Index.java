import java.util.Arrays;

class Solution {
    public int solution(int[] citations) {
        int answer = 0;
		
        Arrays.sort(citations);
		
        int up = citations.length;
        for(int i=0;i<citations.length;i++) {
            if(up<=citations[i]) {
                return up;
            }
            up--;
        }
		
        return answer;
    }
}
