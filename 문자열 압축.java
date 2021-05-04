class Solution {
    public int solution(String s) {
        int answer = s.length();
		StringBuilder sb = new StringBuilder();
		
		int half = s.length()/2;
		for(int i=1;i<=half;i++) {
			// 초기화
			sb.setLength(0);
			int cnt = 0;
			int now_idx = 0;
			String before = s.substring(now_idx, now_idx+i);
            
			while(true) {
				if(now_idx > s.length()-1)
					break;
				
				String now = "";
				if(now_idx+i < s.length()) {
					now = s.substring(now_idx, now_idx+i);
					
					if(now.equals(before))
						cnt++;
					else {
						if(cnt == 1)
							sb.append(before);
						else
							sb.append(cnt).append(before);
						
						cnt = 1;
						before = now;
					}
				}
				else {
					now = s.substring(now_idx);
					
					if(now.equals(before))
						sb.append(cnt+1).append(before);
					else {
						if(cnt == 1)
							sb.append(before);
						else
							sb.append(cnt).append(before);
						sb.append(now);
					}
				}
				
				now_idx += i;
			}
			
			answer = Math.min(answer, sb.length());
		}
		
        return answer;
    }
}
