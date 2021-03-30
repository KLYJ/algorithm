class Solution {
    public int solution(int[][] triangle) {
        int len = triangle.length;
		
		for(int i=1;i<len;i++) {
			int t_len = triangle[i-1].length;
			for(int j=0;j<t_len+1;j++) {
				if(j==0) {
					triangle[i][j] += triangle[i-1][j];
				}
				else if(j==t_len) {
					triangle[i][j] += triangle[i-1][j-1]; 
				}
				else {
					triangle[i][j] += Math.max(triangle[i-1][j], triangle[i-1][j-1]);
				}
			}
		}
		
		int answer = 0;
		for(int i=0;i<len;i++) {
			int t = triangle[len-1][i];
			if(answer < t)
				answer = t;
		}
        return answer;
    }
}
