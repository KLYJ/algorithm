class Solution {
    public int[] solution(int brown, int yellow) {
        int answer[] = new int[2];
		
        int xy = brown+yellow;
        for(int i=3;i<=xy;i++) {
            if(xy%i==0) {
                int garo = Math.max(xy/i, i);
                int sero = xy/garo;
                if((garo-2)*(sero-2)==yellow) {
                    answer[0] = garo;
                    answer[1] = sero;
                }
            }
        }
		
        return answer;
    }
}
