class Solution {
    public int solution(String name) {
        int answer = 0;
        int len = name.length();
        int cnt = 0;  //검사한 문자 개수
        
        boolean visited[] = new boolean[len];
        for(int i=0;i<len;i++) {
        	//visited에 A만 true로 저장
        	if(name.charAt(i)=='A') {
        		visited[i] = true;
        		cnt++;
        	}
        	else {
        		//name의 모든 문자의 알파벳 이동 횟수 저장
            	answer += Math.min(Math.abs('A'-name.charAt(i)), Math.abs('Z'-name.charAt(i)+1));
        	}
        }
        
        int idx = 0; //0번째부터 검사
        if(!visited[0]) {
        	cnt++;
        	visited[0] = true;
        }
        //커서 이동 횟수 저장
        while(cnt<len) {        	
        	//현재 문자~검사 안 한 왼쪽 문자까지의 거리 검사
        	int left = idx;
        	int left_cnt = 0;
        	while(visited[left]) {
        		if(left==0)
        			left = len-1;
        		else
        			left--;
        		left_cnt++;
        	}
        	
        	//현재 문자~검사 안 한 오른쪽 문자까지의 거리 검사
        	int right = idx;
        	int right_cnt = 0;
        	while(visited[right]) {
        		if(right==len-1)
        			right = 0;
        		else
        			right++;
        		right_cnt++;
        	}
        	
        	if(left_cnt<right_cnt) {
        		idx = left;
        		answer += left_cnt;
        	}
        	else {
        		idx = right;
        		answer += right_cnt;
        	}
        	
        	visited[idx] = true;
        	cnt++;
        }
        
        return answer;
    }
}
